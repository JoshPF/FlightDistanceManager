package flight_distance.flight;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Tests the Flight class to ensure the information for each Flight is correct,
 * and that Flights are being properly compared
 * 
 * @author jpferrer
 */
public class FlightTest {

	/**
	 * Tests the methods of the Flight class
	 */
	@Test
	public void test() {
		// 2015,12,20,7,UA,346,ORD,MIA,0730,0730,1197,1139,-20
		Flight f1 = new Flight("UA", "346", "ORD", "MIA", 0);
		Flight f2 = new Flight("AA", "123", "CLT", "PIT", 800);
		Flight f3 = new Flight("JB", "456", "PIT", "ATL", 1000);
		Flight f4 = new Flight("UA", "123", "ORD", "MIA", 100);
		Flight f5 = new Flight("UA", "346", "CLT", "MIA", 100);
		Flight f6 = new Flight("UA", "346", "ORD", "PIT", 200);
		Flight f7 = new Flight("UA", "346", "ORD", "MIA", 500);
		assertFalse(f1.equals(f2));
		assertFalse(f1.equals(f4));
		assertFalse(f1.equals(f5));
		assertFalse(f1.equals(f6));
		assertTrue(f1.equals(f7));

		assertEquals(156357062, f1.hashCode());
		assertTrue(f1.compareTo(f2) > 0);
		assertTrue(f3.compareTo(f1) < 0);
		assertTrue(f1.compareTo(f1) == 0);
		assertEquals("MIA", f1.getDestinationAirport());
		assertEquals("123", f2.getFlightNum());
		assertEquals("PIT", f3.getOriginAirport());
	}

}
