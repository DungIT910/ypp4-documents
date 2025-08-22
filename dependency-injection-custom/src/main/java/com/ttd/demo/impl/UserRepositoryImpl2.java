package com.ttd.demo.impl;

import com.ttd.annotation.MyRepository;
import com.ttd.demo.UserRepository;

@MyRepository
public class UserRepositoryImpl2 implements UserRepository {
    @Override
    public String findUser() {
        return "";
    }
}
