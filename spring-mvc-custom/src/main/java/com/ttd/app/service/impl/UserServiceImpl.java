package com.ttd.app.service.impl;

import com.ttd.app.dto.UserDto;
import com.ttd.app.repository.UserRepository;
import com.ttd.app.service.UserService;
import com.ttd.framework.annotation.MyAutowired;
import com.ttd.framework.annotation.MyService;

import java.util.List;

@MyService
public class UserServiceImpl implements UserService {
    @MyAutowired
    private UserRepository userRepository;

    @Override
    public List<UserDto> getAllUsers() {
        return userRepository.findAllUsers();
    }

    @Override
    public UserDto getUserById(Integer id) {
        return userRepository.findUserById(id);
    }
}
