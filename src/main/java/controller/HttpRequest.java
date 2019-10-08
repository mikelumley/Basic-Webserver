package controller;

import com.sun.net.httpserver.HttpExchange;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

public class HttpRequest {
    private HttpExchange exchange;

    public HttpRequest(HttpExchange exchange) {
        this.exchange = exchange;
    }

    public HttpExchange getExchange() {
        return this.exchange;
    }

    public String getRequestBody() {
        InputStreamReader in = new InputStreamReader(exchange.getRequestBody());
        BufferedReader requestBodyReader = new BufferedReader(in);
        return requestBodyReader.lines().collect(Collectors.joining());
    }
}
