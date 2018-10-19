package nottheory.donationtracker.Model;
import java.util.ArrayList;

public class LocationCollection {
    private ArrayList<Location> locations = new ArrayList<>();

    public LocationCollection() {
        //default method instantiates empty locations list
    }
    public LocationCollection(CSVReader reader){
        for(int i = 1; i <= reader.size(); i++) {
            locations.add(new Location(reader, i));
        }
    }
    
    public void addLocation(Location l) {
        locations.add(l);
    }
    public void removeLocation(Location l) {
        locations.remove(l);
    }

    public ArrayList<String> getLocationNames() {
        ArrayList<String> ret = new ArrayList<>();
        for(Location l : locations){
            ret.add(l.getName());
        }
        return ret;
    }

    public ArrayList<Location> getLocations() {
        return locations;
    }

}
