package com.example.tnj.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PaymentController {
    @GetMapping("/payment")
    public String payment(){
        return "payment";
    }

   /*
   @PostMapping("/")
    public String payBtn(){

    }
    */
}
