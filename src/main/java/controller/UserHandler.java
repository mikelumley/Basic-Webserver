package controller;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.util.StringJoiner;

public class UserHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        HttpRequest request = new HttpRequest(exchange);
        HttpResponse response;
        switch (exchange.getRequestMethod()) {
            default :
                response = new HttpResponse(exchange, 405, "Unsupported Request");
                break;
            case "GET" :
                response = this.handleGetRequest(request);
                break;
            case "POST" :
                response = this.handlePostRequest(request);
                break;
            case "DELETE" :
                response = this.handleDeleteRequest(request);
                break;
            case "PUT" :
                response = this.handlePutRequest(request);
                break;
        }
        response.send();
    }

    private HttpResponse handleGetRequest(HttpRequest request) {
        HttpExchange exchange = request.getExchange();
        StringJoiner usersAsString = new StringJoiner(", ");
        for(String user : Server.getDB().getAllUserNames()) {
            usersAsString.add(user);
        }
        return new HttpResponse(exchange, 200, usersAsString.toString());
    }

    private HttpResponse handlePostRequest(HttpRequest request) {
        HttpExchange exchange = request.getExchange();
        String userName = this.getBodyFromRequest(request);
        boolean success = Server.getDB().addUserName(userName);
        if (success)
            return new HttpResponse(exchange, 200, "success");
        else
            return new HttpResponse(exchange, 500, "failed");
    }

    private HttpResponse handleDeleteRequest(HttpRequest request) {
        HttpExchange exchange = request.getExchange();
        String userName = this.getBodyFromRequest(request);
        boolean success = Server.getDB().removeUserName(userName);
        if (success)
            return new HttpResponse(exchange, 200, "success");
        else
            return new HttpResponse(exchange, 500, "failed");
    }

    private HttpResponse handlePutRequest(HttpRequest request) {
        HttpExchange exchange = request.getExchange();
        String requestBody = this.getBodyFromRequest(request);
        String[] requestParts = requestBody.split(":");
        String currentName = requestParts[0];
        String newName = requestParts[1];
        boolean success = Server.getDB().changeUserName(currentName, newName);
        if (success)
            return new HttpResponse(exchange, 200, "success");
        else
            return new HttpResponse(exchange, 409, "failed");
    }

    private String getBodyFromRequest(HttpRequest request) {
        return request.getRequestBody();
    }
}
