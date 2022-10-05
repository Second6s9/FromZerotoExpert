package com.seconds.controller;

import com.seconds.utils.BaseResult;
import com.seconds.utils.RegisterResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Controller
@RequestMapping("/FromZerotoExpert/main")
public class MainController {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;


    @PostMapping("/quit")
    @ResponseBody
    public RegisterResult quit(HttpServletRequest request, HttpServletResponse response){
        String username = null;
        try {
            username = (String) request.getSession().getAttribute("username");
            if(null != username && !"".equals(username)){
                stringRedisTemplate.opsForZSet().remove("onlineUser", username);
                return new RegisterResult(true);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return new RegisterResult(false);
    }

    @PostMapping("/getOnlineNums")
    @ResponseBody
    public BaseResult getOnlineNums(long time,HttpServletRequest request, HttpServletResponse response){
        String username = null;
        try {
            username = (String) request.getSession().getAttribute("username");
            if(null != username && !"".equals(username)){
//                Long nums = stringRedisTemplate.opsForZSet().count("onlineUser", (double) (time - 60 * 1000), (double) (time + 60 * 1000));
                Set<String> onlineUser = stringRedisTemplate.opsForZSet().rangeByScore("onlineUser", (double) (time - 60 * 1000), (double) (time + 60 * 1000));
                return new BaseResult(onlineUser,true);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return new BaseResult(null, false);
    }

    @PostMapping("/updateOnlineTime")
    @ResponseBody
    public RegisterResult updateOnlineTime(long time,HttpServletRequest request, HttpServletResponse response){
        String username = null;
        try {
            username = (String) request.getSession().getAttribute("username");
            if(null != username && !"".equals(username)){
                stringRedisTemplate.opsForZSet().add("onlineUser", username,(double) time);
                return new RegisterResult(true);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return new RegisterResult(false);
    }

}
