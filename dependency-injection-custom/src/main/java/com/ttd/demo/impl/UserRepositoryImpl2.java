package com.ttd.demo.impl;

import com.ttd.annotation.MyRepository;
import com.ttd.annotation.Scope;
import com.ttd.beandefinition.model.BeanScope;
import com.ttd.demo.UserRepository;

@MyRepository(value = "unknownBean")
@Scope(BeanScope.SINGLETON)
public class UserRepositoryImpl2 implements UserRepository {
    @Override
    public String findUser() {
        return "";
    }
}
