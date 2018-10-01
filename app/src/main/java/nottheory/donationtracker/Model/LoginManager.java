package nottheory.donationtracker.Model;

import java.util.HashMap;

public abstract class LoginManager {
    private static HashMap<String, Account> credentialsHashMap = new HashMap<>();

    private LoginManager() {}

    public static void addCredentials(String un, Account acct) {
        credentialsHashMap.put(un, acct);
    }

    public static boolean checkCredentials(String un, String pw) {
        return (credentialsHashMap.containsKey(un) && credentialsHashMap.get(un).getPassword().equals(pw));
    }
}
