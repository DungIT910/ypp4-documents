package com.ttd.hello;

import com.ttd.annotation.MyAutowired;
import com.ttd.annotation.MyService;

@MyService
public class HelloServiceImpl implements HelloService {
    @MyAutowired
    private HelloRepo helloRepo;
    @Override
    public void sayHello() {
        helloRepo.sayHello();
    }
}
