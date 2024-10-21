package com.example.tnj.controller;

import mybatis.dao.AbledateMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;

@Controller
public class test {
    @Autowired
    AbledateMapper abd;
    @RequestMapping("/crdate")
    public String test(){
        for(int i = 0 ; i < 100 ; i++){
        abd.createdate(11, LocalDate.now().plusDays(i),"able");
        }
        return "forTest";
    }

}
