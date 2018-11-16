package nottheory.donationtracker;

import org.junit.Before;
import org.junit.Test;

import nottheory.donationtracker.Model.Donation;
import nottheory.donationtracker.Model.DonationCollection;

import static junit.framework.TestCase.assertEquals;

/**
 *
 * Tests the method getDonationBySimilarName in DonationCollection.java
 * Used for the search feature
 * Null is not tested since the only thing that uses this always passes
 * in at least an empty string
 *
 */
public class TylerUnitTest {
    private DonationCollection testCollection;

    /**
     * Sets up a donation collection with various names with spaces, capitals, similar substrings
     */
    @Before
    public void setUp(){
        testCollection = new DonationCollection();
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
        testCollection.addDonation(new Donation("TEST", "1234456789z",
                "TEST","TEST","TEST", "TEST"));
        testCollection.addDonation(new Donation("TEST", "123784206978",
                "TEST","TEST","TEST", "TEST"));
    }

    /**
     * Tests that elements with same string are found ("yeet1", "yeet2", etc.)
     */
    @Test
    public void testSameSubstringName() {
        DonationCollection newCollection = new DonationCollection();
        newCollection.addDonation(new Donation("TEST", "yeet",
                "TEST","TEST","TEST", "TEST"));
        newCollection.addDonation(new Donation("TEST", "yeet1",
                "TEST","TEST","TEST", "TEST"));
        newCollection.addDonation(new Donation("TEST", "yeet2",
                "TEST","TEST","TEST", "TEST"));
        assertEquals("Substrings not correctly found", newCollection.getDonations(),
                testCollection.getDonationsBySimilarName("yeet"));
    }

    /**
     * Tests that exact name is found when searched ("yeet1")
     */
    @Test
    public void testExactName() {
        DonationCollection newCollection = new DonationCollection();
        newCollection.addDonation(new Donation("TEST", "yeet1",
                "TEST","TEST","TEST", "TEST"));
        assertEquals("Element not correctly found, or extra elements found", newCollection.getDonations(),
                testCollection.getDonationsBySimilarName("yeet1"));
    }

    /**
     * Tests that elements with different case but same string are both found
     */
    @Test
    public void testCase() {
        DonationCollection newCollection = new DonationCollection();
        newCollection.addDonation(new Donation("TEST", "aaAaAA",
                "TEST","TEST","TEST", "TEST"));
        newCollection.addDonation(new Donation("TEST", "aaaaaa",
                "TEST","TEST","TEST", "TEST"));
        assertEquals("Cases were checked", newCollection.getDonations(),
                testCollection.getDonationsBySimilarName("aaaaaa"));
        assertEquals("Cases were checked", newCollection.getDonations(),
                testCollection.getDonationsBySimilarName("AAAAAA"));
        assertEquals("Cases were checked", newCollection.getDonations(),
                testCollection.getDonationsBySimilarName("AaAaaA"));
    }

    /**
     * Tests that spaces are counted and nothing is returned when nothing matches
     * Test mostly applies to future changes in the method
     */
    @Test
    public void testEmpty() {
        DonationCollection newCollection = new DonationCollection();
        assertEquals("Non applicable donations returned", newCollection.getDonations(),
                testCollection.getDonationsBySimilarName("ab cd"));
        newCollection.addDonation(new Donation("TEST", "name with spaces",
                "TEST","TEST","TEST", "TEST"));
        assertEquals("Non applicable donations returned", newCollection.getDonations(),
                testCollection.getDonationsBySimilarName("name with"));
        newCollection.addDonation(new Donation("TEST", "namewithoutspaces",
                "TEST","TEST","TEST", "TEST"));
        assertEquals("Non applicable donations returned", newCollection.getDonations(),
                testCollection.getDonationsBySimilarName("name"));
    }

    /**
     * Tests that substring of numbers is correctly found
     */
    @Test
    public void testNumbers() {
        DonationCollection newCollection = new DonationCollection();
        newCollection.addDonation(new Donation("TEST", "123784206978",
                "TEST","TEST","TEST", "TEST"));
        assertEquals("Number substring not checked correctly", newCollection.getDonations(),
                testCollection.getDonationsBySimilarName("42069"));
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
        assertEquals("End of strings not checked", newCollection.getDonations(),
                testCollection.getDonationsBySimilarName("aces"));
        DonationCollection newCollection2 = new DonationCollection();
        newCollection2.addDonation(new Donation("TEST", "1234456789z",
                "TEST","TEST","TEST", "TEST"));
        assertEquals("End of strings not checked", newCollection2.getDonations(),
                testCollection.getDonationsBySimilarName("z"));
    }

    /**
     * Tests that empty string returns all donations
     */
    @Test
    public void testEmptyString() {
        assertEquals("Empty string does not return all", testCollection.getDonations(),
                testCollection.getDonationsBySimilarName(""));
    }
}
