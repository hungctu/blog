package com.hungnguyen.blogweb.Service;

import com.hungnguyen.blogweb.Model.the_loai;
import com.hungnguyen.blogweb.Repository.TheLoaiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TheLoaiService {

    @Autowired
    TheLoaiRepository repository;
    public List<the_loai> findallTL(){
        return  repository.findAll();
    }

    public the_loai findTLbyid(int id){
        return repository.findbyid(id);
    }
    public the_loai findTLbyname(String name){
        return repository.findbyname(name);
    }
    public void saveTL(the_loai theLoai){
        repository.save(theLoai);
    }
}
