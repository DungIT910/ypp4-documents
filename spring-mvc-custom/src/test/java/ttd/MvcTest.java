package ttd;

import com.sun.net.httpserver.HttpExchange;
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
    @Test
    void httpServerShouldStartAndReturnExpectedResponse() throws Exception {
        MyHttpServer server = new MyHttpServer(8080, new Dispatcher());
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
    void testDispatcherDynamicallyRouteToController() throws Exception {
        HttpExchange exchange = mock(HttpExchange.class);
        when(exchange.getRequestMethod()).thenReturn("GET");
        when(exchange.getRequestURI()).thenReturn(new URI("/hello"));

        ByteArrayOutputStream output = new ByteArrayOutputStream();
        when(exchange.getResponseBody()).thenReturn(output);

        Dispatcher dispatcher = new Dispatcher(); // dynamic

        dispatcher.handle(exchange);

        String response = output.toString();
        assertTrue(response.contains("Dung"));
        assertTrue(response.contains("dung@example.com"));
    }
}
