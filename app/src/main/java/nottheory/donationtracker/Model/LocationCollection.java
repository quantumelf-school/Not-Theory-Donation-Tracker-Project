package nottheory.donationtracker.Model;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * structurer class that collects locations together for easy lookup
 * and aggregate data
 */
public class LocationCollection {
    private final ArrayList<Location> locations = new ArrayList<>();
    private /*static*/ int nextRow; //the id of a location is defined by its row in the .csv file
    // or the order in which it was added


    /**
     * default constructor for location collection
     * initializing fields to their defaults
     */
    public LocationCollection() {
        //default method instantiates empty locations list
    }


    /**
     * constructs a location collection out of a
     * list of locations
     * @param list the list of locations to be included in
     *             the location list
     */
    public LocationCollection(Iterable<Location> list) {
        for (Location l : list) {
            locations.add(l);
        }
    }

    /**
     * adds a location to the collection
     * @param l the location to add
     */
    public void addLocation(Location l) {

        locations.add(l);
        l.setRow(nextRow);
        nextRow++;
        Log.d("LOCATION ADDED", l.logText());
    }

    /**
     * gets a location based on its number of appearance
     * or the row number in which it appears in the CSV
     * @param row the row/appearance number
     * @return the location at the row number
     */
    public Location getLocationFromRow(int row) {
        for(Location l: locations) {
            Log.d("LOCATION SEARCHED", l.logText());
            if (l.getRow() == row) {
                return l;
            }
        }
        Log.d("LOCATION NOT FOUND", "ROW NUMBER ATTEMPTED" + row);
        return null;
    }

    /**
     * gets a location object with the name passed
     * in
     * @param name the name of the location to retrieve
     * @return the location object or null if it is not found
     */
    public Location getLocationByName(String name) {
        for (Location l : locations) {
            String thisName = l.getName();
            if (thisName.equals(name)) {
                return l;
            }
        }
        return null;
    }

    /**
     * looks for a location containing
     * the passed in donation object
     * @param d donation object to search by
     * @return the location containing donation d
     */
    public Location getLocationWithDonation(Donation d) {
        if (d == null) {
            return null;
        }
        for(Location l: locations) {
            for(Donation don: l.getDonations()) {
                String donationName = don.getName();
                if (donationName.equals(d.getName())) {
                    return l;
                }
            }
        }
        return null;
    }

    /**
     * returns a list of location names in
     * the location collection
     * @return a list of the names of the locations
     * in the location collection
     */
    public List<String> getLocationNames() {
        List<String> ret = new ArrayList<>();
        for(Location l : locations){
            ret.add(l.getName());
        }
        return ret;
    }

    /**
     * getter for the number of locations
     * in the location collection
     * @return the number of locations in the
     * collection
     */
    public int getNumLocations() {
        return locations.size();
    }

    /**
     * generates and returns an array list
     * containing all the donations of all
     * the locations in the collection
     * @return the list of all the donations
     * belonging to the locations in the collection
     */
    public List<Donation> getAllDonationsAL() {
       List<Donation> ret = new ArrayList<>();
        for(Location l: locations) {
                ret.addAll(l.getDonations());
        }
        return ret;
    }


    /**
     * getter for the arrayList of locations
     * contained by the collection
     * @return the arrayList of locations in the
     * collection
     */
    public ArrayList<Location> getLocations() {
        return locations;
    }

    /**
     * Adds a donation to certain location given its name.
     * @param name the name of the location
     * @param donation the donation object to be added
     */
    public void addDonationToLocationByName(String name, Donation donation) {
        Location l = getLocationByName(name);
        l.addDonation(donation);
    }
}
