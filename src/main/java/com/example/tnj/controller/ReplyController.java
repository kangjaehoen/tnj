package com.example.tnj.controller;

import com.example.tnj.domain.ReplyDTO;
import com.example.tnj.domain.ReplyRatingVO;
import mybatis.dao.ReplyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ReplyController {
    @Autowired
    private ReplyMapper replyMapper;

    @PostMapping("/reviewInsert")
    public String reviewInsert(ReplyDTO dto){
        replyMapper.insertReply(dto);
        return "redirect:";
    }
    @RequestMapping("/review")
    public String reviewList(int accomNum, Model model){
        ReplyRatingVO vo = replyMapper.rating(accomNum);
        model.addAttribute("replyRate",vo);
        return "replyView";
    }
}
