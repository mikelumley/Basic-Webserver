package model;

import java.util.ArrayList;

public interface IStorage {
    ArrayList<String> getAllUserNames();
    boolean addUserName(String userName);
    boolean removeUserName(String userName);
    boolean changeUserName(String currentName, String newName);
}
