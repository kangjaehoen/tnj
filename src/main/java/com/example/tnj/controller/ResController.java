package com.example.tnj.controller;

import jakarta.servlet.http.HttpSession;
import mybatis.dao.AbledateMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;

@Controller
public class ResController {
    // 2차 프로젝트에서 할꺼임
    @Autowired
    AbledateMapper ab;

    @RequestMapping("/accbook")
    public ModelAndView accbook(String id, int accomNum, LocalDate chkin_Date, LocalDate chkout_Date){
        ModelAndView mav = new ModelAndView();
        ab.book(id,accomNum, chkin_Date,chkout_Date);

        mav.setViewName("redirect:/maintest.html");
        return mav;
    }
}
