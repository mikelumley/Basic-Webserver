package model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.StringJoiner;

public class GreetingGenerator {

    public static String generateGreeting(ArrayList<String> users) {
        return "Hello " + GreetingGenerator.createUserListString(users) + " - the time on the server is "
                + GreetingGenerator.getCurrentTime() + " on the " + GreetingGenerator.getCurrentDate();
    }

    private static String createUserListString(ArrayList<String> users) {
        StringJoiner usersAsString = new StringJoiner(", ");
        for(int i = 0; i < users.size() - 1; i++) {
            usersAsString.add(users.get(i));
        }
        if (users.size() > 1)
            return usersAsString.toString() + " and " + users.get(users.size() - 1);
        else
            return users.get(0);
    }

    private static String getCurrentTime() {
        SimpleDateFormat timeFormatter = new SimpleDateFormat("hh:mmaa");
        return timeFormatter.format(new Date());
    }

    private static String getCurrentDate() {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd MMMMM yyyy");
        return dateFormatter.format(new Date());
    }
}
