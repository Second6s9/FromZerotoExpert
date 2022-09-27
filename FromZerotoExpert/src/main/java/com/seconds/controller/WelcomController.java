package com.seconds.controller;

import com.seconds.entity.User;
import com.seconds.utils.SessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class WelcomController {


    @RequestMapping("/FromZerotoExpert")
    public String welcom(HttpServletRequest request, HttpServletResponse response){
        return mainPage(request, response);

    }


    @RequestMapping("/FromZerotoExpert/main")
    public String mainPage(HttpServletRequest request, HttpServletResponse response){
        String username = (String) request.getSession().getAttribute("username");
        Cookie cookie = new Cookie("username", username);
        response.addCookie(cookie);
        return "main.html";
    }

    @RequestMapping("/FromZerotoExpert/redirect_login")
    public String jumpToLogin(){
        return "redirect_login.html";
    }

    @RequestMapping("/redirect_login")
    public String jumpToLogin2(){
        return "redirect_login.html";
    }

    @RequestMapping("/register")
    public String register(){
        return "register.html";
    }

    @RequestMapping("/login")
    public String login(){
        return "login.html";
    }

}
