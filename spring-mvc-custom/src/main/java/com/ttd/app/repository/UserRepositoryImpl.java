package com.ttd.app.repository;

import com.ttd.app.dto.UserDto;
import com.ttd.framework.annotation.MyRepository;

import java.util.List;

@MyRepository
public class UserRepositoryImpl implements UserRepository {
    @Override
    public List<UserDto> findAllUsers() {
        return List.of(new UserDto("Dung", "dung@example.com"));
    }

    @Override
    public UserDto findUserById(Integer id) {
        return new UserDto("Dung", "dung@example.com");
    }
}
