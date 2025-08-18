package com.ttd.example;

import com.ttd.annotation.Component;

@Component
public class MyRepository {
    public void query() {
        System.out.println("Querying the database...");
    }
}