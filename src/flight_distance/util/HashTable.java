package flight_distance.util;

/**
 * A map that uses hash codes to determine locations in the map
 * 
 * @author jpferrer
 * @param <E> An element in the hash map
 */
public class HashTable<E> {

	/** The list of buckets that contain the elements */
	private ArrayList<ArrayList<E>> buckets;
	/** The number of elements in the hash map */
	private int size;
	/** The total number of buckets */
	private int cap;
	/** Default initial capacity size */
	private static final int INIT_SIZE = 16;

	/**
	 * Constructs a new HashMap using the default capacity of 10 buckets
	 */
	public HashTable() {
		this(INIT_SIZE);
	}

	/**
	 * Constructs a new HashMap given an initial capacity
	 * 
	 * @param initialCap the initial number of buckets to store elements in
	 */
	public HashTable(int initialCap) {
		cap = initialCap;
		size = 0;
		buckets = new ArrayList<ArrayList<E>>(cap);
		for (int i = 0; i < cap; i++)
			buckets.add(new ArrayList<E>());
	}

	/**
	 * Increases the number of buckets in the map
	 */
	private void growTable() {
		// buckets.growArray();
		for (int i = 0; i < cap; i++)
			buckets.add(new ArrayList<E>());
		cap *= 2;
		ArrayList<E> elements = getList();
		buckets = new ArrayList<ArrayList<E>>(cap);
		size = 0;
		for (int i = 0; i < cap; i++)
			buckets.add(new ArrayList<E>());
		for (int i = 0; i < elements.size(); i++) {
			insert(elements.get(i));
		}
	}

	/**
	 * Inserts an element into the Hash Map
	 * 
	 * @param element the element to insert
	 */
	public void insert(E element) {
		while ((size / cap) >= .75)
			growTable();
		int hash = element.hashCode();
		if (hash < 0)
			hash *= -1;
		int placement = hash % cap;
		buckets.get(placement).add(element);
		size++;
	}

	/**
	 * Searches the HashMap for a given element
	 * 
	 * @param element the element to search for
	 * @return the element stored in the HashMap
	 */
	public E lookUp(E element) {
		int hash = element.hashCode();
		if (hash < 0)
			hash *= -1;
		int placement = hash % cap;
		ArrayList<E> searchList = buckets.get(placement);
		for (int i = 0; i < searchList.size(); i++) {
			if (element.equals(searchList.get(i)))
				return searchList.get(i);
		}
		return null;
	}

	/**
	 * Returns an ArrayList of all the elements stored in the HashMap
	 * 
	 * @return an ArrayList of all the elements stored in the HashMap
	 */
	public ArrayList<E> getList() {
		ArrayList<E> a = new ArrayList<E>();
		for (int i = 0; i < cap; i++) {
			ArrayList<E> bucket = buckets.get(i);
			for (int j = 0; j < bucket.size(); j++)
				a.add(bucket.get(j));
		}
		return a;
	}

	/**
	 * Returns the number of elements in the HashMap
	 * 
	 * @return size The number of elements in the HashMap
	 */
	public int size() {
		return size;
	}

}
