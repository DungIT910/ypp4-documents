package com.ttd.controller;

import com.ttd.ModelAndView;
import com.ttd.annotation.MyController;
import com.ttd.annotation.MyRequestMapping;
import com.ttd.domain.User;

@MyController
public class HelloController {

    @MyRequestMapping(path = "/hello", method = "GET")
    public ModelAndView sayHello() {
        ModelAndView mv = new ModelAndView("hello");
        mv.addObject("user", new User("Dung", "dung@example.com"));
        return mv;
    }

    @MyRequestMapping(path = "/hello2", method = "GET")
    public ModelAndView sayHello2() {
        ModelAndView mv = new ModelAndView("hello");
        mv.addObject("user", new User("Tran", "tran@example.com"));
        return mv;
    }
}
