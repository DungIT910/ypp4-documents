package com.ttd.demo;

import com.ttd.demo.impl.UserRepositoryImpl;

public interface UserService {
    void printUser();

    UserRepositoryImpl getUserRepository();
}
