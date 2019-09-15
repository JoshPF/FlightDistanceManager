package flight_distance.util;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import flight_distance.airline.Airline;
import flight_distance.cardholder.Cardholder;

/**
 * Tests the HashMap data structure to ensure elements are being properly added,
 * searched, and that the HashMap can resize itself properly
 * 
 * @author jpferrer
 */
public class HashTableTest {

	/**
	 * Tests the methods of the HashMap data structure
	 */
	@Test
	public void test() {
		HashTable<Cardholder> map = new HashTable<Cardholder>();
		ArrayList<Airline> airlines = new ArrayList<Airline>();
		airlines.add(new Airline("AA", "American Airlines"));
		Cardholder c1 = new Cardholder("Person1", "Person1", airlines);
		map.insert(c1);
		assertEquals(1, map.size());
		Cardholder c2 = new Cardholder("Person2", "Person2", airlines);
		map.insert(c2);
		assertEquals(2, map.size());
		assertEquals("Person1", map.lookUp(c1).getFirstName());
		assertEquals("Person2", map.lookUp(c2).getFirstName());

		Cardholder c3 = new Cardholder("Person3", "Person3", airlines);
		Cardholder c4 = new Cardholder("Person4", "Person4", airlines);
		Cardholder c5 = new Cardholder("Person5", "Person5", airlines);
		Cardholder c6 = new Cardholder("Person6", "Person6", airlines);
		Cardholder c7 = new Cardholder("Person7", "Person7", airlines);
		Cardholder c8 = new Cardholder("Person8", "Person8", airlines);
		Cardholder c9 = new Cardholder("Person9", "Person9", airlines);
		Cardholder c10 = new Cardholder("Person10", "Person10", airlines);
		Cardholder c11 = new Cardholder("Person11", "Person11", airlines);
		Cardholder c12 = new Cardholder("Person12", "Person12", airlines);
		Cardholder c13 = new Cardholder("Person13", "Person13", airlines);
		Cardholder c14 = new Cardholder("Person14", "Person14", airlines);
		Cardholder c15 = new Cardholder("Person15", "Person15", airlines);
		Cardholder c16 = new Cardholder("Person16", "Person16", airlines);
		Cardholder c17 = new Cardholder("Person17", "Person17", airlines);

		HashTable<Cardholder> resizeTable = new HashTable<Cardholder>();
		resizeTable.insert(c1);
		resizeTable.insert(c2);
		resizeTable.insert(c3);
		resizeTable.insert(c4);
		resizeTable.insert(c5);
		resizeTable.insert(c6);
		resizeTable.insert(c7);
		resizeTable.insert(c8);
		resizeTable.insert(c9);
		resizeTable.insert(c10);
		resizeTable.insert(c11);
		resizeTable.insert(c12);
		resizeTable.insert(c13);
		resizeTable.insert(c14);
		resizeTable.insert(c15);
		resizeTable.insert(c16);
		resizeTable.insert(c17);
		assertEquals(17, resizeTable.size());

	}

}
