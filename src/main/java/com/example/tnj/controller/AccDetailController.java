package com.example.tnj.controller;

import com.example.tnj.domain.AccDetailDTO;
import com.example.tnj.domain.AccVO;
import mybatis.dao.AccDetailMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class AccDetailController {
    @Autowired
    AccDetailMapper dao;
    AccVO ac;

    @GetMapping("/accDetail")
    public String accDetail(Model model, int accomNum) {
        List<AccDetailDTO> list= dao.listAll(11);
        String revCnt=dao.cntReview(11);
        double revRate=dao.revRating(11);

        model.addAttribute("revCnt",revCnt);
        model.addAttribute("revRate",revRate);
        model.addAttribute("list",list);

        return "accDetail";
    }

    @PostMapping("/accDetail/reserv")
    public String reservBtn(@RequestParam("checkin") String checkin, @RequestParam("checkout") String checkout,
                            @RequestParam("adultCnt") int adultCnt, @RequestParam("kidCnt") int kidCnt, Model model,
                            @ModelAttribute AccVO ac) {

        // 예약 처리 로직
        System.out.println("체크인 날짜: " + checkin);
        System.out.println("체크아웃 날짜: " + checkout);
        System.out.println("성인 수: " + adultCnt);
        System.out.println("어린이 수: " + kidCnt);

        // 모델에 예약 정보 추가
        model.addAttribute("checkin", checkin);
        model.addAttribute("checkout", checkout);
        model.addAttribute("adultCnt", adultCnt);
        model.addAttribute("kidCnt", kidCnt);
        AccVO aVO = dao.acinfo(11);
        model.addAttribute("aVO",aVO);
        return "reservation";
    }

}
