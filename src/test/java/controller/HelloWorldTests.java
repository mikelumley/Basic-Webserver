package controller;

import model.IStorage;
import model.LocalStorage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.URL;

import static org.junit.Assert.assertEquals;

public class HelloWorldTests {
    private URL serverBaseURL;
    private URL serverUserURL;

    @Before
    public void startServer() throws IOException {
        IStorage storage = new LocalStorage();
        Server.startServer("localhost", 8080, storage);
        serverBaseURL = new URL("http://localhost:8080");
        serverUserURL = new URL("http://localhost:8080/user");
    }

    @After
    public void closeServer() {
        Server.closeServer();
    }

    @Test
    public void Given_MichaelInDB_When_SendingGetRequest_Then_ReturnHelloMichael() {
        HttpResponse response = TestHttpClient.sendRequest(this.serverBaseURL, "GET", "");
        String result = response.getResponseBody();
        String expected = "Hello Michael";
        assertEquals(200, response.getResponseCode());
        assertEquals(expected, result.substring(0, expected.length()));
    }

    @Test
    public void Given_MichaelMaryInDB_When_SendingGetRequest_Then_ReturnHelloMichaelMary() {
        TestHttpClient.sendRequest(this.serverUserURL, "POST", "Mary");
        HttpResponse response = TestHttpClient.sendRequest(this.serverBaseURL, "GET", "");
        String result = response.getResponseBody();
        String expected = "Hello Michael and Mary";
        assertEquals(200, response.getResponseCode());
        assertEquals(expected, result.substring(0, expected.length()));
    }

    @Test
    public void Given_MichaelMarySueInDB_When_SendingGetRequest_Then_ReturnHelloMichaelMarySue() {
        TestHttpClient.sendRequest(this.serverUserURL, "POST", "Mary");
        TestHttpClient.sendRequest(this.serverUserURL, "POST", "Sue");
        HttpResponse response = TestHttpClient.sendRequest(this.serverBaseURL, "GET", "");
        String result = response.getResponseBody();
        String expected = "Hello Michael, Mary and Sue";
        assertEquals(200, response.getResponseCode());
        assertEquals(expected, result.substring(0, expected.length()));
    }

    @Test
    public void When_SendingPostRequest_Then_Return405Response() {
        HttpResponse response = TestHttpClient.sendRequest(this.serverBaseURL, "POST", "");
        assertEquals(405, response.getResponseCode());
    }

    @Test
    public void When_SendingPutRequest_Then_Return405Response() {
        HttpResponse response = TestHttpClient.sendRequest(this.serverBaseURL, "PUT", "");
        assertEquals(405, response.getResponseCode());
    }

    @Test
    public void When_SendingDeleteRequest_Then_Return405Response() {
        HttpResponse response = TestHttpClient.sendRequest(this.serverBaseURL, "DELETE", "");
        assertEquals(405, response.getResponseCode());
    }
}
