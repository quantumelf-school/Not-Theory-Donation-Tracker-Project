package nottheory.donationtracker.Model;

import android.support.annotation.NonNull;

/**
 * A class for the Account which grants permissions and saves login information
 */
public class Account {
    private final String name;
    private final String email;
    private final String username;
    private final String password;
    private final AccountType acctType;

    /**
     * The constructor for the account with all information to be saved
     * @param name the name of the user
     * @param un the username used for logging in
     * @param pw the password associated with the username
     * @param email the email of a user
     * @param acctType the permissions given to the user associated with the account
     */
    public Account(String name, String un, String pw, String email, AccountType acctType) {
        this.name = name;
        this.email = email;
        this.username = un;
        this.password = pw;
        this.acctType = acctType;
    }

    /**
     * The getter for the name attribute
     * @return the user's name
     */
    public String getName() { return name; }

    @NonNull
    @Override
    public String toString() { return this.name + ", " + this.email;}

    /**
     * Gets the permissions of the account
     * @return the account type
     */
    public AccountType getAcctType() {
        return acctType;
    }

    /**
     * Gets all of the info for the SQL file
     * @return the string used for the SQL Table
     */
    public String sqlAllInfo() {
        String returnString = "'" + name + "','" + username + "','" + password + "','";
        returnString += email + "','" + acctType.name() + "'";
        if (!email.contains("@")) {
            return null;
        }
        return returnString;
    }
}
