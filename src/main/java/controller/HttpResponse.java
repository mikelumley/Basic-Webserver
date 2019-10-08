package controller;

import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;

public class HttpResponse {
    private HttpExchange exchange;
    private int responseCode;
    private String responseBody;

    public HttpResponse(HttpExchange exchange, int responseCode, String responseBody) {
        this.exchange = exchange;
        this.responseCode = responseCode;
        this.responseBody = responseBody;
    }

    public int getResponseCode() {
        return this.responseCode;
    }

    public String getResponseBody() {
        return this.responseBody;
    }

    public void send() throws IOException {
        this.exchange.sendResponseHeaders(this.responseCode, this.responseBody.length());
        OutputStream responseWriter = exchange.getResponseBody();
        responseWriter.write(responseBody.getBytes());
        responseWriter.close();
    }
}
