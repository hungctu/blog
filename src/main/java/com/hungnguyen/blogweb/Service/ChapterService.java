package com.hungnguyen.blogweb.Service;

import com.hungnguyen.blogweb.Model.chapter;
import com.hungnguyen.blogweb.Repository.ChapterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ChapterService {

    @Autowired
    ChapterRepository chapterRepository;
    public void savechap(chapter chapter){
            chapterRepository.save(chapter);
    }
    public void insertchap(int id, Date ngaydang,String noidung,String ten){
        chapterRepository.insertchapter(id, ngaydang, noidung, ten);
    }

    public List<chapter> findchaptertheodsid(int id){
        return chapterRepository.findchaptheodsid(id);
    }

    public chapter findchapbyid(int id){
        return chapterRepository.findchapterbyid(id);
    }

    public void tangluotxem(int id){
        chapter chapter = chapterRepository.findchapterbyid(id);
        int luotxem = chapter.getLUOT_XEM()+1;
        chapter.setLUOT_XEM(luotxem);
        chapterRepository.save(chapter);
    }

    public int tongluotxem(int id){
        int tong=0;
        List<chapter> list = chapterRepository.findchaptheodsid(id);
        for(chapter l : list){
            tong+= l.getLUOT_XEM();
        }
        return tong;
    }
}
