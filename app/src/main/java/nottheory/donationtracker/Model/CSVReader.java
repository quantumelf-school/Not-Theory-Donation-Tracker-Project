package nottheory.donationtracker.Model;

import java.util.ArrayList;
import java.io.InputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class CSVReader {
    private LocationCollection data;
    
    public CSVReader(InputStream is) throws IOException {
        data = new LocationCollection();
        readFile(is);
    }
    
    public void readFile(InputStream is) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
        
        String line;
        line = reader.readLine();
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
            String[] items = line.split(",");
            if (items.length == 11) {
                try {
                    String get_name = DatabaseConnection.sendRawSQL("SELECT * FROM Locations WHERE name = '" + items[1] + "';");
                    if (get_name.equals("")) {
                        DatabaseConnection.sendRawSQL("INSERT INTO Locations (name, address, city, state, type, phone, website, zipcode, latitude, longitude) VALUES" +
                        "('" + items[1] + "', '" + items[2] + "', '" + items[3] + "', '" + items[4] + "', '" + items[5] +
                                "', '" + items[6] + "', '" + items[7] + "', '" + items[8] + "', '" + items[9] + "', '" + items[10] + "');");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        reader.close();
        try {
            String get_locations = DatabaseConnection.sendRawSQL("SELECT * FROM Locations;");
            String delimiter = "\\),\\(";
            String[] location_list = (get_locations.substring(1, get_locations.length() - 1)).split(delimiter);
            for (int i = 0; i < location_list.length; i++) {
                String[] location_parts = location_list[i].substring(1, location_list[i].length() - 1).split("', '");
                Location this_location = new Location(location_parts[0], location_parts[1], location_parts[2], location_parts[3],
                        location_parts[4], location_parts[5], location_parts[6], location_parts[7], location_parts[8],
                        location_parts[9]);
                data.addLocation(this_location);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public LocationCollection getLC(){return data;}
}
