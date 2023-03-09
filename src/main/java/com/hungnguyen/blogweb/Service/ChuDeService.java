package com.hungnguyen.blogweb.Service;

import com.hungnguyen.blogweb.Model.chu_de;
import com.hungnguyen.blogweb.Repository.ChuDeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChuDeService {

    @Autowired
    ChuDeRepository repository;

    public List<chu_de> findallCD(){
        return repository.findAll();
    }

    public chu_de findCDbyid(int id){
        return repository.findbyid(id);
    }
    public chu_de findCDbyname(String name){
        return repository.findbyname(name);
    }
    public void saveCD(chu_de chuDe){
        repository.save(chuDe);
    }
}
