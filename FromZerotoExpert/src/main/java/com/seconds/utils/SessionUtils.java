package com.seconds.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class SessionUtils {

    @Autowired
    public static StringRedisTemplate stringRedisTemplate;


    public static boolean isContainsSessionId(HttpServletRequest request){
        //若能查询对应的sessionId中确实存在username的记录，则拦截器放行访问
        try {
            String sessionId = (String) request.getSession().getAttribute("user");
            if(null != sessionId && !"".equals(sessionId)){
                String username = stringRedisTemplate.opsForValue().get(sessionId);
                if(null != username && !"".equals(username)) return true;
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        //若查询sessionId失败（未找到,或异常)
        return false;
    }
}
