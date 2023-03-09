package com.hungnguyen.blogweb.Service;

import com.hungnguyen.blogweb.Model.danh_gia;
import com.hungnguyen.blogweb.Repository.DanhGiaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DanhGiaService {

    @Autowired
    DanhGiaRepository danhGiaRepository;

    public int luotdg(int dsid,int loai){
        return danhGiaRepository.luotdanhgia(dsid, loai);
    }

    public int tongluotdg(int userid,int loai){
        return danhGiaRepository.tongluotdanhgia(userid,loai);
    }

    public danh_gia finddg(int dsid,int uid){
        return danhGiaRepository.finddanhgia(dsid, uid);
    }

    public void save(danh_gia danhGia){
        danhGiaRepository.save(danhGia);
    }
}
