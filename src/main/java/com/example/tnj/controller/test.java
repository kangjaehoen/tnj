package com.example.tnj.controller;

import mybatis.dao.AbledateMapper;
import mybatis.dao.Testdata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;

@Controller
public class test {
    @Autowired
    AbledateMapper abd;
    @Autowired
     Testdata td;

    @RequestMapping("/crdate")
    public String test(){
        for(int i = 0 ; i < 100 ; i++){
        abd.createdate(11, LocalDate.now().plusDays(i),"able");
        }
        return "forTest";
    }
    @RequestMapping("/cpdate")
    public String test2(){
        for(int i = 0 ; i < 10 ; i++){
            td.insertpaydata(i+1000,"user"+i,LocalDate.now().plusDays(i),28,40000,"롯데월드");
        }
        return "forTest";
    }

}
