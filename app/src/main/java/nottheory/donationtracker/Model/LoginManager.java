package nottheory.donationtracker.Model;

import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.util.Log;

import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;

public abstract class LoginManager {
    private static ArrayList<Account> currUsers = new ArrayList<Account>();
    public static LocationCollection locations;
    public static Location currLocation;

    private LoginManager() {}

    public static void addCredentials(String un, Account acct) {

        try {
            DatabaseConnection.sendRawSQL("INSERT INTO Users (name, username, password, email, accttype) " +
            "VALUES ('" + acct.getName() + "','" + acct.getUsername() + "','" + acct.getPassword() + "','" +
            acct.getEmail() + "','" + acct.getAcctType().name() + "');");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean checkCredentials(String un, String pw) {
        String getSelection;
        try {
            getSelection = DatabaseConnection.sendRawSQL("SELECT username, password FROM Users WHERE username = '" + un + "' AND password = '" + pw + "';");
            return !(getSelection.equals(""));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void initialize_tables() {
        try {
            DatabaseConnection.sendRawSQL("CREATE TABLE IF NOT EXISTS Users (user_id INT AUTO_INCREMENT, name TEXT, username TEXT, password TEXT, email TEXT, accttype TEXT, PRIMARY KEY (user_id))  ENGINE=INNODB;");
            DatabaseConnection.sendRawSQL("CREATE TABLE IF NOT EXISTS Locations (location_id INT AUTO_INCREMENT, name TEXT, address TEXT, city TEXT, state TEXT, type TEXT, phone TEXT, website TEXT, zipcode TEXT, latitude TEXT, longitude TEXT, PRIMARY KEY (location_id))  ENGINE=INNODB;");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void logAccount(String un) {
        String this_user = null;
        try {
            this_user = DatabaseConnection.sendRawSQL("SELECT name, username, password, email, accttype FROM Users WHERE username = '" + un + "';");
            String[] user_parts = this_user.substring(2, this_user.length() - 2).split("\', \'");
            Account this_account = new Account(user_parts[0], user_parts[1], user_parts[2], user_parts[3], AccountType.valueOf(user_parts[4]));
            currUsers.add(this_account);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void logoutAccount() {
        currUsers = new ArrayList<Account>();
    }
    public static Account getCurrAccount() {
        return currUsers.get(0);
    }
}
