package com.seconds.interceptor;

import com.seconds.utils.IpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class BlackHouseInterceptor implements HandlerInterceptor {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    public static final String PREFIX_BLACK_IP = "black:ip:";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String ip = IpUtils.getIpAddr(request);
        // 检测是否在黑名单内
        if(stringRedisTemplate.hasKey(PREFIX_BLACK_IP + ip)){
            response.sendRedirect("/blackHouse");
            return false;
        }

        return true;

    }
}
