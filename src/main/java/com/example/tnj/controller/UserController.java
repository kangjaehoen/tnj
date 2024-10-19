package com.example.tnj.controller;

import com.example.tnj.domain.UserDTO;
import mybatis.dao.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Controller
public class UserController {
    @Autowired
    private UserMapper userMapper;

    @PostMapping("/register")
    public String register(@ModelAttribute UserDTO user) {
        if (userMapper.registerUser(user) > 0) {
            return "";
        }
        return "회원가입에 실패하셨습니다";
    }

    @PostMapping("/logIn")
    public ResponseEntity<Map<String, String>> login(@RequestParam String id, @RequestParam String pw) {
        UserDTO user = userMapper.loginUser(id, pw);
        Map<String, String> response = new HashMap<>();
        if (user != null && user.getPw().equals(pw)) {
            response.put("result", "성공~");
            return ResponseEntity.ok(response);
        }
        response.put("result", "ㅇㄴ");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
    }

//    @RequestMapping("/moveID")
//    public String mfindId() {
//        return "idFind";
//    }

    @RequestMapping("/moveRegister")
    public String mRegister() {
        return "registerForm";
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

// Find ID
@PostMapping("/findId")
public ResponseEntity<String> findId(@RequestParam String name, @RequestParam String email) {
    String id = userMapper.findId(name, email);
    if (id != null) {
        return ResponseEntity.ok("당신의 아이디는 " + id + "입니다.");
    }
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("아이디를 찾을 수 없습니다.");
}

// Find Password
@PostMapping("/findPw")
public ResponseEntity<String> findPassword(@RequestParam String id, @RequestParam String email) {
    String pw = userMapper.findPassword(id, email);
    if (pw != null) {
        return ResponseEntity.ok("비밀번호는 : " + pw + "입니다.");
    }
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("비밀번호를 찾을 수 없습니다.");
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