package com.example.tnj.controller;

import com.example.tnj.domain.UserDTO;
import jakarta.servlet.http.HttpSession;
import mybatis.dao.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Controller
@SessionAttributes("user")
public class UserController {
    @Autowired
    private UserMapper userMapper;

    @PostMapping("/register")
    public String register(@ModelAttribute UserDTO user) {
        if (userMapper.registerUser(user) > 0) {
            return "redirect:/loginForm.html?registered=true";
        }
        return "redirect:/registerForm.html";
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(HttpSession session, @RequestParam String id, @RequestParam String pw) {
        UserDTO user = userMapper.loginUser(id, pw);
        Map<String, String> response = new HashMap<>();
        if (user != null && user.getPw().equals(pw)) {
            session.setAttribute("user", user);
            response.put("result", "success");
            return ResponseEntity.ok(response);
        }
        response.put("result", "fail");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @RequestMapping("/moveID")
    public String mfindId() {
        return "idFind";
    }

    @RequestMapping("/moveRegister")
    public String mRegister() {
        return "registerForm";
    }

    @RequestMapping("/moveLogin")
    public String mLogin() {
        return "loginForm";
    }

    @RequestMapping("/movePW")
    public String mfindPw() {
        return "pwFind";
    }

    @GetMapping("/checkId")
    public ResponseEntity<Map<String, Boolean>> checkId(@RequestParam String id) {
        Map<String, Boolean> response = new HashMap<>();
        if (userMapper.checkId(id) == 0) {
            response.put("available", true);
        } else {
            response.put("available", false);
        }
        return ResponseEntity.ok(response);
    }

@GetMapping("/checkEmail")
public String checkEmail(@RequestParam String email) {
    String orEm = userMapper.checkEmail(email);
    if (orEm.equals(email)) {
        return "중복";
    } else {
        return "사용 가능";
    }
}

@PostMapping("/findId")
public ResponseEntity<Map<String, String>> findId(@RequestParam String name, @RequestParam String birth, @RequestParam String email) {
    String id = userMapper.findId(name, birth, email);
    Map<String, String> response = new HashMap<>();
    if (id != null) {
        response.put("name", name);
        response.put("id", id);
        return ResponseEntity.ok(response);
    }
    response.put("error", "아이디를 찾을 수 없습니다.");
    return ResponseEntity.status(HttpStatus.OK).body(response);
}

@GetMapping("/showId")
public String showId(Model model, @RequestParam String id, @RequestParam String name){
        model.addAttribute("name", name);
        model.addAttribute("id", id);
        return "idShow";
}

@PostMapping("/changePassword")
public ResponseEntity<String> changePassword(@RequestParam String id, @RequestParam String newPassword) {
    int result = userMapper.updatePassword(id, newPassword);
    if (result > 0) {
        return ResponseEntity.ok("비밀번호를 성공적으로 변경하였습니다!");
    }
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("비밀번호 변경에 실패하셨습니다.");
}
}