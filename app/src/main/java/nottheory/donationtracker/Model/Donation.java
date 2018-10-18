package nottheory.donationtracker.Model;

public class Donation {
    private Location location;
    private String timestamp, shortDescript, longDescript, value, category, comments;
    public Donation(Location location, String timestamp, String shortDescript, String longDescript, String value, String category, String comments) {
        this.location = location;
        this.timestamp = timestamp;
        this.shortDescript = shortDescript;
        this.longDescript = longDescript;
        this.value = value;
        this.category = category;
        this.comments = comments;
    }
    public Donation(Location location, String timestamp, String shortDescript, String longDescript, String value, String category) { //optional no comment
        this(location, timestamp, shortDescript, longDescript, value, category, "");
    }

}
