package controller;

import model.IStorage;
import model.LocalStorage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.URL;

import static org.junit.Assert.assertEquals;

public class UserTests {
    private URL serverUserURL;

    @Before
    public void startServer() throws IOException {
        IStorage storage = new LocalStorage();
        Server.startServer("localhost", 8080, storage);
        serverUserURL = new URL("http://localhost:8080/user");
    }

    @After
    public void closeServer() {
        Server.closeServer();
    }

    @Test
    public void Given_MichaelInDB_When_SendingGetRequest_ReturnMichael() {
        HttpResponse response = TestHttpClient.sendRequest(this.serverUserURL, "GET", "");
        String result = response.getResponseBody();
        String expected = "Michael";
        assertEquals(200, response.getResponseCode());
        assertEquals(expected, result);
    }

    @Test
    public void Given_MichaelMarySueInDB_When_SendingGetRequest_ReturnMichaelMarySue() {
        TestHttpClient.sendRequest(this.serverUserURL, "POST", "Mary");
        TestHttpClient.sendRequest(this.serverUserURL, "POST", "Sue");
        HttpResponse response = TestHttpClient.sendRequest(this.serverUserURL, "GET", "");
        String result = response.getResponseBody();
        String expected = "Michael, Mary, Sue";
        assertEquals(200, response.getResponseCode());
        assertEquals(expected, result);
    }

    @Test
    public void Given_MaryNotInDB_When_SendingPostRequestWithMary_Then_ReturnSuccess() {
        HttpResponse response = TestHttpClient.sendRequest(this.serverUserURL, "POST", "Mary");
        String result = response.getResponseBody();
        String expected = "success";
        assertEquals(200, response.getResponseCode());
        assertEquals(expected, result);
    }

    @Test
    public void Given_MichaelMaryInDB_When_SendingDeleteRequestWithMary_Then_ReturnSuccess() {
        TestHttpClient.sendRequest(this.serverUserURL, "POST", "Mary");
        HttpResponse response = TestHttpClient.sendRequest(this.serverUserURL, "DELETE", "Mary");
        String result = response.getResponseBody();
        String expected = "success";
        assertEquals(200, response.getResponseCode());
        assertEquals(expected, result);
    }

    @Test
    public void Given_MaryInDB_When_SendingPutRequestMaryToDave_Then_ReturnSuccess() {
        TestHttpClient.sendRequest(this.serverUserURL, "POST", "Mary");
        HttpResponse response = TestHttpClient.sendRequest(this.serverUserURL, "PUT", "Mary:Dave");
        String result = response.getResponseBody();
        String expected = "success";
        assertEquals(200, response.getResponseCode());
        assertEquals(expected, result);
    }

    @Test
    public void Given_MaryNotInDB_When_SendingPutRequestMaryToDave_Then_ReturnFailed() {
        HttpResponse response = TestHttpClient.sendRequest(this.serverUserURL, "PUT", "Mary:Dave");
        String result = response.getResponseBody();
        String expected = "failed";
        assertEquals(409, response.getResponseCode());
        assertEquals(expected, result);
    }
}
