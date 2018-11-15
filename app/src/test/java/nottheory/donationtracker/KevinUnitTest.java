package nottheory.donationtracker;

import nottheory.donationtracker.Model.Account;
import nottheory.donationtracker.Model.AccountType;

import org.junit.Test;
import static junit.framework.TestCase.assertEquals;

public class KevinUnitTest {
    private Account testAccount;

    @Test
    public void testProperEmail() {
        testAccount = new Account("this_name", "this_username", "this_password",
                "this_email@gmail.com", AccountType.USER);
        String target = "'this_name','this_username','this_password','this_email@gmail.com','User'";
        assertEquals("wrong string created", target, testAccount.sqlAllInfo());
    }

    @Test
    public void testWrongEmail() {
        testAccount = new Account("this_name", "this_username", "this_password",
                "this_email", AccountType.USER);
        assertEquals("wrong string created", null, testAccount.sqlAllInfo());
    }
}