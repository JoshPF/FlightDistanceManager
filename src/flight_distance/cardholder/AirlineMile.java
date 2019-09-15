package flight_distance.cardholder;

import flight_distance.airline.Airline;

/**
 * An object that contains information regarding the airline and the distance
 * traveled with that airline for use by Cardholders
 * 
 * @author jpferrer
 */
public class AirlineMile {
	/** The airline traveled with */
	private Airline airline;
	/** The distance associated with that airline */
	private int distance;

	/**
	 * Constructs a new AirlineMile object given an airline and an initial distance
	 * 
	 * @param airline  The airline traveled with
	 * @param distance The distance associated with that airline
	 */
	public AirlineMile(Airline airline, int distance) {
		setAirline(airline);
		setDistance(distance);
	}

	/**
	 * Gets the airline associated with the AirlineMile object
	 * 
	 * @return airline The airline traveled with
	 */
	public Airline getAirline() {
		return airline;
	}

	/**
	 * Sets the airline to a given value
	 * 
	 * @param airline The airline traveled with
	 */
	public void setAirline(Airline airline) {
		this.airline = airline;
	}

	/**
	 * Gets the distance associated with an Airline
	 * 
	 * @return distance the distance associated with an airline
	 */
	public int getDistance() {
		return distance;
	}

	/**
	 * Sets the distance traveled with an airline
	 * 
	 * @param distance the distance traveled with an airline
	 */
	public void setDistance(int distance) {
		this.distance = distance;
	}

	/**
	 * Adds miles to the total distance traveled with an airline
	 * 
	 * @param distance the distance to add
	 */
	public void addMiles(int distance) {
		this.distance += distance;
	}

	/**
	 * Compares two AirlineMile objects by distance with that airline and by their
	 * airline code
	 * 
	 * @param other the AirlineMile to compare to
	 * @return the comparison between the two objects
	 */
	public int compareTo(AirlineMile other) {
		if (this.distance < other.distance) {
			return -1;
		} else if (this.distance > other.distance) {
			return 1;
		} else {
			return other.getAirline().getCode().compareTo(this.getAirline().getCode());
		}
	}

}
