package com.seconds.service.impl;

import com.seconds.dao.UserDao;
import com.seconds.entity.User;
import com.seconds.service.Register;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegisterImpl implements Register {
    @Autowired
    private UserDao userDao;
    @Override
    public void regist(User user) {
        userDao.save(user);
    }
}
