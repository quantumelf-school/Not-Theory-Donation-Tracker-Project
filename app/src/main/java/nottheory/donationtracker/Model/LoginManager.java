package nottheory.donationtracker.Model;


import java.util.List;
import java.util.ArrayList;

/**
 * static monolith class to track login credentials and status between
 * activities of the application; should be static to lower confusion
 * of passing intents and writing/saving data that need not be recovered
 * between restarts
 */
public final class LoginManager {
    private static List<Account> currUsers = new ArrayList<>();
    private static LocationCollection locations;

    private LoginManager() {}

    /**
     * adds credentials to the login manager
     * and also updates the database to facilitate persistence
     * @param un the username of the account
     * @param acct the account of interest
     * @return a boolean indicating the status
     * of the add
     */
    public static boolean addCredentials(String un, Account acct) {
        try {
            String getSelection = DatabaseConnection.sendRawSQL("SELECT username FROM Users WHERE" +
                    " username = '" + un + "';");
            if ("".equals(getSelection)) {
                String sqlAllInfo = acct.sqlAllInfo();
                if (sqlAllInfo == null) {
                    return false;

                }
                String sqlCommand = "INSERT INTO Users (name, username, password," +
                        " email, accttype) " + "VALUES (" + sqlAllInfo + ");";
                DatabaseConnection.sendRawSQL(sqlCommand);
            }
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    /**
     * checks to see if the credentials are
     * valid
     * @param un the username to check
     * @param pw the password to check
     * @return boolean representing whether or
     * not the credentials are valid
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
        System.out.println("TEST FALSE");
        return false;
    }

    /**
     * initializes location and user database tables
     * if they do not already exist
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
     * checks the database for an account given a username and updates the current
     * user based on the account found
     * @param un the username string to be searched for
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
     * sets the locations the app is using
     * to the passed location collection
     * @param newLocations the location collection to use
     *                     to set the current locations
     */
    public static void setLocations(LocationCollection newLocations) {
        locations = newLocations;
    }

    /**
     * sets the current account back to a default
     * to symbolize the user has logged out
     */
    public static void logoutAccount() {
        currUsers = new ArrayList<>();
    }

    /**
     * gets the current account that is being used to log in
     * @return the current account
     */
    public static Account getCurrAccount() {
        return currUsers.get(0);
    }

    /**
     * Adds a donation to certain location given its name.
     * @param name the name of the location
     * @param donation the donation object to be added
     */
    public static void addDonationToLocationByName(String name, Donation donation) {
        locations.addDonationToLocationByName(name, donation);
    }

    /**
     * Returns the name of a location with a certain donation
     * @param d the donation to search for the location
     * @return name of the location
     */
    public static String getNameOfLocationWithDonation(Donation d) {
        Location l = locations.getLocationWithDonation(d);
        return l.getName();
    }
    /**
     * Returns the list of donations of a certain location given its name
     * @param name the name of the location
     * @return the list of its donations
     */
    public static List<Donation> getDonationsOfLocationByName(String name) {
        Location l = locations.getLocationByName(name);
        return l.getDonations();
    }

    /**
     * gets the location collection the app is working with
     * @return the current location collection
     */
    public static LocationCollection getLocations() {
        return locations;
    }
}
