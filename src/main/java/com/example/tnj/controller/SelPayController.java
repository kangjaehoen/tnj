package com.example.tnj.controller;

import com.example.tnj.domain.PayVO;
import jakarta.servlet.http.HttpSession;
import mybatis.dao.PayMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class SelPayController {
    @Autowired
    PayMapper pa;
    @RequestMapping("/pCheck")
    public ModelAndView paylist(HttpSession session){
        ModelAndView mav = new ModelAndView();
        List<PayVO>pv = pa.paycheck((String)session.getAttribute("id"));
        mav.addObject("pv",pv);
        mav.setViewName("payCheck");
        return mav;
    }
}
