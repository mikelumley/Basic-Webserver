package model;

import java.util.ArrayList;
import java.util.Collections;

public class LocalStorage implements IStorage {
    private final static String OWNER = "Michael";

    private ArrayList<String> userNames = new ArrayList<>(Collections.singletonList(OWNER));

    @Override
    public ArrayList<String> getAllUserNames() {
        return this.userNames;
    }

    @Override
    public boolean addUserName(String userName) {
        userName = this.capitaliseUserName(userName);
        return this.userNames.add(userName);
    }

    @Override
    public boolean removeUserName(String userName) {
        if (!userName.equals(OWNER))
            return this.userNames.remove(userName);
        else
            return false;
    }

    @Override
    public boolean changeUserName(String currentName, String newName) {
        if (this.userNames.remove(currentName)) {
            return this.userNames.add(newName);
        }
        return false;
    }

    private String capitaliseUserName(String userName) {
        return userName.substring(0,1).toUpperCase() + userName.substring(1).toLowerCase();
    }
}
