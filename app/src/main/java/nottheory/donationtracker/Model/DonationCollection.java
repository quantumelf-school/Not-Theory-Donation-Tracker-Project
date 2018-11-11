package nottheory.donationtracker.Model;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;

public class DonationCollection {
    private ArrayList<Donation> donations = new ArrayList<>();

    public DonationCollection() {
        //default method instantiates empty donation list
    }
    public DonationCollection(ArrayList<Donation> d){
        for(Donation i: d) {
            donations.add(i);
        }
    }

    public void addDonation(Donation d) {
        donations.add(d);
    }
    public void addDonations(ArrayList<Donation> d) {
        for(Donation i: d) {
            donations.add(i);
        }
    }
    public void addDonations(DonationCollection d) {
        ArrayList<Donation> list = d.getDonations();
        for(Donation i: list) {
            donations.add(i);
        }
    }

    public void removeDonation(Donation d) {
        donations.remove(d);
    }

    public ArrayList<String> getDonationNames() {
        ArrayList<String> ret = new ArrayList<>();
        for(Donation d : donations){
            ret.add(d.getName());
        }
        return ret;
    }

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
    public ArrayList<Donation> getDonationsByName(String name) { //replace string with enum
        ArrayList<Donation> ret = new ArrayList<>();
        name = name.toUpperCase();
        for(Donation d : donations){
            String checkName = d.getName().toUpperCase();
            if(checkName.equals(name)) {
                ret.add(d);
            }
        }
        return ret;
    }
    public Donation getDonationByName(String name) throws NoSuchElementException { //Joey Test
        Donation ret = null;
        name = name.toUpperCase();
        for(Donation d : donations){
            String checkName = d.getName().toUpperCase();
            if(checkName.equals(name)) {
                ret = d;
            }
        }
        if (ret == null) {
            throw new NoSuchElementException();
        }
        return ret;
    }

//    returns list of donations where the name contains given string
    public ArrayList<Donation> getDonationsBySimilarName(String name) { //replace string with enum
        ArrayList<Donation> ret = new ArrayList<>();
        name = name.toUpperCase();
        for(Donation d : donations){
            String checkName = d.getName().toUpperCase();
            if(checkName.contains(name)) {
                ret.add(d);
            }
        }
        return ret;
    }

    public int getNumDonations() {
        return donations.size();
    }

    public ArrayList<Donation> getDonations() {
        return donations;
    }
}
