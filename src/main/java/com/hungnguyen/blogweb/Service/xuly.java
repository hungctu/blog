package com.hungnguyen.blogweb.Service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class xuly {
    public static int itemperpage=5;

    public List<String> xulychuoi(String chuoi){
        List<String> l = new ArrayList<>();

        while(chuoi.indexOf(";")!=-1) {
            l.add(chuoi.substring(0, chuoi.indexOf(";")).trim());
            chuoi = chuoi.substring(chuoi.indexOf(";") + 1);
        }
        l.add(chuoi.trim());
        return l;
    }

    public int sopage(int sodulieu,int soitempage){
        int sopage = 1;
        if(sodulieu<soitempage){

        }
        else if(sodulieu%soitempage!=0){
            sopage=sodulieu/soitempage+1;
        }
        else sopage = sodulieu/soitempage;
        return sopage;
    }
    public List<Integer> page(int sodulieu,int soitempage){
        List<Integer> p = new ArrayList<Integer>();
        for(int i=0 ;i<sopage(sodulieu,soitempage); i++){
            p.add(i+1);
        }
        return p;
    }
}
