package nottheory.donationtracker.Model;
import java.io.Serializable;
import java.util.ArrayList;
import nottheory.donationtracker.Model.Donation;

public class Location {
    private String name, address, city, state, type, phone, website, zipcode, latitude, longitude;
    private DonationCollection inventory = new DonationCollection();
    //private ArrayList<Donation> inventory = new ArrayList<>();
    private int row = 0;

    public Location(String name, String latitude, String longitude, String address, String city, String state, String zipcode, String type, String phone, String website) {
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
            DatabaseConnection.sendRawSQL("CREATE TABLE IF NOT EXISTS `" + name + " INV` (donation_id INT AUTO_INCREMENT, timestamp TEXT, shortDescript TEXT, longDescript TEXT, value TEXT, category TEXT, comments TEXT, PRIMARY KEY (donation_id))  ENGINE=INNODB;");
            String locationinventory = DatabaseConnection.sendRawSQL("SELECT timestamp, shortDescript, longDescript, value, category, comments from `" + name + " Inv`");
            if (!locationinventory.equals("")) {
                String delimiter = "\\),\\(";
                String[] donation_list = (locationinventory.substring(1, locationinventory.length() - 1)).split(delimiter);
                for (int i = 0; i < donation_list.length; i++) {
                    String[] donation_parts = donation_list[i].substring(1, donation_list[i].length() - 1).split("', '");
                    Donation this_donation = new Donation(donation_parts[0], donation_parts[1], donation_parts[2], donation_parts[3],
                            donation_parts[4], donation_parts[5]);
                    inventory.addDonation(this_donation);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void addDonation(Donation d) {
        try {
            DatabaseConnection.sendRawSQL("INSERT INTO `" + name + " INV` (timestamp, shortDescript, longDescript, value, category, comments) " +
            "VALUES('" + d.dataBaseString() + ");");
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
    public ArrayList<Donation> getDonations() {
        return inventory.getDonations();
    }
    public DonationCollection getDonationCollection() {
        return inventory;
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
}

