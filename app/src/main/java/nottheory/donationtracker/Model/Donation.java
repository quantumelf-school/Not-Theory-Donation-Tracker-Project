package nottheory.donationtracker.Model;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

/**
 * Information Holder class to represent and store data for
 * donations logged by the system
 */
public class Donation implements Parcelable{
    private final String timestamp;
    private final String shortDescript;
    private final String longDescript;
    private final String value;
    private final String category;
    private final String comments;

    /**
     * Creates a donation based on passed in string data
     * @param timestamp the timestamp for the donation
     * @param shortDescript a short description of the donation
     * @param longDescript a long description of the donation
     * @param value the monetary value of the donation
     * @param category the category of the donation
     * @param comments any comments given about the donation
     */
    public Donation(String timestamp, String shortDescript, String longDescript, String value,
                    String category, String comments) {
        this.timestamp = timestamp;
        this.shortDescript = shortDescript;
        this.longDescript = longDescript;
        this.value = value;
        this.category = category;
        this.comments = comments;
    }

    /**
     * A constructor allowing the omission of comments
     * @param timestamp the timestamp for the donation
     * @param shortDescript a short description of the donation
     * @param longDescript a long description of the donation
     * @param value the monetary value of the donation
     * @param category the category of the donation
     */
    public Donation(String timestamp, String shortDescript, String longDescript, String value,
                    String category) { //optional no comment
        this(timestamp, shortDescript, longDescript, value, category, "");
    }

    /**
     * simple getter method for the name of the donation
     * @return the name of the donation
     */
    public String getName(){
        return shortDescript;
    }

    /**
     * simple getter for the category of the donation
     * @return the string representation of the donation's category
     */
    public String getCategory() { return category;}

    //Parcelable stuff
    private Donation(Parcel in){
        String[] data = new String[6];
        in.readStringArray(data);

        this.timestamp = data[0];
        this.shortDescript = data[1];
        this.longDescript = data[2];
        this.value = data[3];
        this.category = data[4];
        this.comments = data[5];
    }
    @Override
    public int describeContents() {
        return 0;
    }
    @Override
    public void writeToParcel(Parcel out, int flags) {
        String[] output = {this.timestamp, this.shortDescript, this.longDescript, this.value,
                this.category, this.comments};
        out.writeStringArray(output);
    }
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        @Override
        public Donation createFromParcel(Parcel in) {
            return new Donation(in);
        }

        @Override
        public Donation[] newArray(int size) {
            return new Donation[size];
        }
    };

    @NonNull
    public String toString() {
        String text = "";
        text += "Name: " + shortDescript + "\n";
        text += "Time Stamp: " + timestamp + "\n";
        text += "Description: " + longDescript + "\n";
        text += "Value: " + value + "\n";
        text += "Category: " + category + "\n";
        text += "Comments (Optional): " + comments + "\n";
        return text;
    }

    /**
     * generates and returns the string representation
     * of the donation needed for database operations
     * @return the database string of the donation
     */
    public String dataBaseString() {
        String text = "";
        text += "'" + timestamp + "', '";
        text += shortDescript + "', '";
        text += longDescript + "', '";
        text += value + "', '";
        text += category + "', '";
        text += comments + "'";
        return text;
    }

    @Override
    public boolean equals(Object o) {

        if (o == this) {
            return true;
        }

        if (o == null) {
            return false;
        }

        if (!(o instanceof Donation)) {
            return false;
        }

        Donation other = (Donation) o;

        if (!other.toString().equals(this.toString())) {
            return false;
        }

        return true;
    }

}
