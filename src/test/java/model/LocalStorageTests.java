package model;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.*;

public class LocalStorageTests {
    @Test
    public void Given_MichaelInStorage_When_AddingMary_Then_ReturnTrue() {
        IStorage storage = new LocalStorage();
        boolean result = storage.addUserName("Mary");
        assertTrue(result);
    }

    @Test
    public void Given_MichaelMarySueInStorage_When_RemovingMary_Then_ReturnTrue() {
        IStorage storage = new LocalStorage();
        storage.addUserName("Mary");
        storage.addUserName("Sue");
        boolean result = storage.removeUserName("Mary");
        assertTrue(result);
    }

    @Test
    public void Given_MichaelInStorage_When_RemovingMichael_Then_ReturnFalse() {
        IStorage storage = new LocalStorage();
        boolean result = storage.removeUserName("Michael");
        assertFalse(result);
    }

    @Test
    public void Given_MichaelInStorage_When_RemovingMichael_Then_DoNotRemoveMichael() {
        IStorage storage = new LocalStorage();
        storage.removeUserName("Michael");
        ArrayList<String> userList = storage.getAllUserNames();
        ArrayList<String> expectedUserList = new ArrayList<>(Collections.singletonList("Michael"));
        assertArrayEquals(userList.toArray(), expectedUserList.toArray());
    }

    @Test
    public void Given_MichaelInStorage_When_ChangingNameToMary_Then_ReturnTrue() {
        IStorage storage = new LocalStorage();
        boolean result = storage.changeUserName("Michael", "Mary");
        assertTrue(result);
    }

    @Test
    public void Given_MaryNotInStorage_When_ChangingNameToDave_Then_ReturnFalse() {
        IStorage storage = new LocalStorage();
        boolean result = storage.changeUserName("Mary", "Dave");
        assertFalse(result);
    }

    @Test
    public void When_AddingNonCapitalisedJeff_Then_StorageContainsCapitalisedJeff() {
        IStorage storage = new LocalStorage();
        storage.addUserName("jeff");
        ArrayList<String> userList = storage.getAllUserNames();
        ArrayList<String> expectedUserList = new ArrayList<>(Arrays.asList("Michael", "Jeff"));
        assertArrayEquals(userList.toArray(), expectedUserList.toArray());
    }
}
