package com.ttd;

import com.ttd.beancontainer.BeanContainer;
import com.ttd.demo.UserRepository;
import com.ttd.demo.UserService;
import com.ttd.dependencyinjector.DependencyInjector;

public class Main {
    public static void main(String[] args) throws Exception {
        BeanContainer container = new BeanContainer();
        container.registerBean(UserRepository.class);
        container.registerBean(UserService.class);

        DependencyInjector injector = new DependencyInjector(container);
        injector.injectDependencies();

        UserService userService = (UserService) container.getBean(UserService.class);

        userService.printUser();

        if (userService.getUserRepository() != null) {
            System.out.println("Inject completed!");
        } else {
            System.out.println("Inject failed!");
        }
    }
}