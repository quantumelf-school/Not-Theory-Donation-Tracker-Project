package nottheory.donationtracker.Model;
import java.util.List;
import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * A class to hold and manage all of the donations in a location
 */
public class DonationCollection {
    private final List<Donation> donations = new ArrayList<>();

    /**
     * The default constructor for a DonationCollection which creates an empty collection
     */
    public DonationCollection() {
        //default method instantiates empty donation list
    }

    /**
     * A constructor for the collection which takes an iterable type of donations to add
     * @param d an iterable of Donations to be added to the collection
     */
    public DonationCollection(Iterable<Donation> d){
        for(Donation i: d) {
            donations.add(i);
        }
    }

    /**
     * Adds a donation to the collection
     * @param d the donation we intend to add
     */
    public void addDonation(Donation d) {
        donations.add(d);
    }

    /**
     * Adds all donations from an iterable to the collection
     * @param d the iterable containing the donations we wish to add
     */
    public void addDonations(Iterable<Donation> d) {
        for(Donation i: d) {
            donations.add(i);
        }
    }

    /**
     * Adds all donations from another donationCollection to itself
     * @param d the donationCollection we wish to take all elements from
     */
    public void addDonations(DonationCollection d) {
        List<Donation> list = d.getDonations();
        for(Donation i: list) {
            donations.add(i);
        }
    }

    /**
     * Removes a donation from the collection
     * @param d the donation we wish to remove
     */
    public void removeDonation(Donation d) {
        donations.remove(d);
    }

    /**
     * Gets a list of all of the names of donations in the collection
     * @return an ArrayList of donation names
     */
    public ArrayList<String> getDonationNames() {
        ArrayList<String> ret = new ArrayList<>();
        for(Donation d : donations){
            ret.add(d.getName());
        }
        return ret;
    }

    /**
     * Gets all of the donations which have the same category attribute
     * @param category the category we wish to find all donations from
     * @return an Arraylist of all Donations of the selected category
     */
    public ArrayList<Donation> getDonationsByCategory(String category) { //replace string with enum
        ArrayList<Donation> ret = new ArrayList<>();
        for(Donation d : donations){
            if(d.getCategory().equals(category)) {
                ret.add(d);
            }
        }
        return ret;
    }

//    returns list of donations by name (NOT case-sensitive)

    /**
     * Gets all of the donations with the name in question
     * @param name the name we wish to search by
     * @return an arrayList of all of the donations with the given name
     */
    public ArrayList<Donation> getDonationsByName(String name) {
        ArrayList<Donation> ret = new ArrayList<>();
        for(Donation d : donations){
            String checkName = d.getName().toUpperCase();
            if(checkName.equals(name.toUpperCase())) {
                ret.add(d);
            }
        }
        return ret;
    }

    /**
     * Gets the donation when given it's name
     * @param name the name we wish to search by
     * @return a donation with the name in question
     * @throws NoSuchElementException If the name is not in the collection, NoSuchElement will
     * be thrown
     */
    public Donation getDonationByName(String name) throws NoSuchElementException { //Joey Test
        Donation ret = null;
        for(Donation d : donations){
            String checkName = d.getName().toUpperCase();
            if(checkName.equals(name.toUpperCase())) {
                ret = d;
            }
        }
        if (ret == null) {
            throw new NoSuchElementException();
        }
        return ret;
    }

//    returns list of donations where the name contains given string

    /**
     * Uses a fuzzy search to get all of the donations with a similar name to that passed in
     * @param name the name we want to search by
     * @return All of the donations with similar names to the name passed in
     */
    public ArrayList<Donation> getDonationsBySimilarName(String name) { //replace string with enum
        ArrayList<Donation> ret = new ArrayList<>();
        for(Donation d : donations){
            String checkName = d.getName().toUpperCase();
            if(checkName.contains(name.toUpperCase())) {
                ret.add(d);
            }
        }
        return ret;
    }

    /**
     * Gets the number of donations in the collection
     * @return the number of donations
     */
    public int getNumDonations() {
        return donations.size();
    }

    /**
     * Gets the donations in the collection in list form
     * @return a list of the donations in the collection
     */
    public List<Donation> getDonations() {
        return donations;
    }
}
