package com.ttd.app.controller;

import com.ttd.app.dto.UserDto;
import com.ttd.app.service.UserService;
import com.ttd.framework.annotation.MyAutowired;
import com.ttd.framework.annotation.MyController;
import com.ttd.framework.annotation.MyGetMapping;
import com.ttd.framework.annotation.MyRequestMapping;
import com.ttd.framework.web.view.ModelAndView;

@MyController
public class UserController {
    @MyAutowired
    private UserService userService;

    @MyGetMapping(path = "/users")
    public ModelAndView getAllUsers() {
        UserDto userDto = userService.getUserById(1);
        ModelAndView mv = new ModelAndView("hello");
        mv.addObject("user", userDto);
        return mv;
    }

    @MyGetMapping(path = "/users/{userId}}")
    public ModelAndView getUserById() {
        UserDto userDto = userService.getUserById(1);
        ModelAndView mv = new ModelAndView("hello");
        mv.addObject("user", userDto);
        return mv;
    }
}
