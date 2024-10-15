package com.example.tnj.controller;

import com.example.tnj.domain.PageDTO;
import com.example.tnj.domain.ReplyDTO;
import com.example.tnj.domain.ReplyPageDTO;
import com.example.tnj.domain.ReplyRatingVO;
import mybatis.dao.ReplyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        List<ReplyDTO> dto = replyMapper.listReply(accomNum);
        model.addAttribute("replyRate",vo);
        model.addAttribute("list",dto);
        return "replyView";
    }




    @PostMapping(value = "/page/{page}/{accomNum}",produces="application/json; charset=utf-8")
    @ResponseBody
    public ReplyPageDTO pagenation(@PathVariable int page,
                                     @PathVariable int accomNum){

        int total = replyMapper.amountReivew(accomNum);
        PageDTO pdto = new PageDTO((page-1)*4,page*4,page,total);
        List<ReplyDTO> dto = replyMapper.pagenation(pdto,accomNum);




        ReplyPageDTO rpdto = new ReplyPageDTO(dto,pdto);
        return rpdto;
    }
}
