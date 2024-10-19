package com.example.tnj.Controller;

import com.example.tnj.domain.AccDetailDTO;
import com.example.tnj.domain.AccVO;
import com.example.tnj.domain.ResVO;
import com.example.tnj.domain.ReservationDTO;
import mybatis.dao.AccDetailMapper;
import mybatis.dao.ReplyMapper;
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

    @Autowired
    private ReplyMapper replyMapper;

    @RequestMapping("/accDetail")
    public String accDetail(Model model, @ModelAttribute AccVO ac, @RequestParam("accomNum") int accomNum) {
        List<AccDetailDTO> list= dao.listAll(accomNum);
        String revCnt=dao.cntReview(accomNum);
       // double revRate=dao.revRating(accomNum);
        AccVO aVO=dao.acinfo(accomNum);

        model.addAttribute("revCnt",revCnt);
         // model.addAttribute("revRate",revRate);
        model.addAttribute("list",list);
        model.addAttribute("aVO",aVO);

        model.addAttribute("replyRate",replyMapper.rating(accomNum));
        model.addAttribute("reviewList",replyMapper.listReply(accomNum));

        return "accDetail";
    }


}
