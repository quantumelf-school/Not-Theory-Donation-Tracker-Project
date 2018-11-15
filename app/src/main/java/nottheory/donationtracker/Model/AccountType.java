package nottheory.donationtracker.Model;

import android.support.annotation.NonNull;

/**
 * An enum of the different account types that can be had
 */
public enum AccountType {
    USER("User"),
    LOCATION_EMPLOYEE("Location Employee"),
    ADMIN("Admin");

    private final String displayString;

    AccountType(String displayString) {
        this.displayString = displayString;
    }

    @Override
    public @NonNull
    String toString() { return displayString; }
}
