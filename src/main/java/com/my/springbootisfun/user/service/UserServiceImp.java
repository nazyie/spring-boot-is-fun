package com.my.springbootisfun.user.service;

import com.my.springbootisfun.user.controller.UserController;
import com.my.springbootisfun.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * Spring IOC
 * ==========
 * - Inversion-Of-Control (IOC) is the software architecture where everything under the container
 * - Let said you have the certain object
 * - We will register that object in the container
 * - So we can easily call that object without relying on the container
 */
@Component
public class UserServiceImp implements UserService{

    private final User DEFAULT_USER = User.builder().name("Engku Nazri").age(99).build();

    @Autowired
    ApplicationContext applicationContext;

    /**
     * Spring IOC
     * ==========
     * - We can check if our bean is existed in our container or not here
     */
    @Bean
    private void exampleIocImplementation() {
        System.out.println("Check for the bean is exist or not");
        UserController userControllerBean = (UserController) applicationContext.getBean("userController");
        if (userControllerBean != null) {
            System.out.println("Bean is exist!!");
        }
    }

    @Override
    public User getUserInfo() {
        return DEFAULT_USER;
    }

    @Override
    public User updateUserInfo(User payload) {
        User user = DEFAULT_USER;
        user.setName(payload.getName() != null ? payload.getName() : DEFAULT_USER.getName());
        user.setAge(payload.getAge() != 0 ? payload.getAge() : DEFAULT_USER.getAge());
        return user;
    }
}
