package com.hungnguyen.blogweb.Controller;

import com.hungnguyen.blogweb.Model.dau_sach;
import com.hungnguyen.blogweb.Model.nguoi_dung;
import com.hungnguyen.blogweb.Service.DauSachService;
import com.hungnguyen.blogweb.Service.NguoiDungService;
import com.hungnguyen.blogweb.Service.xuly;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.hungnguyen.blogweb.Service.xuly.itemperpage;


@Controller
public class HomeController {

    @Autowired
    DauSachService dsservice;
    @Autowired
    NguoiDungService nguoiDungService;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    xuly xuly;


    @GetMapping("/")
    public String home() {
        return "redirect:/index";
    }

    @GetMapping("/index")
    public String Index(Model model, @RequestParam(value = "page",defaultValue = "1") int page ){
        int currentpage=page;
        int bd = 0;
        if(currentpage==1){

        }else {
            bd = (currentpage-1)* itemperpage;
        }
        List<dau_sach> dauSachList =dsservice.findallpt(bd,itemperpage);
        model.addAttribute("crpage",currentpage);

        model.addAttribute("danhsachDS",dauSachList);

        List<Integer> p = xuly.page(dsservice.findallds().size(),itemperpage);
        model.addAttribute("page",p);

        nguoi_dung nguoiDung = new nguoi_dung();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication!=null){
            nguoiDung = nguoiDungService.findbyusername(authentication.getName());
            model.addAttribute("nd",nguoiDung);
        }

        return "index";
    }


    @GetMapping("/index/page")
    public String Indexpage(Model model, @ModelAttribute("page") int page){
        List<dau_sach> dauSachList = dsservice.findallds().subList(page-1,page-1+itemperpage);
        model.addAttribute("danhsachDS",dauSachList);
        nguoi_dung nguoiDung = new nguoi_dung();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication!=null){
            nguoiDung = nguoiDungService.findbyusername(authentication.getName());
            model.addAttribute("nd",nguoiDung);
        }
        return "redirect:/index/page?page="+page;
    }

    @GetMapping("/login")
    public String login(){
        /*nguoi_dung user =*/
        return "login";
    }
    @GetMapping("/register")
    public String register(){
        return "register";
    }

    @PostMapping("/register/save")
    public String saveuser(nguoi_dung nguoiDung){
        nguoiDung.setHinh_ND("666201.png");
        if(!nguoiDungService.patternMatches(nguoiDung.getEmail())){
            return "redirect:/register";
        }
        if(nguoiDungService.findbyusername(nguoiDung.getTEN_DANG_NHAP())!=null){
            return "redirect:/register";
        }
        if(nguoiDungService.findbyemail(nguoiDung.getEmail())!=null){
            return "redirect:/register";
        }
        nguoiDung.setHinh_ND("666201.png");
        String pass = bCryptPasswordEncoder.encode(nguoiDung.getMAT_KHAU());
        nguoiDung.setMAT_KHAU(pass);

        nguoiDungService.save(nguoiDung);

        return "redirect:/login";
    }

}
