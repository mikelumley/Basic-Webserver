package model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GreetingGeneratorTests {

    @Test
    public void Given_Michael_When_GeneratingGreeting_Then_ReturnHelloMichael() {
        IStorage storage = new LocalStorage();
        String result = GreetingGenerator.generateGreeting(storage.getAllUserNames());
        String expected = "Hello Michael";
        assertEquals(expected, result.substring(0, expected.length()));
    }

    @Test
    public void Given_MichaelMary_When_GeneratingGreeting_Then_ReturnHelloMichaelAndMary() {
        IStorage storage = new LocalStorage();
        storage.addUserName("Mary");
        String result = GreetingGenerator.generateGreeting(storage.getAllUserNames());
        String expected = "Hello Michael and Mary";
        assertEquals(expected, result.substring(0, expected.length()));
    }

    @Test
    public void Given_MichaelMarySue_When_GeneratingGreeting_Then_ReturnHelloMichaelMaryAndSue() {
        IStorage storage = new LocalStorage();
        storage.addUserName("Mary");
        storage.addUserName("Sue");
        String result = GreetingGenerator.generateGreeting(storage.getAllUserNames());
        String expected = "Hello Michael, Mary and Sue";
        assertEquals(expected, result.substring(0, expected.length()));
    }
}
