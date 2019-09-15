package flight_distance.flight;

/**
 * Contains the information for a flight
 * 
 * @author jpferrer
 */
public class Flight implements Comparable<Flight> {
	/** The code of the airline the flight was taken on */
	private String airlineCode;
	/** The flight number of the flight */
	private String flightNum;
	/** The airport the flight started at */
	private String originAirport;
	/** The airport the flight landed at */
	private String destinationAirport;
	/** The distance of the flight */
	private int distance;

	/**
	 * Constructs a flight object given an airline code, flight number, origin and
	 * destination airports and distance
	 * 
	 * @param airlineCode        The code of the airline the flight was taken on
	 * @param flightNum          The flight number of the flight
	 * @param originAirport      The airport the flight started at
	 * @param destinationAirport The airport the flight landed at
	 * @param distance           The distance of the flight
	 */
	public Flight(String airlineCode, String flightNum, String originAirport, String destinationAirport, int distance) {
		setAirlineCode(airlineCode);
		setFlightNum(flightNum);
		setOriginAirport(originAirport);
		setDestinationAirport(destinationAirport);
		setDistance(distance);
	}

	/**
	 * Gets the airline code of the flight
	 * 
	 * @return airlineCode The code of the airline the flight was taken on
	 */
	public String getAirlineCode() {
		return airlineCode;
	}

	/**
	 * Sets the airline code of the flight to a given value
	 * 
	 * @param airlineCode the airline code of the flight
	 */
	public void setAirlineCode(String airlineCode) {
		this.airlineCode = airlineCode;
	}

	/**
	 * Gets the flight number of the flight
	 * 
	 * @return flightNum the flight number of the flight
	 */
	public String getFlightNum() {
		return flightNum;
	}

	/**
	 * Sets the flight number to a given value
	 * 
	 * @param flightNum the flight number of the flight
	 */
	public void setFlightNum(String flightNum) {
		this.flightNum = flightNum;
	}

	/**
	 * Gets the airport the flight took off at
	 * 
	 * @return originAirport the airport the flight took off at
	 */
	public String getOriginAirport() {
		return originAirport;
	}

	/**
	 * Sets the origin airport to a given value
	 * 
	 * @param originAirport the airport the flight took off at
	 */
	public void setOriginAirport(String originAirport) {
		this.originAirport = originAirport;
	}

	/**
	 * Gets the airport the flight landed at
	 * 
	 * @return destinationAirport the airport the flight landed at
	 */
	public String getDestinationAirport() {
		return destinationAirport;
	}

	/**
	 * Sets the airport the flight landed at to a given value
	 * 
	 * @param destinationAirport the airport the flight landed at
	 */
	public void setDestinationAirport(String destinationAirport) {
		this.destinationAirport = destinationAirport;
	}

	/**
	 * Gets the distance of the flight
	 * 
	 * @return distance the distance of the flight
	 */
	public int getDistance() {
		return distance;
	}

	/**
	 * Sets the distance of the flight to a given value
	 * 
	 * @param distance the distance of the flight
	 */
	public void setDistance(int distance) {
		this.distance = distance;
	}

	/**
	 * Compares two Flights based on airline code and then flight number
	 * 
	 * @return a negative value if this flight is less than the other flight, a
	 *         positive if this flight is greater than the other flight, or 0 if
	 *         they're the same
	 */
	@Override
	public int compareTo(Flight other) {
		int comparison = this.getAirlineCode().compareTo(other.getAirlineCode());
		if (comparison == 0) {
			comparison = this.getFlightNum().compareTo(other.getFlightNum());
			if (comparison == 0) {
				comparison = this.getOriginAirport().compareTo(other.getOriginAirport());
				if (comparison == 0) {
					return this.getDestinationAirport().compareTo(other.getDestinationAirport());
				} else {
					return comparison;
				}
			} else {
				return comparison;
			}
		} else {
			return comparison;
		}
	}

	/**
	 * Generates a hash code for the Flight object
	 * 
	 * @return the hash code for the Flight object
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + airlineCode.hashCode();
		result = prime * result + destinationAirport.hashCode();
		// result = prime * result + distance;
		result = prime * result + flightNum.hashCode();
		result = prime * result + originAirport.hashCode();
		return result;
	}

	/**
	 * Sees if two Flight objects are the same
	 * 
	 * @return true if the two Flights are the same, false if not
	 */
	@Override
	public boolean equals(Object other) {
		if (this.compareTo((Flight) other) != 0)
			return false;
		return true;
	}

}
