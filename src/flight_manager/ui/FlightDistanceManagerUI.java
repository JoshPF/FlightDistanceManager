package flight_manager.ui;

import java.util.NoSuchElementException;
import java.util.Scanner;

import flight_distance.manager.FlightDistanceManager;

/**
 * This class represents the User Interface for the Airline Mileage program
 * @author jpferrer
 */
public class FlightDistanceManagerUI {
    
    /**
     * The user interface of FlightDistanceManager. Prompts the user for valid input files and
     * for whether they want a full mileage report or a report for one specific customer and gets
     * the correct information
     * @param args The arguments given when the program starts
     */
    public static void main(String[] args) {
	Scanner s = new Scanner(System.in);
	Scanner rProcessor;
	String airlineFile = "";
	String customerFile = "";
	String flightFile = "";
	String r = "";
	String first = "";
	String last = "";
	
	while (true) {
            try {
        	System.out.println("Enter a valid airline file: ");
        	airlineFile = s.nextLine();
        	if (airlineFile.equals("")) {
        	    System.out.println("Nothing was entered. Please try again.");
        	    continue;
        	}
        	System.out.println("Enter a valid customer file: ");
        	customerFile = s.nextLine();
        	if (customerFile.equals("")) {
        	    System.out.println("Nothing was entered. Please try again.");
        	    continue;
        	}
        	System.out.println("Enter a valid flight file: ");
        	flightFile = s.nextLine();
        	if (flightFile.equals("")) {
        	    System.out.println("Nothing was entered. Please try again.");
        	    continue;
        	}
                FlightDistanceManager a = new FlightDistanceManager(airlineFile, customerFile, flightFile);
                System.out.println("Would you like a full mileage report of all customers (1),"
                	           + " or a report of a specific customer (2)? (Enter 1 or 2)");
                int response = s.nextInt();
                System.out.println();
                if (response == 1) {
                    System.out.println(a.getMileageReport());
                    break;
                } else if (response == 2) {
                    s = new Scanner(System.in);
                    System.out.println("Please enter a name, first name first, last name second:");
                    r = s.nextLine();
                    rProcessor = new Scanner(r);
                    first = rProcessor.next();
                    last = rProcessor.next();
                    System.out.println();
                    System.out.println(a.getMiles(first, last));
                    break;
                } else {
                    System.out.println("Not a valid option. Please try again.");
                    continue;
                }
            } catch (NoSuchElementException e) {
        	System.out.println("Invalid input. Please try again.\n");
        	continue;
            } catch (IllegalArgumentException f) {
        	if (f.getMessage().equals("File could not be found")) {
        	    System.out.println("The specified file could not be found."
        	    	               + " Please try again.\n");
        	    continue;
        	}
        	if (f.getMessage().equals("File does not contain the correct information")) {
        	    System.out.println("The specified file does not contain the correct information."
        	    	               + " Please try again.\n");
        	    continue;
        	}
            }
	}
	s.close();
    }
}
