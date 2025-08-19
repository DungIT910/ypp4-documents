package com.ttd.demo;

import com.ttd.annotation.Autowired;
import com.ttd.annotation.Component;

@Component
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public void printUser() {
        System.out.println(userRepository.findUser());
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }
}
