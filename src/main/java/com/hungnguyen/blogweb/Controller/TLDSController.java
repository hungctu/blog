package com.hungnguyen.blogweb.Controller;

import com.hungnguyen.blogweb.Repository.TLDSRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class TLDSController {

    @Autowired
    TLDSRepository repository;

}
