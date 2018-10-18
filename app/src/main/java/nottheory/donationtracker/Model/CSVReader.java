import java.util.ArrayList;
import java.io.InputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class CSVReader {
    private ArrayList<String[]> data;
    
    public CSVReader(InputStream is) throws IOException {
        data = new ArrayList<String[]>();
        readFile(is);
    }
    
    public void readFile(InputStream is) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
        
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
