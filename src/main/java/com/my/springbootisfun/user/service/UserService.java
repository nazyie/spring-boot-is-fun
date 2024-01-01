package com.my.springbootisfun.user.service;

import com.my.springbootisfun.user.model.User;

public interface UserService {

    public User getUserInfo();

    public User updateUserInfo(User payload);
}
