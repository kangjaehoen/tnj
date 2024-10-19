package com.example.tnj.Controller;

import com.example.tnj.domain.AccVO;
import com.example.tnj.domain.ResVO;
import com.example.tnj.domain.ReservationDTO;
import jakarta.servlet.http.HttpServletResponse;
import mybatis.dao.ReservationMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ReservationController {
     @Autowired
     ReservationMapper dao;


    @PostMapping("/reservation")
    public String oneReserv(Model model, @RequestParam("accomNum") int accomNum){
        List<ReservationDTO> rDTO= dao.oneReserv(accomNum);
        model.addAttribute("rDTO",rDTO);

        int revCnt=dao.cntReview(accomNum);
        double revRate=dao.revRating(accomNum);
        String price=dao.accomPrice(accomNum);

        model.addAttribute("revCnt",revCnt);
        model.addAttribute("revRate",revRate);
        model.addAttribute("price",price);
        model.addAttribute("rDTO",rDTO);

        return "reservation";
    }

    //예약하기 버튼-> 예약페이지
    @PostMapping("/reservation/resInfo")
    public String reservBtn(@RequestParam("checkin") String checkin, @RequestParam("checkout") String checkout,
                            @RequestParam("adultCnt") int adultCnt, @RequestParam("kidCnt") int kidCnt, Model model,
                            @ModelAttribute AccVO ac, @ModelAttribute ResVO rVO,
                            @RequestParam("totalDays") int totalDays, @RequestParam("totalPayment") int totalPayment) {

        // 모델에 예약 정보 추가
        model.addAttribute("checkin", checkin);
        model.addAttribute("checkout", checkout);
        model.addAttribute("adultCnt", adultCnt);
        model.addAttribute("kidCnt", kidCnt);

        AccVO aVO = dao.accInfo(ac.getAccomNum());
        //체크인 시간 형식변환
        LocalTime lt = aVO.getChkin_Time();
        DateTimeFormatter format= DateTimeFormatter.ofPattern("h:mm");
        String fmChkTime= lt.format(format);

        int revCnt=dao.cntReview(ac.getAccomNum());
        double revRate=dao.revRating(ac.getAccomNum());



        model.addAttribute("chkTime",fmChkTime);
        model.addAttribute("totalDays", totalDays);
        model.addAttribute("totalPayment", totalPayment);
        model.addAttribute("aVO",aVO);
        model.addAttribute("rVO",rVO);
        model.addAttribute("revCnt",revCnt);
        model.addAttribute("revRate",revRate);

        return "reservation";
    }

    @ResponseBody
    public void insertRes(@ModelAttribute ReservationDTO rDTO ,HttpServletResponse response){

        try{
            dao.insertRes(rDTO);
            response.setStatus(HttpServletResponse.SC_OK);

        }catch (Exception e){
            e.printStackTrace();

            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }

    }

}
