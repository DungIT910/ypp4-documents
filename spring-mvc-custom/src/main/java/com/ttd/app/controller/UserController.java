package com.ttd.app.controller;

import com.ttd.app.dto.UserDto;
import com.ttd.app.service.UserService;
import com.ttd.framework.annotation.MyAutowired;
import com.ttd.framework.annotation.MyController;
import com.ttd.framework.annotation.MyRequestMapping;
import com.ttd.framework.web.view.ModelAndView;

@MyController
public class UserController {
    @MyAutowired
    private UserService userService;

    @MyRequestMapping(path = "/users", method = "GET")
    public ModelAndView sayHello() {
        UserDto userDto = userService.getUserById(1);
        ModelAndView mv = new ModelAndView("hello");
        mv.addObject("user", userDto);
        return mv;
    }

    @MyRequestMapping(path = "/users/{userId}}", method = "GET")
    public ModelAndView sayHelloByUserId() {
        UserDto userDto = userService.getUserById(1);
        ModelAndView mv = new ModelAndView("hello");
        mv.addObject("user", userDto);
        return mv;
    }
}
