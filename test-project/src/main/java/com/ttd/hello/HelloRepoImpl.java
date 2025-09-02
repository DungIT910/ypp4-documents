package com.ttd.hello;

import com.ttd.annotation.MyRepository;

@MyRepository
public class HelloRepoImpl implements HelloRepo {
    @Override
    public void sayHello() {
        System.out.println("Hello world");
    }
}
