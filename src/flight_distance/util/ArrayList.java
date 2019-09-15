package flight_distance.util;

/**
 * Array based list.
 * 
 * @author jpferrer
 * @param <E> The element of a list
 */
public class ArrayList<E> {

	/** Default initial list size */
	private static final int INIT_SIZE = 10;
	/** List of objects */
	private E[] list;
	/** Size of the list */
	private int size;

	/**
	 * Constructs a new ArrayList with a default size of 10
	 */
	public ArrayList() {
		this(INIT_SIZE);
	}

	/**
	 * Constructs a new ArrayList with a given size
	 * 
	 * @param size the size to create the list with
	 */
	@SuppressWarnings("unchecked")
	public ArrayList(int size) {
		list = (E[]) new Object[size];
		this.size = 0;
	}

	/**
	 * Returns an element at a given index
	 * 
	 * @param index The index of the desired element
	 * @return the element at the given index
	 */
	public E get(int index) {
		if (index < 0 || index >= list.length) {
			throw new ArrayIndexOutOfBoundsException("Index given was out of bounds");
		}
		return list[index];
	}

	/**
	 * Returns the size of the list
	 * 
	 * @return size the size of the list
	 */
	public int size() {
		return size;
	}

	/**
	 * Adds an element to the end of the list
	 * 
	 * @param element the element to add
	 */
	public void add(E element) {
		if (element == null) {
			throw new NullPointerException("Element given was null");
		}
		if (size + 1 >= list.length) {
			growArray();
		}
		list[size++] = element;
	}

	/**
	 * Grows the array if it is too small
	 */
	@SuppressWarnings("unchecked")
	public void growArray() {
		E[] temp = (E[]) new Object[2 * size];
		for (int i = 0; i < size; i++) {
			temp[i] = list[i];
		}
		list = temp;
	}

	/**
	 * Removes an element at a given index
	 * 
	 * @param index the index of the element to remove
	 * @throws ArrayIndexOutOfBoundsException if the index is less than 0 or more
	 *                                        than the size of the list
	 */
	public void remove(int index) {
		if (index < 0 || index >= size) {
			throw new ArrayIndexOutOfBoundsException("Index given was out of bounds");
		}
		list[index] = null;
		for (int i = index; i < size - 1; i++) {
			list[i] = list[i + 1];
		}
		size--;
	}

	/**
	 * Sets the element at the given index to the given value
	 * 
	 * @param index   the index
	 * @param element the value to set
	 * @throws ArrayIndexOutOfBoundsException if the index is less than 0 or more
	 *                                        than the size of the list
	 */
	public void set(int index, E element) {
		if (index < 0 || index >= size) {
			throw new ArrayIndexOutOfBoundsException("Index given was out of bounds");
		}
		if (element == null) {
			throw new NullPointerException("Element given was null");
		}
		list[index] = element;
	}

}