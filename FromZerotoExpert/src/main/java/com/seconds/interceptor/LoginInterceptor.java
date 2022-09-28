package com.seconds.interceptor;

import com.seconds.entity.User;
import com.seconds.utils.SessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeUnit;


@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //若能查询对应的sessionId中确实存在username的记录，则拦截器放行访问
        try {
            String sessionId = (String) request.getSession().getAttribute("user");
            if(null != sessionId && !"".equals(sessionId)){
                String username = stringRedisTemplate.opsForValue().get(sessionId);
                if(null != username && !"".equals(username)){
                    request.getSession().setMaxInactiveInterval(60 * 60 * 24);        //设置session过期时间
                    stringRedisTemplate.expire(sessionId, 60 * 60 * 24, TimeUnit.SECONDS);
                    stringRedisTemplate.expire(username, 60 * 60 * 24, TimeUnit.SECONDS);
                    return true;
                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }


        response.sendRedirect("/redirect_login");
        //查询sessionId失败（未找到或异常），则重定向至登录页面
        return false;



    }
}
