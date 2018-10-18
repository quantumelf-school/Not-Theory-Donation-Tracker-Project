package nottheory.donationtracker.Model;
import java.util.ArrayList;

public class Location {
    private String name, address, city, state, type, phone, website, zipcode, latitude, longitude;
    private ArrayList<Donation> inventory = new ArrayList();

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
    }
    public String toString() {
        String text = "";
        text += "Name: " + name + "\n";
        text += "Latitude: " + latitude + "\n";
        text += "Longitude: " + longitude + "\n";
        text += "Street address: " + address + "\n";
        text += "City: " + city + "\n";
        text += "State: " + state + "\n";
        text += "Zipcode: " + zipcode + "\n";
        text += "Location type: " + type + "\n";
        text += "Phone: " + phone + "\n";
        text += "Website: " + website;
        return text;
    }
}

