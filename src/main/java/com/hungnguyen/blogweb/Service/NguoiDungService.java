package com.hungnguyen.blogweb.Service;

import com.hungnguyen.blogweb.Model.nguoi_dung;
import com.hungnguyen.blogweb.Repository.NguoiDungRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Pattern;

@Service
public class NguoiDungService {

    @Autowired
    NguoiDungRepository repository;

    public nguoi_dung findbyid(int id){
        return  repository.findbyid(id);
    }

    public nguoi_dung findbyusername(String username){
        return repository.findbyusername(username);
    }

    public nguoi_dung findbyemail(String email){
        return repository.findbyemail(email);
    }
    public nguoi_dung findnd(int id,String usn){return repository.findnd(id, usn);}

    public nguoi_dung findnduap(String usn,String pass){return  repository.findnduap(usn, pass);}

    public List<nguoi_dung> findall(){return repository.findAll();}

    public void save(nguoi_dung nguoiDung){
        repository.save(nguoiDung);
    }

    public boolean patternMatches(String emailAddress) {
        String regexPattern = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
        return Pattern.compile(regexPattern)
                .matcher(emailAddress)
                .matches();
    }
}
