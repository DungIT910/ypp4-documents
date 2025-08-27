package ttd;

import com.sun.net.httpserver.HttpExchange;
import com.ttd.framework.web.http.MyHttpServer;
import com.ttd.framework.web.dispatcher.MyDispatcher;
import com.ttd.framework.context.MyApplicationContext;
import com.ttd.framework.container.BeanContainer;
import com.ttd.framework.di.DependencyInjector;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class MvcTest {
    private BeanContainer beanContainer;

    @BeforeEach
    void setUp() throws Exception {
        beanContainer = new BeanContainer();
        DependencyInjector dependencyInjector = new DependencyInjector(beanContainer);
        MyApplicationContext context = new MyApplicationContext(beanContainer, dependencyInjector);
        context.initialize("com.ttd");
    }

    @Test
    void httpServerShouldStartAndReturnExpectedResponse() throws Exception {
        MyDispatcher dispatcher = new MyDispatcher(beanContainer);
        dispatcher.init();
        MyHttpServer server = new MyHttpServer(8080, dispatcher);
        server.start();

        HttpURLConnection connection = (HttpURLConnection) new URL("http://localhost:8080/hello").openConnection();
        connection.setRequestMethod("GET");

        assertEquals(200, connection.getResponseCode());
        String body = new BufferedReader(new InputStreamReader(connection.getInputStream()))
                .lines().collect(Collectors.joining());
        assertTrue(body.contains("Dung"));

        server.stop();
    }

    @Test
    void shouldDispatchToCorrectControllerMethodBasedOnPath() throws Exception {
        HttpExchange exchange = mock(HttpExchange.class);
        when(exchange.getRequestMethod()).thenReturn("GET");
        when(exchange.getRequestURI()).thenReturn(new URI("/users/1?name=Dung"));

        ByteArrayOutputStream output = new ByteArrayOutputStream();
        when(exchange.getResponseBody()).thenReturn(output);

        MyDispatcher dispatcher = new MyDispatcher(beanContainer);
        dispatcher.init();

        dispatcher.handle(exchange);

        String response = output.toString();
        assertTrue(response.contains("Dung"));
    }
}
