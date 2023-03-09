package com.hungnguyen.blogweb.Controller;

import com.hungnguyen.blogweb.Model.chu_de;
import com.hungnguyen.blogweb.Model.dau_sach;
import com.hungnguyen.blogweb.Model.nguoi_dung;
import com.hungnguyen.blogweb.Service.ChuDeService;
import com.hungnguyen.blogweb.Service.DauSachService;
import com.hungnguyen.blogweb.Service.NguoiDungService;
import com.hungnguyen.blogweb.Service.xuly;
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
public class ChudDeController {

    @Autowired
    ChuDeService chuDeService;
    @Autowired
    DauSachService dauSachService;
    @Autowired
    NguoiDungService nd;
    @Autowired
    xuly xuly;

    @GetMapping("/danhsachcd")
    public String danhsachCD(Model model){
        List<chu_de> chuDes = chuDeService.findallCD();
        model.addAttribute("chudelist",chuDes);
        nguoi_dung nguoiDung = new nguoi_dung();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication!=null){
            nguoiDung = nd.findbyusername(authentication.getName());
            model.addAttribute("nd",nguoiDung);
        }

        return "DanhSachCD";
    }

    @GetMapping("/chude")
    public String chude(Model model,@ModelAttribute("cdid") int id,@RequestParam(value = "page",defaultValue = "1") int page){



        chu_de chuDe = chuDeService.findCDbyid(id);
        model.addAttribute("chude",chuDe);

        int currentpage=page;
        int bd = 0;
        if(currentpage==1){

        }else {
            bd = (currentpage-1)* itemperpage;
        }
        List<dau_sach> dauSachList = dauSachService.findbycdidpt(id,bd,itemperpage);
        model.addAttribute("crpage",currentpage);


        model.addAttribute("dslist",dauSachList);

        List<Integer> p =  xuly.page( dauSachService.findbycdid(id).size(),itemperpage);
        model.addAttribute("page",p);

        nguoi_dung nguoiDung = new nguoi_dung();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication!=null){
            nguoiDung = nd.findbyusername(authentication.getName());
            model.addAttribute("nd",nguoiDung);
        }
        return "chude";
    }
}
