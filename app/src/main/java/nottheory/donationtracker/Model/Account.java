package nottheory.donationtracker.Model;

public class Account {
    private String name, email, un, pw;
    private AccountType acctType;


    public Account(String name, String email, String un, String pw, AccountType acctType) {
        this.name = name;
        this.email = email;
        this.un = un;
        this.pw = pw;
        this.acctType = acctType;
    }

    public String getName() { return name; }

    public String getEmail() { return email; }

    public String getPassword() {
        return pw;
    }

    public String getUsername() {
        return un;
    }

    public AccountType getAcctType() {
        return acctType;
    }
}
