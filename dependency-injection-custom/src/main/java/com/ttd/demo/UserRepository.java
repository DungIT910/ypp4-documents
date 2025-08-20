package com.ttd.demo;

import com.ttd.annotation.MyService;

@MyService
public class UserRepository {
    public String findUser() {
        return "User from DB";
    }
}
