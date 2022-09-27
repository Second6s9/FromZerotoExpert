package com.seconds.controller;

import com.seconds.entity.User;
import com.seconds.service.Login;
import com.seconds.utils.RegisterResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/FromZerotoExpert/login")
public class LoginController {
    @Autowired
    private Login loginService;


    @PostMapping("/user")
    @ResponseBody
    public RegisterResult login(User user, HttpServletRequest request, HttpServletResponse response){
        return loginService.checkUser(user, request, response);

    }
}
