package flight_distance.manager;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import flight_distance.airline.Airline;
import flight_distance.cardholder.AirlineMile;
import flight_distance.cardholder.Cardholder;
import flight_distance.flight.Flight;
import flight_distance.util.ArrayList;
import flight_distance.util.HashTable;

/**
 * The main class that reads in the three input files and prints out the mileage
 * report from those files
 * 
 * @author jpferrer
 */
public class FlightDistanceManager {
	/** The list of cardholders in the system */
	private HashTable<Cardholder> cardholders;
	/** The list of flights in the system */
	private HashTable<Flight> flights;
	/** The list of Airlines in the system */
	private ArrayList<Airline> airlines;
	/** The final output of the mileage report of the system */
	private StringBuilder mileageReport;

	/**
	 * Constructs an AirlineMileageManager object given the airline, customer and
	 * flight input files
	 * 
	 * @param pathToAirlineFile  The input file containing information on the
	 *                           Airlines
	 * @param pathToCustomerFile The input file containing information on the
	 *                           Cardholders and the flights they took
	 * @param pathToFlightsFile  The input file containing information on the
	 *                           Flights
	 */
	public FlightDistanceManager(String pathToAirlineFile, String pathToCustomerFile, String pathToFlightsFile) {
		cardholders = new HashTable<Cardholder>();
		flights = new HashTable<Flight>();
		airlines = new ArrayList<Airline>();
		mileageReport = new StringBuilder();

		readAirlineFile(pathToAirlineFile);
		readFlightFile(pathToFlightsFile);

		readCardholderFile(pathToCustomerFile);

	}

	/**
	 * Reads in the Cardholder file and creates Cardholder information based on the
	 * file's contents
	 * 
	 * @throws IllegalArgumentException if the file has improperly formatted
	 *                                  information or if the file could not be
	 *                                  found
	 * @param file the file to read in
	 */
	public void readCardholderFile(String file) {
		try {
			FileInputStream f = new FileInputStream(new File(file));
			Scanner fileReader = new Scanner(f);
			// Throw out first line
			fileReader.nextLine();
			while (fileReader.hasNextLine()) {
				String line = fileReader.nextLine();
				Scanner lineProcessor = new Scanner(line);
				lineProcessor.useDelimiter(",");
				try {
					String firstName = lineProcessor.next();
					String lastName = lineProcessor.next();
					Cardholder c = null;
					c = new Cardholder(firstName, lastName, airlines);
					if (cardholders.lookUp(c) == null)
						cardholders.insert(c);
					else
						c = cardholders.lookUp(c);

					// throw out date
					lineProcessor.next();

					String flightCode = lineProcessor.next();
					StringBuilder airlineCode = new StringBuilder();
					airlineCode.append(flightCode.charAt(0));
					airlineCode.append(flightCode.charAt(1));
					String flightNum = flightCode.substring(2);

					String origin = lineProcessor.next();
					String destination = lineProcessor.next();
					Flight temp = new Flight(airlineCode.toString(), flightNum, origin, destination, 0);
					Flight fl = flights.lookUp(temp);
					if (fl != null)
						c.addFlight(fl);
					lineProcessor.close();
				} catch (NoSuchElementException g) {
					lineProcessor.close();
					throw new IllegalArgumentException("File does not contain the correct information");
				}
			}
			fileReader.close();
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException("File could not be found");
		}

	}

	/**
	 * Reads in Airline information from an input file and constructs a list of
	 * Airline objects given the contents of the file
	 * 
	 * @throws IllegalArgumentException if the file has improperly formatted
	 *                                  information or if the file could not be
	 *                                  found
	 * @param file the file to read in
	 */
	public void readAirlineFile(String file) {
		try {
			FileInputStream f = new FileInputStream(new File(file));
			Scanner fileReader = new Scanner(f);
			// Throw out first line
			fileReader.nextLine();
			while (fileReader.hasNextLine()) {
				String line = fileReader.nextLine();
				Scanner lineProcessor = new Scanner(line);
				lineProcessor.useDelimiter(",");
				try {
					String name = lineProcessor.next();
					String code = lineProcessor.next();
					Airline a = new Airline(code, name);
					airlines.add(a);
					lineProcessor.close();
				} catch (NoSuchElementException g) {
					lineProcessor.close();
					fileReader.close();
					throw new IllegalArgumentException("File does not contain the correct information");
				}
			}
			fileReader.close();
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException("File could not be found");
		}
	}

	/**
	 * Reads in Flight information from an input file and constructs a list of
	 * Flight objects given the contents of the file
	 * 
	 * @throws IllegalArgumentException if the file has improperly formatted
	 *                                  information or if the file could not be
	 *                                  found
	 * @param file the file to read in
	 */
	public void readFlightFile(String file) {
		try {
			FileInputStream f = new FileInputStream(new File(file));
			Scanner fileReader = new Scanner(f);
			// Throw out first line
			fileReader.nextLine();
			while (fileReader.hasNextLine()) {
				String line = fileReader.nextLine();
				Scanner lineProcessor = new Scanner(line);
				lineProcessor.useDelimiter(",");
				try {
					// throw out year, month, day and dayOfWeek
					lineProcessor.next();
					lineProcessor.next();
					lineProcessor.next();
					lineProcessor.next();
					String airlineCode = lineProcessor.next();
					String flightNum = lineProcessor.next();
					String originAirport = lineProcessor.next();
					String destinationAirport = lineProcessor.next();
					// throw out scheduled_departure and depature_time
					lineProcessor.next();
					lineProcessor.next();
					int distance = lineProcessor.nextInt();
					Flight flight = new Flight(airlineCode, flightNum, originAirport, destinationAirport, distance);
					flights.insert(flight);
					lineProcessor.close();
				} catch (NoSuchElementException g) {
					throw new IllegalArgumentException("File does not contain the correct information");
				}
			}
			fileReader.close();
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException("File could not be found");
		}
	}

	/**
	 * Gets the number of miles for each airline for a given Cardholder given their
	 * name
	 * 
	 * @param firstName The first name of the Cardholder to get mile information for
	 * @param lastName  The last name of the Cardholder to get mile information for
	 * @return output A string representation of the number of miles a Cardholder
	 *         has for each airline
	 */
	public String getMiles(String firstName, String lastName) {
		Cardholder c = new Cardholder(firstName, lastName, airlines);
		c = cardholders.lookUp(c);
		if (c == null) {
			mileageReport.append(firstName + " " + lastName + " earned\n    no miles.");
			return mileageReport.toString();
		}
		c.mergeSortAirlineMiles(0, c.getAirlineMiles().size() - 1);
		ArrayList<AirlineMile> miles = c.getAirlineMiles();
		mileageReport.append(firstName + " " + lastName + " earned\n");
		for (int i = 0; i < miles.size(); i++) {
			AirlineMile mile = miles.get(i);
			Airline airline = mile.getAirline();
			int distance = mile.getDistance();
			if (distance > 0) {
				mileageReport.append("    " + mile.getDistance() + " miles with " + airline.getName() + " ("
						+ airline.getCode() + ")");
			}
			if (i + 1 < miles.size() && miles.get(i + 1).getDistance() > 0) {
				mileageReport.append("\n");
			}
		}
		return mileageReport.toString();
	}

	/**
	 * Gets mileage information for each Cardholder in the system
	 * 
	 * @return mileageReport A string representation of the number of miles every
	 *         Cardholder has for each airline
	 */
	public String getMileageReport() {
		ArrayList<Cardholder> cList = cardholders.getList();
		mergeSortCardholders(cList, 0, cList.size() - 1);
		for (int i = 0; i < cList.size(); i++) {
			getMiles(cList.get(i).getFirstName(), cList.get(i).getLastName());
			if (i + 1 < cList.size())
				mileageReport.append("\n\n");
		}
		return mileageReport.toString();
	}

	/**
	 * Gets the list of airlines
	 * 
	 * @return airlines the list of airlines read in from the airline input file
	 */
	public ArrayList<Airline> getAirlines() {
		return airlines;
	}

	/**
	 * Sorts the list of Cardholders alphabetically by last name and then first name
	 * if their last names are the same Referenced lecture slides
	 * 
	 * @param cardholders The list of Cardholders to sort
	 * @param first       The first element to begin sorting
	 * @param last        The last element to begin sorting
	 */
	public void mergeSortCardholders(ArrayList<Cardholder> cardholders, int first, int last) {
		if (cardholders.size() <= 1) {
			return;
		}

		if (first < last) {
			int mid = (first + last) / 2;
			mergeSortCardholders(cardholders, first, mid);
			mergeSortCardholders(cardholders, mid + 1, last);
			mergeCardholders(cardholders, first, mid, mid + 1, last);
		}
	}

	/**
	 * Merges the list of cardholders back together, for use by the
	 * mergeSortCardholders method
	 * 
	 * @param cardholders The list of Cardholders to sort
	 * @param first       The first element of the bottom list
	 * @param mid         The last element of the bottom list
	 * @param upMid       The first element of the upper list
	 * @param last        The last element of the upper list
	 */
	private void mergeCardholders(ArrayList<Cardholder> cardholders, int first, int mid, int upMid, int last) {
		ArrayList<Cardholder> temp = new ArrayList<Cardholder>();

		for (int i = 0; i < cardholders.size(); i++) {
			temp.add(cardholders.get(i));
		}

		int leftIndex = first;
		int rightIndex = upMid;
		int cardholderIndex = leftIndex;

		while (leftIndex <= mid && rightIndex <= last) {
			if (temp.get(leftIndex).compareTo(temp.get(rightIndex)) <= 0) {
				cardholders.set(cardholderIndex++, temp.get(leftIndex++));
			} else {
				cardholders.set(cardholderIndex++, temp.get(rightIndex++));
			}
		}

		while (leftIndex <= mid) {
			cardholders.set(cardholderIndex++, temp.get(leftIndex++));
		}

		while (rightIndex <= last) {
			cardholders.set(cardholderIndex++, temp.get(rightIndex++));
		}
	}

}