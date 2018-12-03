package nottheory.donationtracker.Model;

public class AccountFactory {
    public static Account getAccount(String name, String un, String pw, String email, AccountType acctType) {
        if(acctType.equals(AccountType.ADMIN)) {
            return new AdminAccount(name, un, pw, email);
        } else if (acctType.equals(AccountType.LOCATION_EMPLOYEE)) {
            return new LEAccount(name, un, pw, email);
        } else {
            return new Account(name, un, pw, email, acctType);
        }
    }
}
