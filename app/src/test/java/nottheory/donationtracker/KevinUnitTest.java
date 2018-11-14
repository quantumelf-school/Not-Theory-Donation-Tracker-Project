package nottheory.donationtracker;

import org.junit.Test;

import nottheory.donationtracker.Model.Account;
import nottheory.donationtracker.Model.AccountType;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class KevinUnitTest {
    private Account testAccount;

    @Test
    public void testProperEmail() {
        testAccount = new Account("this_name", "this_username", "this_password",
                "this_email", AccountType.USER);
        String target = "'this_name','this_username','this_password','this_email','User'";
        assertEquals("wrong string created", target, testAccount.sqlAllInfo());
    }

    @Test
    public void testWrongEmail() {
        testAccount = new Account("this_name", "this_username", "this_password",
                "this_email", AccountType.USER);
        String target = "'this_name','this_username','this_password','this_email','User'";
        assertEquals("wrong string created", target, testAccount.sqlAllInfo());
    }
}