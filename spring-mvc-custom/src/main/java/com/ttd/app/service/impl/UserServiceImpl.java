package com.ttd.app.service.impl;

import com.ttd.app.dto.UserDto;
import com.ttd.app.service.UserService;
import com.ttd.framework.annotation.MyService;

@MyService
public class UserServiceImpl implements UserService {
    @Override
    public UserDto getUserById(Integer id) {
        return new UserDto("Dung", "dung@example.com");
    }
}
