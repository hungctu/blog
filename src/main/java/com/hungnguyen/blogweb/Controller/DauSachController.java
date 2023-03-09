package com.hungnguyen.blogweb.Controller;

import com.hungnguyen.blogweb.Model.*;
import com.hungnguyen.blogweb.Service.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import static com.hungnguyen.blogweb.Service.xuly.itemperpage;

@Controller
public class DauSachController {

    @Autowired
    NguoiDungService nd;
    @Autowired
    TheLoaiService theLoaiService;
    @Autowired
    ChuDeService chuDeService;
    @Autowired
    DauSachService dauSachService;
    @Autowired
    xuly  xuly;
    @Autowired
    CDDSService cddsService;
    @Autowired
    TLDSService tldsService;
    @Autowired
    ChapterService chapterService;
    @Autowired
    ImageService imageService;
    @Autowired
    DanhGiaService danhGiaService;


    @GetMapping("/dausach")
    public String dausach(Model model,@ModelAttribute("dsid") int id){

        dau_sach dauSach = dauSachService.findbyid(id);
        model.addAttribute("ds",dauSach);

        int tong = chapterService.tongluotxem(id);
        model.addAttribute("tong",tong);

        int like = danhGiaService.luotdg(id,0);
        int dislike = danhGiaService.luotdg(id,1);
        model.addAttribute("like",like);
        model.addAttribute("dislike",dislike);

        List<dau_sach> hot = dauSachService.sachnhieuluotxem();
        model.addAttribute("hot",hot);

        nguoi_dung nguoiDung = new nguoi_dung();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication!=null){
            nguoiDung = nd.findbyusername(authentication.getName());
            model.addAttribute("nd",nguoiDung);
        }

        return "Dausach";
    }

    @GetMapping("/dausach/like")
    public String like(@ModelAttribute("dsid") int id,@RequestParam(value="userid",defaultValue = "0") int userid){
        if(userid==0){
            return  "redirect:/login";
        }
        danh_gia dg = danhGiaService.finddg(id, userid);
        if(dg==null){
            dg = new danh_gia();
            dg.setID_SACH(id);
            dg.setID_NGUOI_DUNG(userid);
            danhGiaService.save(dg);
        }
        else if(dg.getLOAI_DG()==2||dg.getLOAI_DG()==1){
            dg.setLOAI_DG(0);
            danhGiaService.save(dg);
        }else {
            dg.setLOAI_DG(2);
            danhGiaService.save(dg);
        }
        return "redirect:/dausach?dsid="+id;
    }
    @GetMapping("/dausach/dislike")
    public String dislike(@ModelAttribute("dsid") int id,@RequestParam(value="userid",defaultValue = "0") int userid){
        if(userid==0){
            return  "redirect:/login";
        }
        danh_gia dg = danhGiaService.finddg(id, userid);
        if(dg==null){
            dg = new danh_gia();
            dg.setID_SACH(id);
            dg.setID_NGUOI_DUNG(userid);
            dg.setLOAI_DG(1);
            danhGiaService.save(dg);
        }
        else if(dg.getLOAI_DG()==2||dg.getLOAI_DG()==0){
            dg.setLOAI_DG(1);
            danhGiaService.save(dg);
        }else {
            dg.setLOAI_DG(2);
            danhGiaService.save(dg);
        }
        return "redirect:/dausach?dsid="+id;
    }

    @GetMapping("/timkiem")
    public String timkiem(Model model,@ModelAttribute("timkiem") String timkiem,@RequestParam(value = "page",defaultValue = "1") int page){
        int size = dauSachService.timkiem(timkiem).size();
        int currentpage=page;
        int bd = 0;
        if(currentpage==1){

        }else {
            bd = (currentpage-1)* itemperpage;
        }
        List<dau_sach> dauSachList = dauSachService.timkiempt(timkiem,bd,itemperpage);
        model.addAttribute("key",timkiem);

        model.addAttribute("crpage",currentpage);

        model.addAttribute("danhsachDS",dauSachList);

        List<Integer> p = xuly.page(size,itemperpage);
        model.addAttribute("page",p);
        nguoi_dung nguoiDung = new nguoi_dung();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication!=null){
            nguoiDung = nd.findbyusername(authentication.getName());
            model.addAttribute("nd",nguoiDung);
        }
        return "Timkiem";
    }

    @GetMapping("/dausach_tacgia")
    public String ds_tg(Model model,@ModelAttribute("userid") int id){
        nguoi_dung tacgia =  nd.findbyid(id);
        model.addAttribute("tacgia", tacgia);
        int tonglx = dauSachService.tongluotxem(id);
        model.addAttribute("tong",tonglx);

        nguoi_dung nguoidung = new nguoi_dung();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication!=null){
            nguoidung = nd.findbyusername(authentication.getName());
            model.addAttribute("nd",nguoidung);
        }
        return "tacgia";
    }


    @GetMapping("/chitietds")
    public String chitietds(Model model,@ModelAttribute("dsid") int id,@ModelAttribute("userid") int uid,@ModelAttribute("username") String usn){
        dau_sach dauSach = dauSachService.findbyid(id);
        model.addAttribute("ds",dauSach);

        int tong = chapterService.tongluotxem(id);
        model.addAttribute("tong",tong);

        nguoi_dung nguoiDung =  nd.findnd(uid,usn);
        model.addAttribute("nguoidung", nguoiDung);

        return "chitietds";
    }

    @GetMapping("/dsdausach/capnhattt")
    public String capnhattt(@ModelAttribute("dsid") int id,@ModelAttribute("userid") int uid,@ModelAttribute("username") String usn){
        dauSachService.capnhattrangthai(id);

        return "redirect:/dsdausach?userid="+uid+"&username="+usn;

    }

    @GetMapping("/themdausach")
    public String themtacpham(Model model, @ModelAttribute("userid") int id,@ModelAttribute("username") String usn){

        nguoi_dung nguoiDung =  nd.findnd(id,usn);
        model.addAttribute("nguoidung", nguoiDung);

        List<the_loai> theLoais = theLoaiService.findallTL();
        model.addAttribute("theloailist",theLoais);

        List<chu_de> chuDes = chuDeService.findallCD();
        model.addAttribute("chudelist",chuDes);

        return "themtacpham";
    }

    @Transactional
    @PostMapping("/themdausach/save")
    public String save(dau_sach dauSach, @RequestParam("hinhsach") MultipartFile image,
                       @RequestParam("chu_de") List<Integer> cdlist
                     ,@ModelAttribute("chudekhac") String cdkhac,@RequestParam("theloailist") List<Integer> tllist
                     ,@ModelAttribute("theloaikhac") String tlkhac, chapter chap
                     ,@ModelAttribute("usn") String usn) throws IOException {
        int uid =dauSach.getID_NGUOI_DUNG();
        if(dauSachService.findbyname(dauSach.getTEN_SACH())==null && !chap.getTEN_CHAPTER().isEmpty() && !chap.getNOI_DUNG().isEmpty()){
            if(dauSach.getTOM_TAT().equals("")) dauSach.setTOM_TAT("Không có tóm tắt");
            dauSach.setHINH_SACH(image.getOriginalFilename());
            dauSach.setTRANG_THAI(1);
            dauSachService.savedausach(dauSach);
            imageService.saveImage(image);
            int dsid =dauSachService.findbyname(dauSach.getTEN_SACH()).getID_SACH();
            for(int id : cdlist){
                cddsService.insertcdds(id,dsid);
            }
            for(int id : tllist){
                tldsService.inserttlds(id,dsid);
            }
            if(!cdkhac.equals("")&&!cdkhac.equals(" ")&&!tlkhac.isEmpty()){
                List<String> cdk = xuly.xulychuoi(cdkhac);
                for(String name : cdk){
                    if(chuDeService.findCDbyname(name)==null){
                        chu_de chuDe = new chu_de();
                        chuDe.setTEN_CD(name);
                        chuDeService.saveCD(chuDe);
                    }
    //                  cd_ds cdDs = new cd_ds();
    //                  cdDs.setID_SACH(dsid);
                    int cdid = chuDeService.findCDbyname(name).getID_CD();
                    cddsService.insertcdds(cdid,dsid);
                        //cdDs.setID_CD(chuDeService.findCDbyname(name).getID_CD());
                        //cddsService.savecdds(cdDs);
                }
            }
            if(!tlkhac.equals("")&&!tlkhac.equals(" ")&&!tlkhac.isEmpty()) {
                List<String> tlk = xuly.xulychuoi(tlkhac);
                for (String name : tlk) {
                    if (theLoaiService.findTLbyname(name) == null) {
                        the_loai theLoai = new the_loai();
                        theLoai.setTEN_TL(name);
                        theLoaiService.saveTL(theLoai);
                    }
//                    tl_ds tlDs = new tl_ds();
//                    tlDs.setID_SACH(dsid);
                    int tlid = theLoaiService.findTLbyname(name).getID_TL();
                    tldsService.inserttlds(tlid, dsid);
                    //tlDs.setID_TL(theLoaiService.findTLbyname(name).getID_TL());
                    //tldsService.savetlds(tlDs);

                }
            }
                    /*chap.setID_SACH(dauSachService.findbyname(dauSach.getTEN_SACH()).getID_SACH());
                    chap.setNGAY_DANG(new Date());*/
            chapterService.insertchap(dsid,new Date(),chap.getNOI_DUNG(),chap.getTEN_CHAPTER());
            return "redirect:/dsdausach?userid="+uid+"&username="+usn;
        }
        else return "redirect:/themdausach?userid="+uid+"&username="+usn;
    }

    @GetMapping("/suadausach")
    public String suadausach(Model model,@ModelAttribute("dsid") int id,@ModelAttribute("userid") int uid,@ModelAttribute("username") String usn){
        dau_sach dauSach = dauSachService.findbyid(id);
        model.addAttribute("ds", dauSach);

        List<the_loai> tllist = theLoaiService.findallTL();
        model.addAttribute("tllist",tllist);

        List<chu_de> cdlist = chuDeService.findallCD();
        model.addAttribute("cdlist",cdlist);

        List<Integer> idtl = tldsService.findidtldsbydsid(id);
        model.addAttribute("tlids",idtl);

        List<Integer> idcd = cddsService.findcddsidbydsid(id);
        model.addAttribute("cdids",idcd);

        nguoi_dung nguoiDung =  nd.findnd(uid,usn);
        model.addAttribute("nguoidung", nguoiDung);

        return "suadausach";
    }

    @Transactional
    @PostMapping("/suadausach/save")
    public String updateds(dau_sach dauSach,@RequestParam("hinhsach") MultipartFile image,
                           @RequestParam("chu_de") List<Integer> cdlist,@ModelAttribute("chudekhac") String cdkhac,
                           @RequestParam("theloailist") List<Integer> tllist,@ModelAttribute("theloaikhac") String tlkhac,
                           @ModelAttribute("userid") int uid,@ModelAttribute("username") String usn) throws IOException {
        int dsid =dauSach.getID_SACH();
        dau_sach dausach = dauSachService.findbyid(dsid);
        dausach.setTEN_SACH(dauSach.getTEN_SACH());
        dausach.setTOM_TAT(dauSach.getTOM_TAT());
        if(!image.isEmpty()){
            imageService.delete(dausach.getHINH_SACH());
            dausach.setHINH_SACH(image.getOriginalFilename().trim());
            imageService.saveImage(image);
        }
        dauSachService.savedausach(dausach);

        List<Integer> idtl = tldsService.findidtldsbydsid(dsid);
        for(int item : idtl){
            if(tllist.indexOf(item)==-1){
                tl_ds tlDs= tldsService.findtlds(dsid,item);
                tldsService.deletetlds(tlDs);
            }
        }
        for(int id : tllist){
            if(tldsService.findtlds(dsid,id)==null){
                tldsService.inserttlds(id,dsid);
            }
        }
        if(!tlkhac.equals("")&&!tlkhac.equals(" ")&&!tlkhac.isEmpty()) {
            List<String> tlk = xuly.xulychuoi(tlkhac);
            for (String name : tlk) {
                if (theLoaiService.findTLbyname(name) == null) {
                    the_loai theLoai = new the_loai();
                    theLoai.setTEN_TL(name);
                    theLoaiService.saveTL(theLoai);
                }
                int tlid = theLoaiService.findTLbyname(name).getID_TL();
                tldsService.inserttlds(tlid, dsid);

            }
        }

        List<Integer> idcd = cddsService.findcddsidbydsid(dsid);
        for(int item : idcd){
            if(cdlist.indexOf(item)==-1){
                cddsService.deletecdds(cddsService.findcdds(dsid,item));
            }
        }
        for(int item: cdlist){
            if(cddsService.findcdds(dsid,item)==null){
                cddsService.insertcdds(item,dsid);
            }
        }
        if(!cdkhac.equals("")&&!cdkhac.equals(" ")&&!tlkhac.isEmpty()){
            List<String> cdk = xuly.xulychuoi(cdkhac);
            for(String name : cdk){
                if(chuDeService.findCDbyname(name)==null){
                    chu_de chuDe = new chu_de();
                    chuDe.setTEN_CD(name);
                    chuDeService.saveCD(chuDe);
                }
                int cdid = chuDeService.findCDbyname(name).getID_CD();
                cddsService.insertcdds(cdid,dsid);

            }
        }

        return "redirect:/dsdausach?userid="+uid+"&username="+usn;
    }
}
