package com.hungnguyen.blogweb.Service;

import com.hungnguyen.blogweb.Model.binh_luan;
import com.hungnguyen.blogweb.Repository.BinhLuanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BinhLuanService {
    @Autowired
    BinhLuanRepository repository;
    public List<binh_luan> findbychapid(int id){
        return repository.findbychapid(id);
    }

    public void save(binh_luan binhLuan){repository.save(binhLuan);}
}
