package com.ttd.demo;

import com.ttd.annotation.MyAutowired;
import com.ttd.annotation.MyRepository;
import com.ttd.annotation.MyService;

@MyRepository
public class UserService {
    @MyAutowired
    private UserRepository userRepository;

    public void printUser() {
        System.out.println(userRepository.findUser());
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }
}
