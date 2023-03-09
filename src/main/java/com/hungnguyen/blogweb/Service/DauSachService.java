package com.hungnguyen.blogweb.Service;

import com.hungnguyen.blogweb.Model.dau_sach;
import com.hungnguyen.blogweb.Repository.DauSachRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DauSachService {

    @Autowired
    DauSachRepository repository;
    @Autowired
    ChapterService chapterservice;


    public List<dau_sach> findallds(){
        return repository.findalltt();
    }
    public List<dau_sach> findallpt(int bd,int kt){return repository.findallpt(bd, kt);}

    public dau_sach findbyid(int id){
        return repository.findbyid(id);
    }

    public dau_sach findbyname(String name){
        return repository.findbyname(name);
    }

    public List<dau_sach> timkiem(String timkiem){return repository.timkiem(timkiem);}
    public List<dau_sach> timkiempt(String timkiem,int bd,int kt){return repository.timkiempt(timkiem,bd,kt);}
    public List<dau_sach> findbytlid(int id){return repository.finddstheotl(id);}
    public List<dau_sach> findbytlidpt(int id,int bd,int kt){return repository.finddstheotlpt(id,bd,kt);}
    public List<dau_sach> findbycdid(int id){return repository.finddstheocd(id);}
    public List<dau_sach> findbycdidpt(int id,int bd,int kt){return repository.finddstheocdpt(id,bd,kt);}
    public void savedausach(dau_sach dauSach){
        repository.save(dauSach);
    }

    public List<dau_sach> sachnhieuluotxem(){
        return repository.finddsnhieuluotxem();
    }

    public int tongluotxem(int id){
        int tong =0;
        List<dau_sach> dauSachList= repository.finddstheond(id);
        for (dau_sach ds: dauSachList) {
            tong+= chapterservice.tongluotxem(ds.getID_SACH());
        }
        return tong;
    }
    public void capnhattrangthai(int id){
        dau_sach dauSach = repository.findbyid(id);
        int tt = 0;
        if(dauSach.getTRANG_THAI()==0){
            tt = 1;
        }else tt=0;
        dauSach.setTRANG_THAI(tt);
        repository.save(dauSach);
    }


}
