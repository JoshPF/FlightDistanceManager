package flight_distance.cardholder;

import flight_distance.airline.Airline;
import flight_distance.flight.Flight;
import flight_distance.util.ArrayList;

/**
 * Contains the information for a particular Cardholder
 * 
 * @author jpferrer
 */
public class Cardholder implements Comparable<Cardholder> {
	/** The cardholder's first name */
	private String firstName;
	/** The cardholder's last name */
	private String lastName;
	/**
	 * The list of AirlineMile objects to keep track of the cardholder's number of
	 * miles associated with each airline
	 */
	private ArrayList<AirlineMile> miles;

	/**
	 * Constructs a Cardholder object given a first and last name and a list of
	 * Airlines
	 * 
	 * @param firstName The cardholder's first name
	 * @param lastName  The cardholder's last name
	 * @param airlines  The list of airlines the cardholder can travel on
	 */
	public Cardholder(String firstName, String lastName, ArrayList<Airline> airlines) {
		setFirstName(firstName);
		setLastName(lastName);
		miles = new ArrayList<AirlineMile>();
		for (int i = 0; i < airlines.size(); i++) {
			miles.add(new AirlineMile(airlines.get(i), 0));
		}
	}

	/**
	 * Sets the Cardholder's first name
	 * 
	 * @param firstName the cardholder's first name
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Sets the last name for the cardholder
	 * 
	 * @param lastName the Last name for the cardholder
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Adds a flight's distance to the total distance for an Airline
	 * 
	 * @param flight The flight to add
	 * @throws IllegalArgumentException if the flight's airlineCode is with an
	 *                                  unknown airline
	 */
	public void addFlight(Flight flight) {
		int distance = flight.getDistance();
		String airlineCode = flight.getAirlineCode();
		for (int i = 0; i < miles.size(); i++) {
			if (airlineCode.equals(miles.get(i).getAirline().getCode())) {
				miles.get(i).addMiles(distance);
				return;
			}
		}
		throw new IllegalArgumentException("Flight was with an unregistered Airline");
	}

	/**
	 * Gets the cardholder's first name
	 * 
	 * @return firstName the cardholder's first name
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Gets the cardholder's last name
	 * 
	 * @return lastName the cardholder's last name
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Gets the list of AirlineMile objects
	 * 
	 * @return miles The list of AirlineMiles
	 */
	public ArrayList<AirlineMile> getAirlineMiles() {
		return miles;
	}

	/**
	 * Sorts the list of AirlineMiles by distance and then by airline code
	 * 
	 * @param first The first element to begin sorting
	 * @param last  The last element to begin sorting
	 */
	public void mergeSortAirlineMiles(int first, int last) {
		if (miles.size() <= 1) {
			return;
		}
		if (first < last) {
			int mid = (last + first) / 2;
			mergeSortAirlineMiles(first, mid);
			mergeSortAirlineMiles(mid + 1, last);
			mergeAirlineMiles(first, mid, mid + 1, last);
		}
	}

	/**
	 * Merges the list of Airline Miles back together, for use by the
	 * mergeSortAirlineMiles method
	 * 
	 * @param first The first element of the bottom list
	 * @param mid   The last element of the bottom list
	 * @param upMid The first element of the upper list
	 * @param last  The last element of the upper list
	 */
	private void mergeAirlineMiles(int first, int mid, int upMid, int last) {
		ArrayList<AirlineMile> temp = new ArrayList<AirlineMile>();

		// ensure temp has the necessary capacity by adding all of the flights into temp
		for (int i = 0; i < miles.size(); i++) {
			temp.add(miles.get(i));
		}

		// index for left half of list
		int leftIndex = first;
		// index for right half of list
		int rightIndex = upMid;
		// index for final list of flights
		int flightIndex = leftIndex;

		while (leftIndex <= mid && rightIndex <= last) {
			if (temp.get(leftIndex).compareTo(temp.get(rightIndex)) >= 0) {
				miles.set(flightIndex, temp.get(leftIndex));
				leftIndex++;
				flightIndex++;
			} else {
				miles.set(flightIndex, temp.get(rightIndex));
				rightIndex++;
				flightIndex++;
			}
		}

		while (leftIndex <= mid) {
			miles.set(flightIndex, temp.get(leftIndex));
			leftIndex++;
			flightIndex++;
		}

		while (rightIndex <= last) {
			miles.set(flightIndex, temp.get(rightIndex));
			rightIndex++;
			flightIndex++;
		}

	}

	/**
	 * Compares two Cardholders based on alphabetical order, comparing last name
	 * first, then the first name
	 * 
	 * @param other The cardholder to compare to
	 * @return the comparison between the two cardholders
	 */
	@Override
	public int compareTo(Cardholder other) {
		int comparison = this.getLastName().compareTo(other.getLastName());
		if (comparison == 0) {
			return this.getFirstName().compareTo(other.getFirstName());
		} else {
			return comparison;
		}
	}

	/**
	 * Creates a hash code for the Cardholder object
	 * 
	 * @return the hash code for the Cardholder object
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + firstName.hashCode();
		result = prime * result + lastName.hashCode();
		return result;
	}

	/**
	 * Sees if two Cardholder objects are equivalent
	 * 
	 * @return true if two Cardholders are the same, false if not
	 */
	@Override
	public boolean equals(Object other) {
		if (this.compareTo((Cardholder) other) != 0)
			return false;
		return true;
	}

}
