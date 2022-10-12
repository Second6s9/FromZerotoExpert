package com.seconds.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.util.concurrent.TimeUnit;

@Component
public class PvInterceptor implements HandlerInterceptor {
    public static final String PREFIX_KEY_PV = "web:pv:";

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        try {
            Date date = new Date(System.currentTimeMillis());
            String key = PREFIX_KEY_PV + date.toString();
            if(!stringRedisTemplate.hasKey(key)){
                stringRedisTemplate.opsForValue().set(key,"0");
            }

            stringRedisTemplate.opsForValue().increment(key);
            stringRedisTemplate.expire(key, 60 * 60 * 27, TimeUnit.SECONDS);
        }finally {
            return true;
        }
    }
}
