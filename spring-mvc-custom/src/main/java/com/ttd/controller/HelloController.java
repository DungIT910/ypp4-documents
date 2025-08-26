package com.ttd.controller;

import com.ttd.annotation.MyAutowired;
import com.ttd.annotation.MyController;
import com.ttd.annotation.MyRequestMapping;
import com.ttd.service.UserService;

@MyController
public class UserController {
    @MyAutowired
    private UserService userService;

    @MyRequestMapping(path = "/hello", method = "GET")
    public String sayHello() {
        return "Hello from Dispatcher!";
    }
}
