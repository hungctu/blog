package com.hungnguyen.blogweb.Service;

import com.hungnguyen.blogweb.Model.nguoi_dung;
import com.hungnguyen.blogweb.Repository.NguoiDungRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class CustomerUserdetailServiece implements UserDetailsService, UserDetails {
    @Autowired
    NguoiDungRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        nguoi_dung nd = repository.findbyusername(username);
        if(nd!=null){
            List<GrantedAuthority> list = new ArrayList<>();
            GrantedAuthority authority = new SimpleGrantedAuthority("user");
            list.add(authority);

            UserDetails userDetails = new User(nd.getTEN_DANG_NHAP(),nd.getMAT_KHAU(),list);
            return userDetails;
        }
        return null;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
