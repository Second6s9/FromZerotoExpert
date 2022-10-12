package com.seconds.controller;

import com.seconds.entity.WebInformation;
import com.seconds.service.WebInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/FromZerotoExpert/webInformation")
public class WebInformationController {
    @Autowired
    private WebInformationService webInformationService;

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

}
