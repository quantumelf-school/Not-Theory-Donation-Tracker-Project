package nottheory.donationtracker.Model;


import java.util.List;
import java.util.ArrayList;

public final class LoginManager {
    private static List<Account> currUsers = new ArrayList<>();
    private static LocationCollection locations;

    private LoginManager() {}

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
            return false;
        }
    }

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

    public static void setLocations(LocationCollection newLocations) {
        locations = newLocations;
    }

    public static void logoutAccount() {
        currUsers = new ArrayList<>();
    }
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
     * Returns the list of donations of a certain location given its name
     * @param name the name of the location
     * @return the list of its donations
     */
    public static List<Donation> getDonationsOfLocationByName(String name) {
        Location l = locations.getLocationByName(name);
        return l.getDonations();
    }

    public static LocationCollection getLocations() {
        return locations;
    }
}
