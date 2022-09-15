package com.seconds.service.impl;

import com.seconds.dao.UserDao;
import com.seconds.entity.User;
import com.seconds.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public User seletById(Integer id) {
        return userDao.selectById(id);
    }

    @Override
    public void save(User user) {
        userDao.save(user);
    }
}
