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
    public static final String PREFIX_KEY_COUNT_IP = "count:ip:";
    public static final String PREFIX_WHITE_IP = "white:ip:";
    public static final String PREFIX_BLACK_IP = "black:ip:";
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        try {
            // IP统计,统计网站的IP访问数
            Date date = new Date(System.currentTimeMillis());
            String key = PREFIX_KEY_IP + date.toString();
            String ip = IpUtils.getIpAddr(request);
            stringRedisTemplate.opsForHyperLogLog().add(key, ip);
            stringRedisTemplate.expire(key, 60 * 60 * 27, TimeUnit.SECONDS);

            // 数据库白名单应该在Mysql中存储并完成加载，目前为了快速开发，还未实现


            // 检测是否在白名单内
            if(stringRedisTemplate.hasKey(PREFIX_WHITE_IP + ip)){
                return true;
            }
            // 检测是否在黑名单内
            if(stringRedisTemplate.hasKey(PREFIX_BLACK_IP + ip)){
                response.sendRedirect("/blackHouse");
                return false;
            }

            // IP访问计数
            String count_key = PREFIX_KEY_COUNT_IP + ip;
            if(!stringRedisTemplate.hasKey(count_key)){
                stringRedisTemplate.opsForValue().set(count_key,"0");
                stringRedisTemplate.expire(count_key, 60, TimeUnit.SECONDS);
            }
            stringRedisTemplate.opsForValue().increment(count_key);
            Integer count = Integer.parseInt(stringRedisTemplate.opsForValue().get(count_key));

            if(count > 100){
                // 访问频率过高，加入黑名单
                stringRedisTemplate.opsForValue().set(PREFIX_BLACK_IP + ip, "blackHouse");
                stringRedisTemplate.expire(PREFIX_BLACK_IP + ip, 60, TimeUnit.SECONDS);
                return false;
            }else{
                return true;
            }
        }catch (Exception e){
            System.out.println("IP拦截器拦截！");
            e.printStackTrace();
            return false;
        }

    }
}
