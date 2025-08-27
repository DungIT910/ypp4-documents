package com.ttd.app.service;

import com.ttd.app.dto.UserDto;

import java.util.List;

public interface UserService {
    List<UserDto> getAllUsers();

    UserDto getUserById(Integer id);
}
