package com.ttd;

import com.ttd.framework.container.BeanContainer;
import com.ttd.framework.context.MyApplicationContext;
import com.ttd.framework.di.DependencyInjector;
import com.ttd.framework.web.dispatcher.MyDispatcher;
import com.ttd.framework.web.http.MyHttpServer;

public class Main {
    public static void main(String[] args) throws Exception {
        BeanContainer beanContainer = new BeanContainer();
        DependencyInjector dependencyInjector = new DependencyInjector(beanContainer);
        MyApplicationContext context = new MyApplicationContext(beanContainer, dependencyInjector);
        context.initialize("com.ttd");
        MyDispatcher dispatcher = new MyDispatcher(beanContainer);
        dispatcher.init();
        MyHttpServer server = new MyHttpServer(8080, dispatcher);
        server.start();
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            server.stop();
            System.out.println("Server stopped.");
        }));
    }
}