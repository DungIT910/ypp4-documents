package com.ttd.demo;

import com.ttd.annotation.Component;

@Component
public class UserRepository {
    public String findUser() {
        return "User from DB";
    }
}
