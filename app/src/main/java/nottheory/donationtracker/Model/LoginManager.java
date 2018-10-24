package nottheory.donationtracker.Model;

import java.util.HashMap;
import java.util.ArrayList;

public abstract class LoginManager {
    private static HashMap<String, Account> credentialsHashMap = new HashMap<>();
    private static ArrayList<Account> currUsers = new ArrayList<Account>();
    public static LocationCollection locations;

    private LoginManager() {}

    public static void addCredentials(String un, Account acct) {
        credentialsHashMap.put(un, acct);
    }

    public static boolean checkCredentials(String un, String pw) {
        return (credentialsHashMap.containsKey(un) && credentialsHashMap.get(un).getPassword().equals(pw));
    }
    public static void logAccount(String un) {
        currUsers.add(credentialsHashMap.get(un));
    }
    public static void logoutAccount() {
        currUsers = new ArrayList<Account>();
    }
    public static Account getCurrAccount() {
        return currUsers.get(0);
    }
}
