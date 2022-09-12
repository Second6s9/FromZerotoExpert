package com.seconds.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class WelcomController {
    @RequestMapping("/FromZerotoExpert")
    public String welcom(HttpServletRequest request, HttpServletResponse response){
        Cookie[] cookies = request.getCookies();
        if(null != cookies){
            for(Cookie cookie : cookies){
                if("firstLogin".equals(cookie.getName())){
                    cookie.setValue("1");
                    cookie.setMaxAge(24 * 60 * 60);
                    response.addCookie(cookie);
                    return "index.html";
                }
            }
        }

        Cookie cookie = new Cookie("firstLogin", "0");
        cookie.setMaxAge(24 * 60 * 60);
        response.addCookie(cookie);
        return "index.html";
    }
}
