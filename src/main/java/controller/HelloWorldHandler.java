package controller;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import model.GreetingGenerator;

import java.io.IOException;

public class HelloWorldHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        HttpResponse response;

        if ("GET".equals(exchange.getRequestMethod())) {
            response = this.handleGetRequest(exchange);
        }
        else {
            response = new HttpResponse(exchange, 405, "Unsupported Request Type");
        }

        response.send();
    }

    private HttpResponse handleGetRequest(HttpExchange exchange) {
        String responseBody = GreetingGenerator.generateGreeting(Server.getDB().getAllUserNames());
        return new HttpResponse(exchange, 200, responseBody);
    }
}
