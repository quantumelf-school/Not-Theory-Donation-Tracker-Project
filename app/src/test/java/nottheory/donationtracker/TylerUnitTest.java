package nottheory.donationtracker;

import org.junit.Before;
import org.junit.Test;

import nottheory.donationtracker.Model.Donation;
import nottheory.donationtracker.Model.DonationCollection;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by Joseph Crawford on 11/11/18.
 * Tests the DonationCollection Model class getDonationByName method (Line 60)
 *
 * The branches to cover are the for loo,p which has an if statement, so there must be elements
 * that are iterated past and an element must be found. There is also a branch if the element
 * name is not in the collection, in which case an exception must be thrown.
 */
public class TylerUnitTest {
    private DonationCollection testCollection;

    /**
     * Initializes a DonationCollection to test
     */
    @Before
    public void setUp(){
        testCollection = new DonationCollection(); //Feature envy is most concise way to add these
        //elements to the collection for testing purposes
        testCollection.addDonation(new Donation("TEST", "aaAaAA",
                "TEST","TEST","TEST", "TEST"));
        testCollection.addDonation(new Donation("TEST", "aaaaaa",
                "TEST","TEST","TEST", "TEST"));
        testCollection.addDonation(new Donation("TEST", "abcd",
                "TEST","TEST","TEST", "TEST"));
        testCollection.addDonation(new Donation("TEST", "abde",
                "TEST","TEST","TEST", "TEST"));
        testCollection.addDonation(new Donation("TEST", "yeet",
                "TEST","TEST","TEST", "TEST"));
        testCollection.addDonation(new Donation("TEST", "yeet1",
                "TEST","TEST","TEST", "TEST"));
        testCollection.addDonation(new Donation("TEST", "yeet2",
                "TEST","TEST","TEST", "TEST"));
        testCollection.addDonation(new Donation("TEST", "name with spaces",
                "TEST","TEST","TEST", "TEST"));
        testCollection.addDonation(new Donation("TEST", "namewithoutspaces",
                "TEST","TEST","TEST", "TEST"));
    }

    /**
     * Tests that elements with same string are found
     */
    @Test
    public void testYeets() {
        DonationCollection newCollection = new DonationCollection();
        newCollection.addDonation(new Donation("TEST", "yeet",
                "TEST","TEST","TEST", "TEST"));
        newCollection.addDonation(new Donation("TEST", "yeet1",
                "TEST","TEST","TEST", "TEST"));
        newCollection.addDonation(new Donation("TEST", "yeet2",
                "TEST","TEST","TEST", "TEST"));
        assertEquals("Yeets Not correctly found in DonationCollection", newCollection.getDonations(),
                testCollection.getDonationsBySimilarName("yeet"));
    }

    /**
     * Tests that exact name is found when searched
     */
    @Test
    public void testOneYeet() {
        DonationCollection newCollection = new DonationCollection();
        newCollection.addDonation(new Donation("TEST", "yeet1",
                "TEST","TEST","TEST", "TEST"));
        assertEquals("Element yeet1 not correctly found in DonationCollection", newCollection.getDonations(),
                testCollection.getDonationsBySimilarName("yeet1"));
    }

    /**
     * Tests that elements with different case are found
     */
    @Test
    public void testCase() {
        DonationCollection newCollection = new DonationCollection();
        newCollection.addDonation(new Donation("TEST", "aaAaAA",
                "TEST","TEST","TEST", "TEST"));
        newCollection.addDonation(new Donation("TEST", "aaaaaa",
                "TEST","TEST","TEST", "TEST"));
        assertEquals("Cases checked in DonationCollection", newCollection.getDonations(),
                testCollection.getDonationsBySimilarName("aaaaaa"));
        assertEquals("Cases checked in DonationCollection", newCollection.getDonations(),
                testCollection.getDonationsBySimilarName("AAAAAA"));
        assertEquals("Cases checked in DonationCollection", newCollection.getDonations(),
                testCollection.getDonationsBySimilarName("AaAaaA"));
    }

    /**
     * Tests that spaces are counted and nothing is returned when nothing matches
     */
    @Test
    public void testEmpty() {
        DonationCollection newCollection = new DonationCollection();
        assertEquals("Elements found in DonationCollection when none exist", newCollection.getDonations(),
                testCollection.getDonationsBySimilarName("ab cd"));
        newCollection.addDonation(new Donation("TEST", "name with spaces",
                "TEST","TEST","TEST", "TEST"));
        assertEquals("Elements incorrectly found in DonationCollection", newCollection.getDonations(),
                testCollection.getDonationsBySimilarName("name with"));
        newCollection.addDonation(new Donation("TEST", "namewithoutspaces",
                "TEST","TEST","TEST", "TEST"));
        assertEquals("Elements incorrectly found in DonationCollection", newCollection.getDonations(),
                testCollection.getDonationsBySimilarName("name"));
    }

    /**
     * Tests that substring at end of name is checked
     */
    @Test
    public void testEnd() {
        DonationCollection newCollection = new DonationCollection();
        newCollection.addDonation(new Donation("TEST", "name with spaces",
                "TEST","TEST","TEST", "TEST"));
        newCollection.addDonation(new Donation("TEST", "namewithoutspaces",
                "TEST","TEST","TEST", "TEST"));
        assertEquals("Cases checked in DonationCollection", newCollection.getDonations(),
                testCollection.getDonationsBySimilarName("aces"));
    }

    /**
     * Tests that empty string returns all donations
     */
    @Test
    public void testEmptyString() {
        assertEquals("Cases checked in DonationCollection", testCollection.getDonations(),
                testCollection.getDonationsBySimilarName(""));
    }
}
