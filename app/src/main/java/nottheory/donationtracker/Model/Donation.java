package nottheory.donationtracker.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class Donation implements Parcelable{
    private final String timestamp;
    private final String shortDescript;
    private final String longDescript;
    private final String value;
    private final String category;
    private final String comments;
    public Donation(String timestamp, String shortDescript, String longDescript, String value,
                    String category, String comments) {
        this.timestamp = timestamp;
        this.shortDescript = shortDescript;
        this.longDescript = longDescript;
        this.value = value;
        this.category = category;
        this.comments = comments;
    }
    public Donation(String timestamp, String shortDescript, String longDescript, String value,
                    String category) { //optional no comment
        this(timestamp, shortDescript, longDescript, value, category, "");
    }
    public String getName(){
        return shortDescript;
    }
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
        public Donation createFromParcel(Parcel in) {
            return new Donation(in);
        }

        public Donation[] newArray(int size) {
            return new Donation[size];
        }
    };
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

}
