package com.example.tnj.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HeaderController {

    @GetMapping("/payment")
    public String payment(){

        return "payment";
    }
}
