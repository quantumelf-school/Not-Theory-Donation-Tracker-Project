package nottheory.donationtracker.Model;

public class Account {
    private String un;
    private String pw;
    private AccountType acctType;


    public Account(String un, String pw, AccountType acctType) {
        this.un = un;
        this.pw = pw;
        this.acctType = acctType;
    }

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
