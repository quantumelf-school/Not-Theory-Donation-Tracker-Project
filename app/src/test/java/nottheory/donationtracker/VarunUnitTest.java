package nottheory.donationtracker;

import nottheory.donationtracker.Model.Donation;
import nottheory.donationtracker.Model.LocationCollection;
import nottheory.donationtracker.Model.Location;

import java.util.List;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertEquals;

/**
 * Created by Varun Srikanth on 11/15
 * Tests the LocationCollection Model class getLocationWithDonation method (line 89)
 */
public class VarunUnitTest {

    private LocationCollection locationCollection;
    private List<Location> locations;
    private List<Donation> donations;

    /**
     * Creates the necessary objects to conduct testing
     */
    @Before
    public void setUp() {
        donations = new ArrayList<>();
        donations.add(new Donation("TEST", "A", "TEST", "TEST",
                "TEST", "TEST"));
        donations.add(new Donation("TEST", "B", "TEST", "TEST",
                "TEST", "TEST"));
        donations.add(new Donation("TEST", "C", "TEST", "TEST",
                "TEST", "TEST"));
        donations.add(new Donation("TEST", "D", "TEST", "TEST",
                "TEST", "TEST"));
        donations.add(new Donation("TEST", "E", "TEST", "TEST",
                "TEST", "TEST"));
        donations.add(new Donation("TEST", "F", "TEST", "TEST",
                "TEST", "TEST"));
        
        Location loc1 = new Location("loc1", "TEST", "TEST", "TEST",
                "TEST", "TEST", "TEST", "TEST", "TEST", "TEST");
        loc1.addDonation(donations.get(0));
        loc1.addDonation(donations.get(1));
        loc1.addDonation(donations.get(2));
        
        Location loc2 = new Location("loc2", "TEST", "TEST", "TEST",
                "TEST", "TEST", "TEST", "TEST", "TEST", "TEST");
        loc2.addDonation(donations.get(3));
        loc2.addDonation(donations.get(4));
        
        Location loc3 = new Location("loc3", "TEST", "TEST", "TEST",
                "TEST", "TEST", "TEST", "TEST", "TEST", "TEST");
        loc3.addDonation(donations.get(5));
        
        Location loc4 = new Location("loc3", "TEST", "TEST", "TEST",
                "TEST", "TEST", "TEST", "TEST", "TEST", "TEST");

        locations = new ArrayList<>();
        locations.add(loc1);
        locations.add(loc2);
        locations.add(loc3);
        locations.add(loc4);
        
        locationCollection = new LocationCollection(locations);
    }

    /**
     * Test to search for a location by a donation for all valid donations
     */
    @Test
    public void testFindLocationInCollectionWithValidDonation() {
        assertSame("Location not found correctly", locations.get(0),
            locationCollection.getLocationWithDonation(donations.get(0)));
        assertSame("Location not found correctly", locations.get(0),
            locationCollection.getLocationWithDonation(donations.get(1)));
        assertSame("Location not found correctly", locations.get(0),
            locationCollection.getLocationWithDonation(donations.get(2)));
        assertSame("Location not found correctly", locations.get(1),
            locationCollection.getLocationWithDonation(donations.get(3)));
        assertSame("Location not found correctly", locations.get(1),
            locationCollection.getLocationWithDonation(donations.get(4)));
        assertSame("Location not found correctly", locations.get(2),
            locationCollection.getLocationWithDonation(donations.get(5)));
    }

    /**
     * Test to search for a location with a donation that no location has
     */
    @Test
    public void testNoLocationWithDonation() {
        Donation d = new Donation("TEST", "X", "TEST", "TEST",
                "TEST", "TEST");
        assertEquals("Improperly returned a location", null,
            locationCollection.getLocationWithDonation(d));
    }

    /**
     * Test to search for a location by donation and ensure it is looking for the name of
     * the donation and not the exact same Donation object
     */
    @Test
    public void testObjectEqualityVSReferenceEquality() {
        Donation d = new Donation("TEST", "A", "TEST", "TEST",
                "TEST", "TEST");
        assertSame("Location not found correctly", locations.get(0),
            locationCollection.getLocationWithDonation(d));
    }

    /**
     * Test to ensure method works when null Donation object is passed in
     */
    @Test
    public void testNullDonation() {
        assertEquals("Failed to handle null donation", null,
            locationCollection.getLocationWithDonation(null));
    }

    /**
     * Test to ensure the method works when there are no locations
     */
    @Test
    public void testNoLocationsInCollection() {
        LocationCollection lc = new LocationCollection();
        Donation d = new Donation("TEST", "A", "TEST", "TEST",
                "TEST", "TEST");
        assertEquals("Returned an unexpected location", null,
                lc.getLocationWithDonation(d));
    }
}
