package nottheory.donationtracker;

import org.junit.Before;
import org.junit.Test;

import java.util.NoSuchElementException;

import nottheory.donationtracker.Model.Donation;
import nottheory.donationtracker.Model.DonationCollection;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.fail;

/**
 * Created by Joseph Crawford on 11/11/18.
 * Tests the DonationCollection Model class getDonationByName method (Line 60)
 *
 * The branches to cover are the for loo,p which has an if statement, so there must be elements
 * that are iterated past and an element must be found. There is also a branch if the element
 * name is not in the collection, in which case an exception must be thrown.
 */
public class JoeyUnitTest {
    private DonationCollection testCollection;

    /**
     * Initializes a DonationCollection to test
     */
    @Before
    public void setUp(){
        testCollection = new DonationCollection(); //Feature envy is most concise way to add these
        //elements to the collection for testing purposes
        testCollection.addDonation(new Donation("TEST", "A",
                "TEST","TEST","TEST", "TEST"));
        testCollection.addDonation(new Donation("TEST", "B",
                "TEST","TEST","TEST", "TEST"));
        testCollection.addDonation(new Donation("TEST", "C",
                "TEST","TEST","TEST", "TEST"));
        testCollection.addDonation(new Donation("TEST", "D",
                "TEST","TEST","TEST", "TEST"));
        testCollection.addDonation(new Donation("TEST", "E",
                "TEST","TEST","TEST", "TEST"));
        testCollection.addDonation(new Donation("TEST", "F",
                "TEST","TEST","TEST", "TEST"));
        testCollection.addDonation(new Donation("TEST", "g",
                "TEST","TEST","TEST", "TEST"));
        testCollection.addDonation(new Donation("TEST", "H",
                "TEST","TEST","TEST", "TEST"));
        testCollection.addDonation(new Donation("TEST", "I",
                "TEST","TEST","TEST", "TEST"));
        testCollection.addDonation(new Donation("TEST", "J",
                "TEST","TEST","TEST", "TEST"));
    }

    /**
     * Tests that an element can be found by its name.
     */
    @Test
    public void testFindElementInCollection() {
        String text = "";
        text += "Name: " + "D" + "\n";
        text += "Time Stamp: " + "TEST" + "\n";
        text += "Description: " + "TEST" + "\n";
        text += "Value: " + "TEST" + "\n";
        text += "Category: " + "TEST" + "\n";
        text += "Comments (Optional): " + "TEST" + "\n";
        assertEquals("Element Not correctly found in DonationCollection", text,
                testCollection.getDonationByName("D").toString());
    }
    /**
     * Tests that an element can be found by its name if the case does not match with the query.
     * This achieves total branch coverage beside the exception
     */
    @Test
    public void testFindElementInCollectionUnmatchedCase() {
        String text = "";
        text += "Name: " + "B" + "\n";
        text += "Time Stamp: " + "TEST" + "\n";
        text += "Description: " + "TEST" + "\n";
        text += "Value: " + "TEST" + "\n";
        text += "Category: " + "TEST" + "\n";
        text += "Comments (Optional): " + "TEST" + "\n";
        assertEquals("Uppercase element not correctly found " +
                        "in DonationCollection when searching with lowercase name", text,
                testCollection.getDonationByName("b").toString());
    }
    /**
     * Tests that an element can be found by its name if both are lowercase.
     */
    @Test
    public void testFindElementInCollectionLowercase() {
        String text = "";
        text += "Name: " + "g" + "\n";
        text += "Time Stamp: " + "TEST" + "\n";
        text += "Description: " + "TEST" + "\n";
        text += "Value: " + "TEST" + "\n";
        text += "Category: " + "TEST" + "\n";
        text += "Comments (Optional): " + "TEST" + "\n";
        assertEquals("Lowercase element not correctly found in DonationCollection", text,
                testCollection.getDonationByName("g").toString());
    }
    /**
     * Tests that an element can be found by its name if you search in Uppercase.
     */
    @Test
    public void testFindElementInCollectionUnmatchedCaseLowercase() {
        String text = "";
        text += "Name: " + "g" + "\n";
        text += "Time Stamp: " + "TEST" + "\n";
        text += "Description: " + "TEST" + "\n";
        text += "Value: " + "TEST" + "\n";
        text += "Category: " + "TEST" + "\n";
        text += "Comments (Optional): " + "TEST" + "\n";
        assertEquals("Lowercase element not correctly found in DonationCollection " +
                        "when searching with uppercase name", text,
                testCollection.getDonationByName("G").toString());
    }
    /**
     * Tests that a name queried that is not in the collection will throw NoSuchElementException
     * This achieves total coverage as the for loop has been run and the exception has been tested
     */
    @Test
    public void testThrowNoSuchElement() {
        try {
            testCollection.getDonationByName("K");
            fail("NoSuchElementException not thrown when a donation by a name not " +
                    "in the collection is passed in");
        } catch (NoSuchElementException e) {
            //Test Passed
        }
    }
}
