package ttd;

import com.sun.net.httpserver.HttpExchange;
import com.ttd.core.applicationcontext.MyApplicationContext;
import com.ttd.core.beancontainer.BeanContainer;
import com.ttd.core.dependencyinjector.DependencyInjector;
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

public class MvcTest {
    private BeanContainer beanContainer;
    private MyApplicationContext context;
    private DependencyInjector dependencyInjector;

    @BeforeEach
    void setUp() throws Exception {
        beanContainer = new BeanContainer();
        dependencyInjector = new DependencyInjector(beanContainer);
        context = new MyApplicationContext(beanContainer, dependencyInjector);
        context.initialize("com.ttd");
    }

    @Test
    void httpServerShouldStartAndReturnExpectedResponse() throws Exception {
        MyHttpServer server = new MyHttpServer(8080, new Dispatcher(beanContainer));
        server.start();

        HttpURLConnection connection = (HttpURLConnection) new URL("http://localhost:8080/hello").openConnection();
        connection.setRequestMethod("GET");

        assertEquals(200, connection.getResponseCode());
        String body = new BufferedReader(new InputStreamReader(connection.getInputStream()))
                .lines().collect(Collectors.joining());
        assertEquals("Hello from Dispatcher!", body);

        server.stop();
    }

    @Test
    void shouldDispatchToCorrectControllerMethodBasedOnPath() throws Exception {
        HttpExchange exchange = mock(HttpExchange.class);
        when(exchange.getRequestMethod()).thenReturn("GET");
        when(exchange.getRequestURI()).thenReturn(new URI("/hello2"));

        ByteArrayOutputStream output = new ByteArrayOutputStream();
        when(exchange.getResponseBody()).thenReturn(output);

        Dispatcher dispatcher = new Dispatcher(beanContainer);
        dispatcher.init();

        dispatcher.handle(exchange);

        String response = output.toString();
        assertTrue(response.contains("Tran"));
    }
}
