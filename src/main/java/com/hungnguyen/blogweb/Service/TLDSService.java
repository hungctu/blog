package com.hungnguyen.blogweb.Service;

import com.hungnguyen.blogweb.Model.tl_ds;
import com.hungnguyen.blogweb.Repository.TLDSRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TLDSService {
    @Autowired
    TLDSRepository repository;

    public void savetlds(tl_ds tlDs){
        repository.save(tlDs);
    }

    public void inserttlds(int tlid,int dsid){
        repository.inserttlds(tlid, dsid);
    }

    public List<Integer> findidtldsbydsid(int id){
        return  repository.findidtldstheodsid(id);
    }

    public tl_ds findtlds(int dsid,int tlid){
        return repository.findtlds(dsid, tlid);
    }

    public void deletetlds(tl_ds tlDs){
        repository.delete(tlDs);
    }
}
