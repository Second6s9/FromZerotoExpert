package com.seconds.service;

import com.seconds.entity.User;

public interface Register {
    public void regist(User user);
    public User selectByUsername(String username);
}
