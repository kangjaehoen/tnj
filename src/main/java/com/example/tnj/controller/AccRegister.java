package com.example.tnj.controller;

import com.example.tnj.domain.AccProperty;
import mybatis.dao.Accomodation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartRequest;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class AccRegister {
    @Autowired
    Accomodation acmd;
    @RequestMapping("accUpload")
    public ModelAndView accupload(@ModelAttribute AccProperty ap, String address1, String detailAddress,
                                  @RequestParam(required = false) MultipartRequest mreq) {
        ap.setAddress(address1+" "+detailAddress);
        ap.setOnSale(1);
        acmd.accinsert(ap);

        ModelAndView mav = new ModelAndView();
        //List<MultipartFile> list = mreq.getFiles("imageUpload");
        //String path = "c:/uploadtest/multi";

        mav.setViewName("/forTest");
        return mav;
    }
    @RequestMapping("myacclist")
    public ModelAndView myacclist(String id){
        List<AccProperty>mylist=acmd.listminel(id);
        StringBuffer bf;

        ModelAndView mav = new ModelAndView();
        mav.setViewName("/forTest");
        return mav;
    }

}
