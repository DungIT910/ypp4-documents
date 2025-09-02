package com.ttd;


import com.ttd.applicationcontext.MyApplicationContext;
import com.ttd.beancontainer.BeanContainer;
import com.ttd.dependencyinjector.DependencyInjector;
import com.ttd.hello.HelloService;

public class Main {
    public static void main(String[] args) throws Exception {
        BeanContainer beanContainer = new BeanContainer();
        DependencyInjector dependencyInjector = new DependencyInjector(beanContainer);
        MyApplicationContext applicationContext = new MyApplicationContext(beanContainer, dependencyInjector);
        applicationContext.initialize("com.ttd.hello");
        HelloService helloService = (HelloService) beanContainer.getBeanByName("helloServiceImpl");
        helloService.sayHello();
    }
}