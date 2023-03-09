package com.hungnguyen.blogweb.Service;

import com.hungnguyen.blogweb.Model.cd_ds;
import com.hungnguyen.blogweb.Repository.CDDSRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CDDSService {
    @Autowired
    CDDSRepository repository;
    public void savecdds(cd_ds cdDs){
        repository.save(cdDs);
    }

    public void insertcdds(int cdid,int dsid){
        repository.insertcdds(cdid, dsid);
    }

    public List<Integer> findcddsidbydsid(int id){
        return repository.findidcddstheodsid(id);
    }

    public cd_ds findcdds(int dsid,int cdid){
        return repository.findcdds(dsid,cdid);
    }

    public void deletecdds(cd_ds cdDs){
        repository.delete(cdDs);
    }
}

