package nottheory.donationtracker.Model;

public class LEAccount extends Account{
    public LEAccount(String name, String un, String pw, String email) {
        super(name, un, pw, email, AccountType.LOCATION_EMPLOYEE);
    }
}
