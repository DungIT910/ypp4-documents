package com.ttd.example;

import com.ttd.annotation.Autowired;
import com.ttd.annotation.Component;
import com.ttd.annotation.PostConstruct;

@Component
public class MyService {

    @Autowired
    private MyRepository repository;

    @PostConstruct
    public void init() {
        System.out.println("MyService initialized.");
    }

    public void doSomething() {
        repository.query();
    }
}