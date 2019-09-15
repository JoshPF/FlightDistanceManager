package flight_distance.cardholder;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import flight_distance.airline.Airline;
import flight_distance.flight.Flight;
import flight_distance.util.ArrayList;

/**
 * Tests the Cardholder class to ensure the information for each Cardholder is
 * correct
 * 
 * @author jpferrer
 */
public class CardholderTest {

	/**
	 * Test the sorting method to ensure each AirlineMile object is sorted
	 * correctly, in descending order by number of miles with each Airline
	 */
	@Test
	public void testSortAirlineMiles() {
		Airline a1 = new Airline("A1", "Airline 1");
		Airline a2 = new Airline("A2", "Airline 2");
		ArrayList<Airline> aList = new ArrayList<Airline>();
		aList.add(a1);
		aList.add(a2);
		Cardholder c = new Cardholder("John", "Doe", aList);

		Flight f1 = new Flight("A2", "0001", "CLT", "ATL", 100);

		c.addFlight(f1);
		c.addFlight(f1);

		assertEquals("A1", c.getAirlineMiles().get(0).getAirline().getCode());
		assertEquals("A2", c.getAirlineMiles().get(1).getAirline().getCode());

		c.mergeSortAirlineMiles(0, c.getAirlineMiles().size() - 1);

		assertEquals("A2", c.getAirlineMiles().get(0).getAirline().getCode());
		assertEquals("A1", c.getAirlineMiles().get(1).getAirline().getCode());
	}

	/**
	 * Tests the compare to method to see if Cardholders are being compared properly
	 */
	@Test
	public void testCompareTo() {
		Airline a1 = new Airline("A1", "Airline 1");
		Airline a2 = new Airline("A2", "Airline 2");
		ArrayList<Airline> aList = new ArrayList<Airline>();
		aList.add(a1);
		aList.add(a2);
		Cardholder c = new Cardholder("John", "Doe", aList);

		Cardholder cLess = new Cardholder("Z", "A", aList);
		assertTrue(cLess.compareTo(c) < 0);

		Cardholder cMore = new Cardholder("A", "Z", aList);
		assertTrue(cMore.compareTo(c) > 0);

		Cardholder cSame = new Cardholder("John", "Doe", aList);
		assertTrue(cSame.compareTo(c) == 0);

		assertTrue(cSame.equals(c));
		assertEquals(71820560, c.hashCode());
	}

}
