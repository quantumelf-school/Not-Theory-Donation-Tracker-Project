package nottheory.donationtracker;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.ArrayList;

import nottheory.donationtracker.Model.Location;
import nottheory.donationtracker.Model.LocationCollection;

/**
 * Tests the LocationCollection.getLocationByName method
 */
public class JTUnitTest {
    private List<Location> referenceLocations;
    private LocationCollection testNonEmpty;
    private LocationCollection testEmpty;

    /**
     * Setup for the code instantiating the reference list
     * and the two collections for testing
     */
    @Before
    public void setUp() {
        referenceLocations = new ArrayList<>();

         referenceLocations.add(new Location("LocationA", "1", "2",
                "TEST", "Atlanta", "GA", "33333", "Drop Off",
                "(212) 434-4477", "nottheory.com"));
        referenceLocations.add(new Location("LocationB", "3", "4",
                "217 TEST", "Columbia", "SC", "44444", "Store",
                "(888) 888-8888", "theory.com"));
        referenceLocations.add(new Location("LocationC", "5", "6",
                "TEST 33", "Raleigh", "NC", "55555", "Drop Off",
                "(333) 333-3344", "lol.com"));
        referenceLocations.add(new Location("LocationD", "7", "8",
                "TEST 11", "Roanoke", "VA", "11111", "Warehouse",
                "(555) 555-5544", "testsite.com"));
        referenceLocations.add(new Location("LocationE", "9", "10",
                "32 TEST", "Tampa", "FL", "88888", "Store",
                "(070) 707-7700", "NSA.gov"));

        testNonEmpty = new LocationCollection();

        for (Location l : referenceLocations) {
            testNonEmpty.addLocation(l);
        }

        testEmpty = new LocationCollection();
    }

    /**
     * tests finding a location in a collection
     * that should appear at the beginning of the collection
     */
    @Test
    public void testBeginning() {
        Location res = testNonEmpty.getLocationByName("LocationA");
        Assert.assertEquals(referenceLocations.get(0), res);
    }

    /**
     * tests finding a location in a collection
     * that should appear at the end of the collection
     */
    @Test
    public void testEnd() {
        Location res = testNonEmpty.getLocationByName("LocationE");
        Assert.assertEquals(referenceLocations.get(4), res);
    }

    /**
     * tests finding a location in a collection
     * that should appear in the middle of the collection
     */
    @Test
    public void testMiddle() {
        Location res = testNonEmpty.getLocationByName("LocationC");
        Assert.assertEquals(referenceLocations.get(2), res);
    }

    /**
     * tests the result of getting location
     * that is not in the collection from
     * a non-empty collection
     * (should return null)
     */
    @Test
    public void testNonEmptyNoContain() {
        Location res = testNonEmpty.getLocationByName("NoSuchLocation");
        Assert.assertNull(res);
    }

    /**
     * tests the result of getting from an empty collection
     * (should return null)
     */
    @Test
    public void testEmpty() {
        Location res = testEmpty.getLocationByName("Location");
        Assert.assertNull(res);
    }
}
