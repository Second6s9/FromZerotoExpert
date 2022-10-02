package com.seconds.service.impl;

import cn.hutool.core.util.IdUtil;
import com.seconds.dao.UserDao;
import com.seconds.entity.User;
import com.seconds.service.Login;
import com.seconds.utils.RegisterResult;
import com.seconds.utils.RegisterUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Service
public class LoginImpl implements Login {
    @Autowired
    private UserDao userDao;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public RegisterResult checkUser(User user, HttpServletRequest request, HttpServletResponse response) {
        //检测用户名是否存在
        if(null == user) return new RegisterResult(false, "该用户不存在！");
        String username = user.getUsername();
        String input_password = user.getPassword();
        User res = userDao.selectByUsername(username);
        if(null == res) return new RegisterResult(false, "该用户不存在！");

        //校验密码是否正确
        String salt = res.getSalt();
        String password = res.getPassword();
        String check_password = RegisterUtils.encryptPassword(input_password, salt);
        //密码错误，则在前端做出提示
        if(!password.equals(check_password)) return new RegisterResult(false, "用户名或密码错误!");


        Cookie cookie = new Cookie("JSESSIONID",request.getSession().getId());
        cookie.setMaxAge(60 * 60 * 24);
        response.addCookie(cookie);

        //密码正确，先检查之前的登录是否过期
        String oldSessionId = stringRedisTemplate.opsForValue().get(username);
        if(null != oldSessionId && !"".equals(oldSessionId)){
            //未过期，则手动设置过期
            stringRedisTemplate.expire(oldSessionId, 0, TimeUnit.SECONDS);
            stringRedisTemplate.expire(username, 0, TimeUnit.SECONDS);
        }


        //密码正确，生成sessionId，并将sessionId和username互作为键值存入redis数据库中
        String sessionId = IdUtil.simpleUUID();
        request.getSession().setAttribute("user", sessionId);
        request.getSession().setMaxInactiveInterval(60 * 60 * 24);        //设置session过期时间
        stringRedisTemplate.opsForValue().set(sessionId, username);
        stringRedisTemplate.opsForValue().set(username, sessionId);
        //设置redis键值对过期时间
        stringRedisTemplate.expire(sessionId, 60 * 60 *24, TimeUnit.SECONDS);
        stringRedisTemplate.expire(username, 60 * 60 * 24, TimeUnit.SECONDS);



        //存储用户名称，重定向至主页时防止数据丢失
        request.getSession().setAttribute("username", username);
        long time = new Date().getTime();
        stringRedisTemplate.opsForZSet().add("onlineUser", username, (double) time);

        return new RegisterResult(true);
    }
}
