package com.ttd.controller;

import com.ttd.annotation.MyAutowired;
import com.ttd.annotation.MyController;
import com.ttd.service.UserService;

@MyController
public class UserController {
    @MyAutowired
    private UserService userService;
}
