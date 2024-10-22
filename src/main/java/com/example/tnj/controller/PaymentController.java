package com.example.tnj.controller;

import com.example.tnj.domain.*;
import mybatis.dao.PaymentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.beans.Transient;
import java.util.ArrayList;
import java.util.List;

@Controller
public class PaymentController {
    @Autowired
    PaymentMapper dao;


    @GetMapping("/payment")
    public String payment(Model model, @ModelAttribute AccVO ac,
                          @RequestParam("accomNum") int accomNum,
                          String impUid, PayResAccomVO payResAccomVO){

        int resNum= dao.selectresNum(accomNum);
        List<PaymentDTO> list= dao.listAll(resNum);
        model.addAttribute("list",list);

        payResAccomVO =dao.payReservAccomInfo(impUid);

        return "payment";
    }



    @PostMapping("/reservation/payment")
    public String insertPay(@RequestBody PaymentDTO pDTO, @ModelAttribute ReservationDTO rDTO,
                            @RequestParam("accomNum") int accomNum){
        try{
            int resNum= dao.selectresNum(accomNum);

            pDTO.setResNum(resNum);
            pDTO.setAccomNum(accomNum);
            System.out.println("Received PaymentDTO: " + pDTO);
            dao.insertPay(pDTO);

            return "payment";
        }catch (Exception e){
            e.printStackTrace();
            return "redirect:/";
        }


    }


    @GetMapping(value ="/payResAccomInfo/{impUid}")
    @ResponseBody
    public PayResAccomVO payResAccomInfo(@PathVariable String impUid){
       PayResAccomVO payResAccomVO = dao.payReservAccomInfo(impUid);
        return payResAccomVO;
    }


}
