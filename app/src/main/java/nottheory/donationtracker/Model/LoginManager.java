package nottheory.donationtracker.Model;


import java.util.List;
import java.util.ArrayList;
/**
 * A class to check and read login credentials
 */
public final class LoginManager {
    private static List<Account> currUsers = new ArrayList<Account>();
    public static LocationCollection locations;
    //public static Location currLocation;

    private LoginManager() {}

    /**
     * A method for adding an account and its credentials to the database so that the account
     * can be used.
     * @param un the user name being added to the database
     * @param acct the account being associated with the username
     */
    public static void addCredentials(String un, Account acct) {

        try {
            String getSelection = DatabaseConnection.sendRawSQL("SELECT username FROM Users WHERE" +
                    " username = '" + un + "';");
            if ("".equals(getSelection)) {
                DatabaseConnection.sendRawSQL("INSERT INTO Users (name, username, password," +
                        " email, accttype) " +
                        "VALUES (" + acct.sqlAllInfo() + ");");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * A method for checking a login being attempted by the apps user
     * @param un the username being tested as to whether it is in the database
     * @param pw the password being tested as to whether it is associated with pw
     * @return a boolean of whether the login is valid
     */
    public static boolean checkCredentials(String un, String pw) {
        String getSelection;
        try {
            getSelection = DatabaseConnection.sendRawSQL("SELECT username, password FROM Users" +
                    " WHERE username = '" + un + "' AND password = '" + pw + "';");
            return !("".equals(getSelection));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * A method to start the SQL database
     */
    public static void initialize_tables() {
        try {
            DatabaseConnection.sendRawSQL("CREATE TABLE IF NOT EXISTS Users (user_id INT" +
                    " AUTO_INCREMENT, name TEXT, username TEXT, password TEXT, email TEXT," +
                    " accttype TEXT, PRIMARY KEY (user_id))  ENGINE=INNODB;");
            DatabaseConnection.sendRawSQL("CREATE TABLE IF NOT EXISTS Locations (location_id INT" +
                    " AUTO_INCREMENT, name TEXT, address TEXT, city TEXT, state TEXT, type TEXT," +
                    " phone TEXT, website TEXT, zipcode TEXT, latitude TEXT, longitude TEXT," +
                    " PRIMARY KEY (location_id))  ENGINE=INNODB;");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Method to get the account by its username in the SQL table and parse it as an Account object
     * @param un The username we want the account information about
     */
    public static void logAccount(String un) {
        try {
            String this_user = DatabaseConnection.sendRawSQL("SELECT name, username, password," +
                    " email, accttype FROM Users WHERE username = '" + un + "';");
            String substring = this_user.substring(2, this_user.length() - 2);
            String[] user_parts = substring.split("\', \'");
            Account this_account = new Account(user_parts[0], user_parts[1], user_parts[2],
                    user_parts[3], AccountType.valueOf(user_parts[4]));
            currUsers.add(this_account);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * A method to log out of any accounts which may be logged in currently
     */
    public static void setLocations(LocationCollection newLocations) {
        locations = newLocations;
    }

    public static void logoutAccount() {
        currUsers = new ArrayList<Account>();
    }

    /**
     * A method to return the account being used
     * @return the account being used
     */
    public static Account getCurrAccount() {
        return currUsers.get(0);
    }
}
