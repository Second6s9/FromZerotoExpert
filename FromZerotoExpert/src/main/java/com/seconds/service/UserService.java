package com.seconds.service;

import com.seconds.entity.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    public User seletById(Integer id);
    public void save(User user);
}
