package com.hungnguyen.blogweb.Controller;

import com.hungnguyen.blogweb.Model.nguoi_dung;
import com.hungnguyen.blogweb.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller

public class NguoiDungController {

    @Autowired
    NguoiDungService nd;
    @Autowired
    DauSachService dauSachService;
    @Autowired
    ImageService imageService;
    @Autowired
    DanhGiaService danhGiaService;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    @GetMapping("/dsdausach")
    public String dstacpham(Model model, @ModelAttribute("userid") int id,@ModelAttribute("username") String usn){
        nguoi_dung nguoiDung =  nd.findnd(id,usn);
        model.addAttribute("nguoidung", nguoiDung);
        return "dstacpham";
    }

    @GetMapping("/ttnguoidung")
    public String thongtinnguoidung(Model model, @ModelAttribute("userid") int id,@ModelAttribute("username") String usn){
        nguoi_dung nguoiDung =  nd.findnd(id,usn);
        model.addAttribute("nguoidung", nguoiDung);

        int tonglx = dauSachService.tongluotxem(id);
        int like = danhGiaService.tongluotdg(id,0);
        int dislike = danhGiaService.tongluotdg(id,1);

        model.addAttribute("tong",tonglx);
        model.addAttribute("like",like);
        model.addAttribute("dislike",dislike);
        return "userprofile";
    }

    @PostMapping("/ttnguoidung/update")
    public String updateuser(nguoi_dung nguoiDung,@RequestParam("hinhnguoidung") MultipartFile image) throws IOException {
        int id = nguoiDung.getID_NGUOI_DUNG();
        String usn = nguoiDung.getTEN_DANG_NHAP();
        nguoi_dung nguoiDung1 = nd.findnd(id,usn);
        nguoiDung1.setTEN_NGUOI_DUNG(nguoiDung.getTEN_NGUOI_DUNG());
        nguoiDung1.setEmail(nguoiDung.getEmail());
        if(!image.isEmpty()){
            nguoiDung1.setHinh_ND(image.getOriginalFilename().trim());
            imageService.saveImage(image);
        }
        nd.save(nguoiDung1);
        return "redirect:/ttnguoidung?userid="+id+"&username="+usn;
    }

    @PostMapping("/ttnguoidung/cnpass")
    public String capnhatpass( @ModelAttribute("userid") int id,@ModelAttribute("username") String usn,
                               @ModelAttribute("cpass") String cpass,@ModelAttribute("newpass") String newpass,
                               @ModelAttribute("renewpass") String renewpass
    ){
        nguoi_dung nguoidung = nd.findnd(id,usn);
        if(nguoidung!=null){
            if(bCryptPasswordEncoder.matches(cpass,nguoidung.getMAT_KHAU())){
                if(newpass.equals(renewpass)){
                    String newpassword = bCryptPasswordEncoder.encode(newpass);
                    nguoidung.setMAT_KHAU(newpassword);
                    nd.save(nguoidung);
                }
                else {
                    System.out.println("loi");
                }
            }else {
                System.out.println("nd1!=nd2");
            }
        }else System.out.println(nguoidung.getMAT_KHAU());

        return "redirect:/ttnguoidung?userid="+id+"&username="+usn;
    }

}
