package nottheory.donationtracker.Model;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class LocationCollection {
    private ArrayList<Location> locations = new ArrayList<>();
    private /*static*/ int nextRow; //the id of a location is defined by its row in the .csv file or the order in which it was added


    public LocationCollection() {
        //default method instantiates empty locations list
    }


    public LocationCollection(Iterable<Location> list) {
        for (Location l : list) {
            locations.add(l);
        }
    }

    public void addLocation(Location l) {

        locations.add(l);
        l.setRow(nextRow);
        nextRow++;
        Log.d("@JT LOCATION ADDED", "Location Name: " + l.getName() + " LocationNumber: " + l.getRow());
    }
    public void removeLocation(Location l) {
        locations.remove(l);
    }

    public Location getLocationFromRow(int row) {
        for(Location l: locations) {
            Log.d("@JT LOCATION SEARCHED", "Location: " + l.getName() + " RowNumber: " + l.getRow());
            if (l.getRow() == row) {
                return l;
            }
        }
        Log.d("@JT LOCATION NOT FOUND", "ROW NUMBER ATTEMPTED" + row);
        return null;
    }

    public Location getLocationByName(String name) {
        for (Location l : locations) {
            if (l.getName().equals(name)) {
                return l;
            }
        }
        return null;
    }

    public Location getLocationWithDonation(Donation d) {
        for(Location l: locations) {
            for(Donation don: l.getDonations()) {
                if (don.getName().equals(d.getName())) {
                    return l;
                }
            }
        }
        return null;
    }

    public List<String> getLocationNames() {
        List<String> ret = new ArrayList<>();
        for(Location l : locations){
            ret.add(l.getName());
        }
        return ret;
    }

    public int getNumLocations() {
        return locations.size();
    }
    public DonationCollection getAllDonations() {
        DonationCollection ret = new DonationCollection();
        for(Location l: locations) {
            ret.addDonations(l.getDonationCollection());
        }
        return ret;
    }
    public List<Donation> getAllDonationsAL() {
       List<Donation> ret = new ArrayList<>();
        for(Location l: locations) {
            for(Donation d: l.getDonations()) {
                ret.add(d);
            }
        }
        return ret;
    }

    public ArrayList<Location> getLocations() {
        return locations;
    }

}
