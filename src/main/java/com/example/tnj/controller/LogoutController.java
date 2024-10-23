package com.example.tnj.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LogoutController {

    @GetMapping("/logout")
    public String logout(HttpSession session){
      String id = (String) session.getAttribute("id");
        System.out.println(id);
        if(id != null && !id.equals("")){
            session.invalidate();
        }
        return "redirect:/";
    }

}
