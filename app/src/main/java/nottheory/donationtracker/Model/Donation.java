package nottheory.donationtracker.Model;

public class Donation {
    private String timestamp, shortDescript, longDescript, value, category, comments;
    public Donation(String timestamp, String shortDescript, String longDescript, String value, String category, String comments) {
        this.timestamp = timestamp;
        this.shortDescript = shortDescript;
        this.longDescript = longDescript;
        this.value = value;
        this.category = category;
        this.comments = comments;
    }
    public Donation(String timestamp, String shortDescript, String longDescript, String value, String category) { //optional no comment
        this(timestamp, shortDescript, longDescript, value, category, "");
    }
    public String getName(){
        return shortDescript;
    }
    public String toString(){
        String text = "";
        text += "Name: " + shortDescript + "\n";
        text += "Time Stamp: " + timestamp + "\n";
        text += "Description: " + longDescript + "\n";
        text += "Value: " + value + "\n";
        text += "Category: " + category + "\n";
        text += "Comments (Optional): " + comments + "\n";
        return text;
    }


}
