package nottheory.donationtracker.Model;
import java.util.ArrayList;
import nottheory.donationtracker.Model.Donation;

public class Location {
    private String name, address, city, state, type, phone, website, zipcode, latitude, longitude;
    private ArrayList<Donation> inventory = new ArrayList<>();
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
    }
    public Location(CSVReader reader, int row){
        this(reader.getData(row,1), reader.getData(row,2), reader.getData(row,3), reader.getData(row,4)
                , reader.getData(row,5), reader.getData(row,6), reader.getData(row,7), reader.getData(row,8), reader.getData(row,9), reader.getData(row,10));
        this.row = row;
    }
    public void addDonation(Donation d) {
        inventory.add(d);
    }
    public void removeDonation(Donation d) {
        inventory.remove(d);
    }
    public String getName(){
        return name;
    }
    public void setRow(int row) {
        this.row = row;
    }
    public ArrayList<String> getNames() {
        ArrayList<String> ret = new ArrayList<>();
        for(Donation d : inventory){
            ret.add(d.getName());
        }
        return ret;
    }
    public int getRow(){
        return row;
    }
    public ArrayList<Donation> getDonations() {
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

