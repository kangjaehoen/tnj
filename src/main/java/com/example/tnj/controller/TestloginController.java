package com.example.tnj.controller;

import jakarta.servlet.http.HttpSession;
import mybatis.dao.AccomMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TestloginController {

    @RequestMapping(value = "/sellerPage", produces = "application/json; charset=utf-8")
    @ResponseBody
    public ModelAndView sellpage(HttpSession session, String id) {
        ModelAndView mav = new ModelAndView();
        session.setAttribute("id", id);
        mav.addObject("id", session.getAttribute("id"));
        mav.setViewName("forward:/myacclist");
        return mav;
    }
}