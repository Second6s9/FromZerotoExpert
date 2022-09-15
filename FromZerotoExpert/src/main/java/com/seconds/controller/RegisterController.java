package com.seconds.controller;

import com.seconds.entity.User;
import com.seconds.service.Register;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;



@Controller
@RequestMapping("/FromZerotoExpert")
public class RegisterController {
    @Autowired
    private Register register;

    @RequestMapping("/register")
    public String register(){
        return "register.html";
    }

    @RequestMapping("/login")
    public String login(){
        return "login.html";
    }

    @PostMapping("/save")
    public String save(User user){
        user.setSalt(user.getUsername());
        register.regist(user);

        return "register_success.html";
    }
}
