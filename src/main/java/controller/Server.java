package controller;

import com.sun.net.httpserver.HttpServer;
import model.IStorage;
import model.LocalStorage;

import java.io.IOException;
import java.net.InetSocketAddress;

public class Server {

    private static HttpServer server;
    private static IStorage storage;

    public static IStorage getDB() {
        return Server.storage;
    }

    public static void main(String[] args) {
        try {
            String hostname = args[0];
            int port = Integer.parseInt(args[1]);
            IStorage storage = new LocalStorage();
            Server.startServer(hostname, port, storage);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public static void startServer(String hostname, int port, IStorage storage) throws IOException {
        Server.storage = storage;
        Server.server = HttpServer.create(new InetSocketAddress(hostname, port), 0);
        Server.server.createContext("/", new HelloWorldHandler());
        Server.server.createContext("/user", new UserHandler());
        Server.server.start();
    }

    public static void closeServer() {
        Server.server.stop(0);
    }
}
