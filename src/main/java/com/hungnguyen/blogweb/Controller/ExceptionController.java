package com.hungnguyen.blogweb.Controller;

import com.hungnguyen.blogweb.Model.nguoi_dung;
import com.hungnguyen.blogweb.Service.NguoiDungService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@SuppressWarnings("deprecation")
public class ExceptionController implements ErrorController{
    @Autowired
    NguoiDungService nguoiDungService;

    @RequestMapping("/error")
    public String handleError(HttpServletResponse response, Model model) {
        nguoi_dung nguoiDung = new nguoi_dung();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication!=null){
            nguoiDung = nguoiDungService.findbyusername(authentication.getName());
            model.addAttribute("nd",nguoiDung);
        }
        int status = response.getStatus();
        model.addAttribute("tenloi",status);
        return "error";
    }

    public String getErrorPath() {
        return null;
    }

}
