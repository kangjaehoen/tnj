package com.example.tnj.controller;


import com.example.tnj.domain.AccResDTO;
import com.example.tnj.domain.AccVO;
import com.example.tnj.domain.ResVO;
import jakarta.servlet.http.HttpSession;
import mybatis.dao.AccomMapper;
import mybatis.dao.ResMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
public class AccResController {
    @Autowired
    ResMapper rv;
    @Autowired
    AccomMapper acmd;

    @RequestMapping(value = "/rCheck", produces = "application/json; charset=utf-8")
    @ResponseBody
    public ModelAndView rCheckChange(HttpSession session, @RequestParam(defaultValue = "1") int year, @RequestParam(defaultValue = "1") int month) {
        String id = (String) session.getAttribute("id");
        ModelAndView mav = new ModelAndView();
        //이 날짜 구간의 데이터만 가져올거임
        LocalDate now;
        if (year == 1) {
            now = LocalDate.now();
            year = now.getYear();
            month = now.getMonthValue();
        } else {
            now = LocalDate.of(year, month, 1);
        }
        mav.addObject("year",year);
        mav.addObject("month",month);

        LocalDate sdate = now.withDayOfMonth(1);
        LocalDate startdate = sdate.minusDays(sdate.getDayOfWeek().getValue());
        LocalDate edate = now.withDayOfMonth(now.lengthOfMonth());
        LocalDate enddate = edate.plusDays(6 - edate.getDayOfWeek().getValue());

        mav.addObject("rchart", startdate.datesUntil(enddate.plusDays(1)).toList());
        List<ResVO> rsv = rv.reservationcheck(id, startdate, enddate);

        //AccVO와 ResVO를 합친 AccResDTO 만들었음.
        List<AccResDTO> arDTOs = new ArrayList<>();
        for (int i = 0; i < rsv.size(); i++) {
            AccResDTO ar = new AccResDTO();
            ResVO rv = rsv.get(i);
            AccVO ac = new AccVO();
            ac.setAccName(acmd.oneNameByAccomNum(rv.getAccomNum()));
            ar.setAcc(ac);
            ar.setRes(rv);
            arDTOs.add(ar);
        }
        mav.addObject("arDTOs", arDTOs);
        mav.setViewName("reservationCheck");
        return mav;
    }

}