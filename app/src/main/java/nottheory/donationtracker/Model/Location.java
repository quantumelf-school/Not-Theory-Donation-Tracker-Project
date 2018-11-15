package nottheory.donationtracker.Model;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

public class Location {
    private final String name;
    private final String address;
    private final String city;
    private final String state;
    private final String type;
    private final String phone;
    private final String website;
    private final String zipcode;
    private final String latitude;
    private final String longitude;
    private DonationCollection inventory = new DonationCollection();
    private int row = 0;

    public Location(String name, String latitude, String longitude, String address, String city,
                    String state, String zipcode, String type, String phone, String website) {
        this.name = name;
        this.address = address;
        this.city = city;
        this.state = state;
        this.type = type;
        this.phone = phone;
        this.website = website;
        this.zipcode = zipcode;
        this.latitude = latitude;
        this.longitude = longitude;
        try {
            DatabaseConnection.sendRawSQL("CREATE TABLE IF NOT EXISTS `" + name + " INV` " +
                    "(donation_id INT AUTO_INCREMENT, timestamp TEXT, shortDescript TEXT, " +
                    "longDescript TEXT, value TEXT, category TEXT, comments TEXT, PRIMARY KEY " +
                    "(donation_id))  ENGINE=INNODB;");
            String locationinventory = DatabaseConnection.sendRawSQL("SELECT timestamp, " +
                    "shortDescript, longDescript, value, category, " +
                    "comments FROM `" + name + " INV`");
            if (!"".equals(locationinventory)) {
                String delimiter = "\\),\\(";
                String[] donation_list = (locationinventory.substring(1,
                        locationinventory.length() - 1)).split(delimiter);
                for (String donation_x: donation_list) {
                    String[] donation_parts = donation_x.substring(1,
                            donation_x.length() - 1).split("', '");
                    if (donation_parts.length == 5) {
                        Donation this_donation = new Donation(donation_parts[0],
                                donation_parts[1], donation_parts[2], donation_parts[3],
                                donation_parts[4]);
                        inventory.addDonation(this_donation);
                    } else {
                        Donation this_donation = new Donation(donation_parts[0],
                                donation_parts[1], donation_parts[2], donation_parts[3],
                                donation_parts[4], donation_parts[5]);
                        inventory.addDonation(this_donation);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void addDonation(Donation d) {
        try {
            DatabaseConnection.sendRawSQL("INSERT INTO `" + name + " INV` (timestamp," +
                    " shortDescript, longDescript, value, category, comments) " +
            "VALUES(" + d.dataBaseString() + ");");
        } catch (Exception e) {
            e.printStackTrace();
        }
        inventory.addDonation(d);
    }
    public void removeDonation(Donation d) {
        inventory.removeDonation(d);
    }
    public String getName(){
        return name;
    }
    public void setRow(int row) {
        this.row = row;
    }
    public ArrayList<String> getNames() {
        ArrayList<String> ret = inventory.getDonationNames();
        return ret;
    }
    public int getRow(){
        return row;
    }
    public List<Donation> getDonations() {
        return inventory.getDonations();
    }
    public DonationCollection getDonationCollection() {
        return inventory;
    }

    public double getLat() {
        return Double.valueOf(this.latitude);
    }

    public double getLong() {
        return Double.valueOf(this.longitude);
    }

    public String getSnippet() {
        String text = "";
        text += "Phone: " + phone + "\n";
        text += "Website: " + website;
        return text;
    }

    public String toString() {
        String text = "";
        text += "Name: " + name + "\n";
        text += "Latitude: " + latitude + "\n";
        text += "Longitude: " + longitude + "\n";
        text += "Street address: " + address + "\n";
        text += "City: " + city + "\n";
        text += "State: " + state + "\n";
        text += "Zip Code: " + zipcode + "\n";
        text += "Location type: " + type + "\n";
        text += "Phone: " + phone + "\n";
        text += "Website: " + website;
        return text;
    }

    public String logText() {
        return "Location Name: " + name + " LocationNumber: " + row;
    }

    public MarkerOptions makeMarkerOption() {
        return new MarkerOptions().position(new LatLng(this.getLat(), this.getLong()))
                .title(name).snippet(this.getSnippet());
    }
}

