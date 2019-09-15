package flight_distance.airline;

/**
 * Contains the information for an airline
 * 
 * @author jpferrer
 */
public class Airline {
	/** The code of the airline */
	private String code;
	/** The full name of the airline */
	private String name;

	/**
	 * Constructs a new airline given an airline code and a name
	 * 
	 * @param code The code of the airline
	 * @param name The full name of the airline
	 */
	public Airline(String code, String name) {
		setCode(code);
		setName(name);
	}

	/**
	 * Gets the code of the airline
	 * 
	 * @return code the code of the airline
	 */
	public String getCode() {
		return code;
	}

	/**
	 * Sets the code of the airline to a given value
	 * 
	 * @param code the code of the airline
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * Gets the name of the airline
	 * 
	 * @return name the full name of the airline
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name of the airline to a given value
	 * 
	 * @param name the full name of the airline
	 */
	public void setName(String name) {
		this.name = name;
	}

}
