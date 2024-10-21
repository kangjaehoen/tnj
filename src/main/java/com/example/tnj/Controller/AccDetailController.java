package com.example.tnj.controller;

import com.example.tnj.domain.AccDetailDTO;
import com.example.tnj.domain.AccVO;
import com.example.tnj.domain.ResVO;
import com.example.tnj.domain.ReservationDTO;
import mybatis.dao.AccDetailMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
public class AccDetailController {
    @Autowired
    AccDetailMapper dao;
    AccVO ac;

    @RequestMapping("/accDetail")
    public String accDetail(Model model, @ModelAttribute AccVO ac, @RequestParam("accomNum") int accomNum) {
        List<AccDetailDTO> list= dao.listAll(accomNum);
        String revCnt=dao.cntReview(accomNum);
        String revRate=dao.revRating(accomNum);
        AccVO aVO=dao.acinfo(accomNum);

        model.addAttribute("revCnt",revCnt);
        model.addAttribute("revRate",revRate);
        model.addAttribute("list",list);
        model.addAttribute("aVO",aVO);

        return "accDetail";
    }


}
