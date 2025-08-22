package com.ttd.demo.impl;

import com.ttd.annotation.MyRepository;
import com.ttd.demo.UserRepository;

@MyRepository
public class UserRepositoryImpl implements UserRepository {
    public String findUser() {
        return "User from DB";
    }
}
