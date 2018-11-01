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

    public Account() {}

    public String getName() { return name; }

    public String getEmail() { return email; }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() { return this.name + ", " + this.email;}

    public String getUsername() {
        return username;
    }

    public AccountType getAcctType() {
        return acctType;
    }
}
