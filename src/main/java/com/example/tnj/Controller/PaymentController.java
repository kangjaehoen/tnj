package com.example.tnj.controller;

import com.example.tnj.domain.AccVO;
import com.example.tnj.domain.PayVO;
import com.example.tnj.domain.PaymentDTO;
import com.example.tnj.domain.ReservationDTO;
import jakarta.servlet.http.HttpSession;
import mybatis.dao.PaymentMapper;
import org.codehaus.groovy.runtime.InvokerHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


@Controller
public class PaymentController {
    @Autowired
    PaymentMapper dao;

    @GetMapping("/payment")
    public String payment(Model model, @ModelAttribute AccVO ac, HttpSession session,
                          RedirectAttributes redirectAttributes, @ModelAttribute PayVO pVO, String year, String month) {
        String id = (String) session.getAttribute("id");

        if(id == null){
            redirectAttributes.addFlashAttribute("errorMessage", "로그인 한 회원만 접근가능한 페이지 입니다.");
        }
        List<PaymentDTO> list = dao.payList(id, year, month);

        model.addAttribute("list", list);
        return "payment";
    }



    @PostMapping("/reservation/payment")
    public String insertPay(@RequestBody PaymentDTO pDTO,
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
@PostMapping("/payment/cancel")
@ResponseBody
    public ResponseEntity<String> cancelPayment(@RequestBody Map<String, Object> requestData,
                                                @ModelAttribute PayVO pVOs,Model model) {
        // JSON 데이터를 Map으로 받음
        List<String> impUids = (List<String>) requestData.get("impUids");
       /* PayVO pVO= dao.payInfo(accomNum);*/

        // 각 impUid에 대해 결제 취소 처리 (DB에서 결제 상태 변경 또는 삭제)
        for (String impUid : impUids) {
            dao.cancelPay(impUid); // DAO 호출하여 결제 취소 처리
        }
        /*model.addAttribute("pVO", pVO);*/
        return ResponseEntity.ok("결제가 성공적으로 취소되었습니다.");
    }

}
