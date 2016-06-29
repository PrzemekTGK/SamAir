/*
 * Initializer class initializes all pseudo data bases from the files
 */
package samair;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author PRZEMEK
 */
public class Initializer implements Serializable {
    // Declared pseudo data base objects to store various items neeeded for the program
    private UserDataBase users = new UserDataBase();
    private PilotDataBase pilots = new PilotDataBase();
    private AirPortDataBase airPorts = new AirPortDataBase();
    private AirCraftDataBase airCrafts = new AirCraftDataBase();
    private FlightDataBase flights = new FlightDataBase(new File("airlines.txt"));
    // Dummy users data
    private User admin = new Admin("admin", "123");
    private User customer = new Customer("Sam", "123");
    
    public UserDataBase initialize(Logic logic){

        getUsersDataBase().getUsers().put(logic.generateUniqueKey(getUsersDataBase().getUsers()), getAdmin());
        getUsersDataBase().getUsers().put(logic.generateUniqueKey(getUsersDataBase().getUsers()), getCustomer());

        // Initializing Hashmaps with randomly created pilots, airplanes
        try {
            // Creat 100 random pilots
            for (int i = 0; i < 100; i++) {
                Pilot pilot = getPilotsDataBase().generatePilot(getPilotsDataBase().readTextFile(
                        new File("names.txt")),
                        getPilotsDataBase().readTextFile(new File("surnames.txt")),
                        getPilotsDataBase().getPilots());
                getPilotsDataBase().getPilots().put(pilot.getPilotID(), pilot);
            }
            // Create 50 random Beoing airplanes
            for (int i = 0; i < 50; i++) {
                getAirCraftsDataBase().getAirCrafts().put(logic.generateUniqueKey(getAirCraftsDataBase().getAirCrafts()), getAirCraftsDataBase().generateAirCraft(getAirCraftsDataBase().readAirplanesFromCsvFile(
                        new File("boeing.csv"))));
            }
            // Create 50 random Airbus airplanes
            for (int i = 0; i < 50; i++) {
                getAirCraftsDataBase().getAirCrafts().put(logic.generateUniqueKey(getAirCraftsDataBase().getAirCrafts()), getAirCraftsDataBase().generateAirCraft(getAirCraftsDataBase().readAirplanesFromCsvFile(
                        new File("airbus.csv"))));
            }
            
            getAirPortsDataBase().generateAirportsFromCsvFile(new File("airports.csv"));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SamAir.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Generate 100 random flights
        for (int i = 0; i < 100; i++) {
            Flight fligth = this.getFlightsDataBase().generateFlight(getAirPortsDataBase(), getAirCraftsDataBase(), getPilotsDataBase());        
            ((Admin)getAdmin()).scheduleFlight(fligth);
            ((Admin)getAdmin()).addFlight(fligth, getFlightsDataBase());            
        }
               
        // Display all airplanes and pilots created
//        getAirPortsDataBase().getAirPortsDataBase().forEach((k, v) -> System.out.println("Key: " + k + "\n" + v));
//        getAirCraftsDataBase().getAirCraftsDataBase().forEach((k, v) -> System.out.println(v + "\n"));
//        getPilotsDataBase().getPilotsDataBase().forEach((k, v) -> System.out.println("Key: " + k + "\nValue: " + v));
//        getFlightsDataBase().getScheduledFlights().forEach((k,v) -> System.out.println(v));
        
        return getUsersDataBase();
    }

    /**
     * @return the users
     */
    public UserDataBase getUsersDataBase() {
        return users;
    }

    /**
     * @return the pilots
     */
    public PilotDataBase getPilotsDataBase() {
        return pilots;
    }

    /**
     * @return the airPorts
     */
    public AirPortDataBase getAirPortsDataBase() {
        return airPorts;
    }

    /**
     * @return the airCrafts
     */
    public AirCraftDataBase getAirCraftsDataBase() {
        return airCrafts;
    }

    /**
     * @return the flight
     */
    public FlightDataBase getFlightsDataBase() {
        return flights;
    }

    /**
     * @return the admin
     */
    public User getAdmin() {
        return admin;
    }

    /**
     * @return the customer
     */
    public User getCustomer() {
        return customer;
    }    
}
