package com.seconds.interceptor;

import cn.hutool.core.util.IdUtil;
import com.seconds.utils.CookieDeathUtils;
import com.seconds.utils.CookieUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.util.concurrent.TimeUnit;

@Component
public class UvInterceptor implements HandlerInterceptor {
    public static final String PREFIX_KEY_UV = "web:uv:";
    public static final String PV_COOKIE_NAME = "pv_cookie_id";

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        try {
            Date date = new Date(System.currentTimeMillis());
            String key = PREFIX_KEY_UV + date.toString();
            String cookieValue = CookieUtils.getCookieValue(request, PV_COOKIE_NAME);
            if(null == cookieValue){

                String c_value = IdUtil.simpleUUID();
                CookieUtils.setCookie(request,response,PV_COOKIE_NAME,c_value,
                        CookieDeathUtils.getCookieDeath());

                stringRedisTemplate.opsForHyperLogLog().add(key, c_value);
                stringRedisTemplate.expire(key, 60 * 60 * 24, TimeUnit.SECONDS);
            }else{
                stringRedisTemplate.opsForHyperLogLog().add(key, cookieValue);
                stringRedisTemplate.expire(key, 60 * 60 * 27, TimeUnit.SECONDS);
            }
        }finally {
            return true;
        }

    }
}
