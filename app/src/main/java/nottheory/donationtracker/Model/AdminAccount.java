package nottheory.donationtracker.Model;

public class AdminAccount extends Account {
    public AdminAccount(String name, String un, String pw, String email) {
        super(name, un, pw, email, AccountType.ADMIN);
    }
}
