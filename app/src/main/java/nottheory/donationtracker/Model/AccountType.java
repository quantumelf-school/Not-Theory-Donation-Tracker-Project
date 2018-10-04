package nottheory.donationtracker.Model;

public enum AccountType {
    USER("User"),
    LOCATION_EMPLOYEE("Location Employee"),
    ADMIN("Admin");

    private String displayString;

    AccountType(String displayString) {
        this.displayString = displayString;
    }

    @Override
    public String toString() { return displayString; }
}
