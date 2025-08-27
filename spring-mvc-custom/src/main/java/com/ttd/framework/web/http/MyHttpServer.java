package com.ttd.framework.web.http;

import com.sun.net.httpserver.HttpServer;
import com.ttd.framework.web.dispatcher.MyDispatcher;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

public class MyHttpServer {
    private final HttpServer server;

    public MyHttpServer(int port, MyDispatcher dispatcher) throws IOException {
        server = HttpServer.create(new InetSocketAddress(port), 0);
        server.createContext("/", dispatcher::handle);
        server.setExecutor(Executors.newCachedThreadPool());
    }

    public void start() {
        server.start();
    }

    public void stop() {
        server.stop(0);
    }
}
