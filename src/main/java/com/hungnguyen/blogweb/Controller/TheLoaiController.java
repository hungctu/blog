package com.hungnguyen.blogweb.Controller;


import com.hungnguyen.blogweb.Model.dau_sach;
import com.hungnguyen.blogweb.Model.nguoi_dung;
import com.hungnguyen.blogweb.Model.the_loai;
import com.hungnguyen.blogweb.Service.DauSachService;
import com.hungnguyen.blogweb.Service.NguoiDungService;
import com.hungnguyen.blogweb.Service.TheLoaiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import static com.hungnguyen.blogweb.Service.xuly.itemperpage;


@Controller
public class TheLoaiController {

    @Autowired
    TheLoaiService theLoaiService;
    @Autowired
    DauSachService dauSachService;
    @Autowired
    NguoiDungService nd;
    @Autowired
    com.hungnguyen.blogweb.Service.xuly xuly;

    @GetMapping("/danhsachtl")
    public String danhsachTL(Model model){
        List<the_loai> theLoais = theLoaiService.findallTL();
        model.addAttribute("theloailist",theLoais);
        nguoi_dung nguoiDung = new nguoi_dung();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication!=null){
            nguoiDung = nd.findbyusername(authentication.getName());
            model.addAttribute("nd",nguoiDung);
        }

        return "DanhSachTL";
    }

    @GetMapping("/theloai")
    public String theloai(Model model, @ModelAttribute("tlid") int id,@RequestParam(value = "page",defaultValue = "1") int page){
        the_loai theLoai = theLoaiService.findTLbyid(id);
        model.addAttribute("theloai",theLoai);

        int currentpage=page;
        int bd = 0;
        if(currentpage==1){

        }else {
            bd = (currentpage-1)* itemperpage;
        }
        model.addAttribute("crpage",currentpage);

        List<dau_sach> dauSachList = dauSachService.findbytlidpt(id,bd,itemperpage);
                model.addAttribute("dslist",dauSachList);

        List<Integer> p =  xuly.page( dauSachService.findbycdid(id).size(),itemperpage);
        model.addAttribute("page",p);

        nguoi_dung nguoiDung = new nguoi_dung();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication!=null){
            nguoiDung = nd.findbyusername(authentication.getName());
            model.addAttribute("nd",nguoiDung);
        }
        return "theloai";
    }
}
