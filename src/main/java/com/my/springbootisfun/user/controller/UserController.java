package com.my.springbootisfun.user.controller;

import com.my.springbootisfun.user.model.User;
import com.my.springbootisfun.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{userId}")
    public User getUserSpecific(@PathVariable String userId) {
        System.out.println("Getting the user information for user id -> " + userId);
        User user = service.getUserInfo();
        user.setName("Tampered for specific user");
        return user;
    }

    /**
     * Annotation
     * ==========
     * - Common annotation for creating the API
     *
     * @RestController - to annotate the RestController
     * @RequestMapping("/api/user") - to register the route mapping
     * @PostMapping
     * @GetMapping("/{user}")
     * @DeleteMapping
     * @PatchMapping
     * @RequestBody
     * @PathVariable
     *
     * @Component - to create component class
     * @Repository - to create the repository class
     * @Services - to create service class
     * @Bean - to register the bean (generic)
     * @Configuration - for configuration class
     *
     * @Transactional - to allow capabilities to rollback the transaction of the action if any of the item is failing
     *
     *
     * @Autowired - to inject dependencies
     */
    @PostMapping
    @Transactional
    public User updateUser(@RequestBody User payload) {
        return service.updateUserInfo(payload);
    }
}
