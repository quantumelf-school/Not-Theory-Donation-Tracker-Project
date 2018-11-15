package nottheory.donationtracker.Model;

import android.util.Log;

import java.io.InputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class CSVReader {
    private final LocationCollection data;
    
    public CSVReader() {
        data = new LocationCollection();
    }

    //This method has feature envy for DatabaseConnection. However, it is unintuitive and wouldn't
    //make things easier to understand to combine all of the sendRawSQL methods into one method.
    //We created CSVReader and DatabaseConnection in order to make it separate from the overall app
    //as individual tools, so it wouldn't make sense to combine them. There are also many other
    //cases of using sendRawSQL, so we shouldn't make a specialized method for this case.
    public void readFile(InputStream is) throws IOException {
        final int FILE_LENGTH = 11;
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(is, StandardCharsets.UTF_8));

        String line;
        reader.readLine();
        while ((line = reader.readLine()) != null) {
            String[] items = line.split(",");
            if (items.length == FILE_LENGTH) {
                try {
                    String get_name = DatabaseConnection.sendRawSQL(
                            "SELECT * FROM Locations WHERE name = '" + items[1] + "';");
                    if ("".equals(get_name)) {
                        DatabaseConnection.sendRawSQL("INSERT INTO Locations (name, address, city, state, type, phone, website, zipcode, latitude, longitude) VALUES" +
                        "('" + items[1] + "', '" + items[2] + "', '" + items[3] + "', '" + items[4] + "', '" + items[5] +
                                "', '" + items[6] + "', '" + items[7] + "', '" + items[8] + "', '" + items[9] + "', '" + items[10] + "');");
                        Log.d("@JT LOCATION INSERTED","Location Name: " + items[1] + "; get_name was: " + get_name);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        reader.close();
        try {
            String get_locations = DatabaseConnection.sendRawSQL("SELECT name, address, city, state, type, phone, website, zipcode, latitude, longitude FROM Locations;");
            String delimiter = "\\),\\(";
            Log.d("myApp", "DB QUERY RESULT: " + get_locations);
            String[] location_list = (get_locations.substring(1, get_locations.length() - 1)).split(delimiter);
            for (String location_x: location_list) {
                String[] location_parts = location_x.substring(1, location_x.length() - 1).split("', '");
                Location this_location = new Location(location_parts[0], location_parts[1], location_parts[2], location_parts[3],
                        location_parts[4], location_parts[5], location_parts[6], location_parts[7], location_parts[8],
                        location_parts[9]);
            }

        } catch (Exception e) {
            e.printStackTrace();
            assert true : e.getStackTrace();
        }
    }

    public LocationCollection getLC(){return data;}
}
