package com.ttd.app.repository;

import com.ttd.app.dto.UserDto;

import java.util.List;

public interface UserRepository {
    List<UserDto> findAllUsers();

    UserDto findUserById(Integer id);
}
