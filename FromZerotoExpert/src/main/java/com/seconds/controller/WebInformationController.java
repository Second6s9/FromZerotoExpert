package com.seconds.controller;

import com.seconds.entity.WebInformation;
import com.seconds.service.WebInformationService;
import com.seconds.utils.IpUtils;
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
import java.util.concurrent.TimeUnit;

@Controller
@RequestMapping("/FromZerotoExpert/webInformation")
public class WebInformationController {
    @Autowired
    private WebInformationService webInformationService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @PostMapping("/getIP")
    @ResponseBody
    public String getIP(){
        return webInformationService.getIpNum();
    }

    @PostMapping("/getPV")
    @ResponseBody
    public String getPV(){
        return webInformationService.getPvNum();
    }


    @PostMapping("/getUV")
    @ResponseBody
    public String getUV(){
        return webInformationService.getUvNum();
    }

    @PostMapping("/getLastDayInformation")
    @ResponseBody
    public WebInformation getLastDayInformation(){
        return webInformationService.getLastDayInformation();
    }

    @PostMapping("/getRangeWebInformation")
    @ResponseBody
    public Map<String, String> getRangeWebInformation(Integer days){
        Map<String, String> map = new HashMap<>();
        String ip = webInformationService.getRangeIpNum(days);
        String pv = webInformationService.getRangePvNum(days);
        String uv = webInformationService.getRangeUvNum(days);
        map.put("ip", ip);
        map.put("pv", pv);
        map.put("uv", uv);
        return map;
    }



    @RequestMapping("/go_blackHouse")
    public String blackHouse(HttpServletRequest request, HttpServletResponse response){
//        System.out.println("进来啦");
        String ip = IpUtils.getIpAddr(request);
        stringRedisTemplate.opsForValue().set("black:ip:" + ip, "blackHouse");
        stringRedisTemplate.expire("black:ip:" + ip, 60, TimeUnit.SECONDS);

        return "blackHouse.html";
    }


}
