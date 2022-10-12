package com.seconds.interceptor;

import com.seconds.utils.IpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.util.concurrent.TimeUnit;

@Component
public class IpInterceptor implements HandlerInterceptor {
    public static final String PREFIX_KEY_IP = "web:ip:";

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        try {
            Date date = new Date(System.currentTimeMillis());
            String key = PREFIX_KEY_IP + date.toString();
            String ip = IpUtils.getIpAddr(request);
//            System.out.println("ip解析:" + ip);
            stringRedisTemplate.opsForHyperLogLog().add(key, ip);
            stringRedisTemplate.expire(key, 60 * 60 * 27, TimeUnit.SECONDS);
        }finally {
            return true;
        }

    }
}
