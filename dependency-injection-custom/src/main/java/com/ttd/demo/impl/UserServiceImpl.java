package com.ttd.demo.impl;

import com.ttd.annotation.MyAutowired;
import com.ttd.annotation.MyQualifier;
import com.ttd.annotation.MyService;
import com.ttd.demo.UserRepository;
import com.ttd.demo.UserService;

@MyService
public class UserServiceImpl implements UserService {
    @MyAutowired
    @MyQualifier("unknownBean")
    private UserRepository userRepository;

    public void printUser() {
        System.out.println(userRepository.findUser());
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }
}
