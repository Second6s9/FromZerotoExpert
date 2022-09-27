package com.seconds.service;


import com.seconds.entity.User;
import com.seconds.utils.RegisterResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Login {
    public RegisterResult checkUser(User user, HttpServletRequest request, HttpServletResponse response);
}
