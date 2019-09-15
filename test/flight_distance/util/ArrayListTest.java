package flight_distance.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

/**
 * Tests the ArrayList class to ensure it stores, adds, removes and sets
 * elements properly
 * 
 * @author jpferrer
 */
public class ArrayListTest {

	/**
	 * Tests the add method to ensure elements are added correctly to the list
	 */
	@Test
	public void testAdd() {
		ArrayList<String> a = new ArrayList<String>();
		assertEquals(0, a.size());
		a.add("First");
		a.add("Second");
		a.add("Third");
		assertEquals(3, a.size());
		assertEquals("First", a.get(0));
		assertEquals("Second", a.get(1));
		assertEquals("Third", a.get(2));
		// test growing the array
		a.add("Fourth");
		a.add("Fifth");
		a.add("Sixth");
		a.add("Seventh");
		a.add("Eighth");
		a.add("Ninth");
		a.add("Tenth");
		a.add("Eleventh");
		a.add("Twelfth");
		assertEquals(12, a.size());
		assertEquals("Twelfth", a.get(11));
		// test adding null element
		try {
			a.add(null);
			fail();
		} catch (NullPointerException e) {
			assertEquals("Element given was null", e.getMessage());
		}
		// test getting an out of bounds element
		try {
			a.get(-1);
		} catch (ArrayIndexOutOfBoundsException e) {
			assertEquals("Index given was out of bounds", e.getMessage());
		}
	}

	/**
	 * Tests the remove method to ensure elements are removed properly from the list
	 */
	@Test
	public void testRemove() {
		ArrayList<String> a = new ArrayList<String>();
		a.add("First");
		a.add("Second");
		a.add("Third");
		a.add("Fourth");
		a.add("Fifth");
		assertEquals(5, a.size());
		a.remove(1);
		assertEquals("First", a.get(0));
		assertEquals("Third", a.get(1));
		assertEquals("Fourth", a.get(2));
		assertEquals("Fifth", a.get(3));
		assertEquals(4, a.size());
		// test removing with invalid index
		try {
			a.remove(5);
			fail();
		} catch (ArrayIndexOutOfBoundsException e) {
			assertEquals("Index given was out of bounds", e.getMessage());
		}
		try {
			a.remove(-1);
			fail();
		} catch (ArrayIndexOutOfBoundsException e) {
			assertEquals("Index given was out of bounds", e.getMessage());
		}

		ArrayList<String> b = new ArrayList<String>();
		b.add("First");
		b.add("Second");
		b.add("Third");
		b.remove(2);
		b.remove(0);
		assertEquals(1, b.size());
		assertEquals("Second", b.get(0));
	}

	/**
	 * Tests the set method to ensure elements are set properly in the list
	 */
	@Test
	public void testSet() {
		ArrayList<String> a = new ArrayList<String>();
		a.add("First");
		a.add("Second");
		a.add("Third");
		assertEquals(3, a.size());
		a.set(0, "Zeroth");
		a.set(1, "First");
		a.set(2, "Second");
		assertEquals("Zeroth", a.get(0));
		assertEquals("First", a.get(1));
		assertEquals("Second", a.get(2));
		// test setting with invalid index
		try {
			a.set(25, "OutOfList");
			fail();
		} catch (ArrayIndexOutOfBoundsException e) {
			assertEquals("Index given was out of bounds", e.getMessage());
		}
		try {
			a.set(-1, "OutOfList");
			fail();
		} catch (ArrayIndexOutOfBoundsException e) {
			assertEquals("Index given was out of bounds", e.getMessage());
		}
		// test setting with null element
		try {
			a.set(0, null);
			fail();
		} catch (NullPointerException e) {
			assertEquals("Element given was null", e.getMessage());
		}

	}

}