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
     * Gets all of the donations which have the same category attribute
     * @param category the category we wish to find all donations from
     * @return an Arraylist of all Donations of the selected category
     */
    public ArrayList<Donation> getDonationsByCategory(String category) { //replace string with enum
        ArrayList<Donation> ret = new ArrayList<>();
        for(Donation d : donations){
            String thisCategory = d.getCategory();
            if(thisCategory.equals(category)) {
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
            String rawName = d.getName();
            String checkName = rawName.toUpperCase();
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
            String rawName = d.getName();
            String checkName = rawName.toUpperCase();
            if(checkName.contains(name.toUpperCase())) {
                ret.add(d);
            }
        }
        return ret;
    }

    /**
     * Gets the donations in the collection in list form
     * @return a list of the donations in the collection
     */
    public List<Donation> getDonations() {
        return donations;
    }
}
