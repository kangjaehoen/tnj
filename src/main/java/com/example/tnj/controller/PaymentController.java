package com.example.tnj.controller;

import com.example.tnj.domain.PaymentDTO;
import com.example.tnj.domain.ReservationDTO;
import mybatis.dao.PaymentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class PaymentController {
    @Autowired
    PaymentMapper dao;

    @GetMapping("/payment")
    public String payment(){
        return "payment";
    }


   @PostMapping("/reservation/payment")
    public String insertPay(@RequestBody PaymentDTO pDTO, @ModelAttribute ReservationDTO rDTO,
                            @RequestParam("accomNum") int accomNum){
        try{
            int resNum= dao.selectresNum(accomNum);

//            int resNum = rDTO.getResNum();
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

}
