package com.my.springbootisfun.user.controller;

import com.my.springbootisfun.user.model.User;
import com.my.springbootisfun.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    /**
     * Dependencies Injection (DI)
     * ===========================
     * - As we register our bean in the IOC
     * - We can apply DI thru two ways
     * - one is thru the @autowired or constructor class as below
     */
    @Autowired
    private UserService service;

//    public usercontroller(userservice userservice){
//        this.service = userservice;
//    }

    @GetMapping
    public User getUser(){
        return service.getUserInfo();
    }

    @PostMapping
    public User updateUser(User payload) {
        return service.updateUserInfo(payload);
    }
}
