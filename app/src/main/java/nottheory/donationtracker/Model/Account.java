package nottheory.donationtracker.Model;

public class Account {
    private String name, email, username, password;
    private AccountType acctType;


    public Account(String name, String un, String pw, String email, AccountType acctType) {
        this.name = name;
        this.email = email;
        this.username = un;
        this.password = pw;
        this.acctType = acctType;
    }

    public String getName() { return name; }

    @Override
    public String toString() { return this.name + ", " + this.email;}

    public AccountType getAcctType() {
        return acctType;
    }
    public String sqlAllInfo() {
        String returnString = "'" + name + "','" + username + "','" + password + "',";
        if (email != null) {
            returnString += "'" + email + "','";
        } else {
            returnString += " ,'";
        }
        returnString += acctType.name() + "'";
        return returnString;
    }
}
