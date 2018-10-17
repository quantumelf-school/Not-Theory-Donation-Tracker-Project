import java.io.*;
import java.util.ArrayList;
import java.nio.charset.StandardCharsets;

import nottheory.donationtracker.R;

public class CSVReader {
    private ArrayList<String[]> data;
    
    public CSVReader() throws IOException {
        data = new ArrayList<String[]>();
        readFile();
    }
    
    public void readFile() throws IOException {
        InputStream inputStream = getResources().openRawResource(R.raw.sample);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
        
        String line;
        while ((line = reader.readLine()) != null) {
            String[] items = line.split(",");
            if (items.length == 11) {
                data.add(items);
            }
        }
        reader.close();
    }
    
    public String getData(int locationKey, int fieldID) {
        return data.get(locationKey)[fieldID];
    }
    
    public int size() {
        return data.size() - 1;
    }
}