package com.hungnguyen.blogweb.Controller;

import com.hungnguyen.blogweb.Model.binh_luan;
import com.hungnguyen.blogweb.Model.chapter;
import com.hungnguyen.blogweb.Model.dau_sach;
import com.hungnguyen.blogweb.Model.nguoi_dung;
import com.hungnguyen.blogweb.Service.BinhLuanService;
import com.hungnguyen.blogweb.Service.ChapterService;
import com.hungnguyen.blogweb.Service.DauSachService;
import com.hungnguyen.blogweb.Service.NguoiDungService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Date;
import java.util.List;

@Controller
public class ChapterController {
    @Autowired
    DauSachService dauSachService;
    @Autowired
    ChapterService chapterService;
    @Autowired
    BinhLuanService binhLuanService;
    @Autowired
    NguoiDungService nd;
    int dsid=-1;

    @GetMapping("/chapter")
    public String Chapter(Model model,@ModelAttribute("dsid") int id, @ModelAttribute("chapid") int chapid){

        dau_sach ds = dauSachService.findbyid(id);
        model.addAttribute("ds",ds);

        List<chapter> chapterList = chapterService.findchaptertheodsid(id);
        model.addAttribute("chapterlist",chapterList);

        chapter chapter = chapterService.findchapbyid(chapid);
        model.addAttribute("chap",chapter);

        List<dau_sach> hot = dauSachService.sachnhieuluotxem();
        model.addAttribute("hot",hot);

        List<binh_luan> binhLuans = binhLuanService.findbychapid(chapid);
        model.addAttribute("binhluans",binhLuans);

        nguoi_dung nguoiDung = new nguoi_dung();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication!=null){
            nguoiDung = nd.findbyusername(authentication.getName());
            model.addAttribute("nd",nguoiDung);
        }

        chapterService.tangluotxem(chapid);
        return "chap";
    }

   @GetMapping("/themchap")
    public String themchap(Model model,@ModelAttribute("dsid") int id,
            @ModelAttribute("userid") int uid,@ModelAttribute("username") String usn) {
       dsid = id;
       dau_sach dauSach = dauSachService.findbyid(id);
       model.addAttribute("dausach", dauSach);

       nguoi_dung nguoiDung =  nd.findnd(uid,usn);
       model.addAttribute("nguoidung", nguoiDung);

       return "themchap";
   }

    @PostMapping("/themchap/them")
    public String save(chapter chap,@ModelAttribute("userid") int uid,@ModelAttribute("username") String usn){
        if(!chap.getNOI_DUNG().isEmpty()){
            chap.setID_SACH(dsid);
            chap.setNGAY_DANG(new Date());
            chapterService.savechap(chap);
            return "redirect:/dsdausach?userid="+uid+"&username="+usn;
        }else return "redirect:/themchap?dsid="+dsid+"&userid="+uid+"&username="+usn;
    }

    @GetMapping("/suachap")
    public String suachap(Model model,@ModelAttribute("chapid") int id,@ModelAttribute("userid") int uid,
                          @ModelAttribute("username") String usn,@ModelAttribute("dsid") int dsid) {
        chapter chap = chapterService.findchapbyid(id);
        model.addAttribute("chap",chap);
        nguoi_dung nguoiDung =  nd.findnd(uid,usn);
        model.addAttribute("nguoidung", nguoiDung);
        model.addAttribute("dsid",dsid);
        return "suachapter";
    }

    @PostMapping("/suachap/sua")
    public String update(chapter chap,@ModelAttribute("userid") int uid,@ModelAttribute("username") String usn){
        if(!chap.getNOI_DUNG().isEmpty()){
            chapter c = chapterService.findchapbyid(chap.getID_CHAPTER());
            c.setTEN_CHAPTER(chap.getTEN_CHAPTER());
            c.setNOI_DUNG(chap.getNOI_DUNG());
            chapterService.savechap(c);
            return "redirect:/dsdausach?userid="+uid+"&username="+usn;
        }else return "/suachap?chapid="+chap.getID_CHAPTER()+"&userid="+uid+"&username="+usn;
    }
}
