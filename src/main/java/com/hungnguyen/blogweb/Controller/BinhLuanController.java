package com.hungnguyen.blogweb.Controller;

import com.hungnguyen.blogweb.Model.binh_luan;
import com.hungnguyen.blogweb.Service.BinhLuanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.Date;

@Controller
public class BinhLuanController {

    @Autowired
    BinhLuanService binhLuanService;

    @GetMapping("/comment")
    public String savecomment(binh_luan binhluan, @ModelAttribute("dsid") int id){
        if(binhluan.getID_NGUOI_DUNG()==0) return "redirect:/login";
        binhluan.setNGAY_BL(new Date());
        binhLuanService.save(binhluan);
        return "redirect:/chapter?dsid="+id+"&chapid="+binhluan.getID_CHAPTER();
    }
}
