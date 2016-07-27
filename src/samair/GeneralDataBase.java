/*
 * GeneralDataBase class initializes all pseudo data bases from the files
 */
package samair;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Przemek Stepien
 */
public class GeneralDataBase implements Serializable {
    // Declared pseudo data base objects to store various items neeeded for the program
    private UserDataBase users = new UserDataBase();
    private PilotDataBase pilots = new PilotDataBase();
    private AirPortDataBase airPorts = new AirPortDataBase();
    private AirCraftDataBase airCrafts = new AirCraftDataBase();
    private JourneyDataBase flights = new JourneyDataBase();
    private BookedFlightsDataBase bookedFlights= new BookedFlightsDataBase();
    private AirLinesDataBase airlines = new AirLinesDataBase(new File("airlines.txt"));
    // Dummy users data
    private User admin = new Admin("admin", "123");
    private User customer = new Customer("Sam", "123");

    /**
     * Initialises all pseudo data base with randomly created 
     * 
     * @param logic is used to get access to some of methods needed 
     */
    public void fillData(Logic logic){
        getUsersDataBase().getUsers().put(logic.verifyUniqueKey(getUsersDataBase().getUsers()), getAdmin());
        getUsersDataBase().getUsers().put(logic.verifyUniqueKey(getUsersDataBase().getUsers()), getCustomer());

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
                getAirCraftsDataBase().getAirCrafts().put(logic.verifyUniqueKey(
                        getAirCraftsDataBase().getAirCrafts()), 
                        getAirCraftsDataBase().generateAirCraft(
                                getAirCraftsDataBase().generateAirPlanesAsList(
                                        getAirCraftsDataBase().readAirplanesFromCsvFile(
                                                new File("boeing.csv")))));
            }
            // Create 50 random Airbus airplanes
            for (int i = 0; i < 50; i++) {
                getAirCraftsDataBase().getAirCrafts().put(logic.verifyUniqueKey(
                        getAirCraftsDataBase().getAirCrafts()), 
                        getAirCraftsDataBase().generateAirCraft(
                                getAirCraftsDataBase().generateAirPlanesAsList(
                                        getAirCraftsDataBase().readAirplanesFromCsvFile(
                                                new File("airbus.csv")))));
            }
            // Generate list of all airports in the file
            getAirPortsDataBase().generateAirportsFromCsvFile(new File("airports.csv"));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SamAir.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Generate 100 random flights
        for (int i = 0; i < 100; i++) {
            Flight fligth = this.getFlightsDataBase().generateFlight(
                    getAirPortsDataBase(), getAirCraftsDataBase(), 
                    getPilotsDataBase(), getAirlinesDataBase());        
            ((Admin)getAdmin()).scheduleFlight(fligth);
            ((Admin)getAdmin()).addFlight(fligth, getFlightsDataBase());            
        }
    }

    /**
     * Gets the booked flights database
     * 
     * @return the bookedFlights
     */
    public BookedFlightsDataBase getBookedFlights() {
        return bookedFlights;
    }
    
    /**
     * Gets the users database
     * 
     * @return the users
     */
    public UserDataBase getUsersDataBase() {
        return users;
    }

    /**
     * Gets the pilots database
     * 
     * @return the pilots
     */
    public PilotDataBase getPilotsDataBase() {
        return pilots;
    }

    /**
     * Gets the airports database
     * 
     * @return the airPorts
     */
    public AirPortDataBase getAirPortsDataBase() {
        return airPorts;
    }

    /**
     * Gets the aircrafts database
     * 
     * @return the airCrafts
     */
    public AirCraftDataBase getAirCraftsDataBase() {
        return airCrafts;
    }

    /**
     * Gets the journeys database
     * 
     * @return the flight
     */
    public JourneyDataBase getFlightsDataBase() {
        return flights;
    }

    /**
     * Gets the airlines database
     * 
     * @return the airlines
     */
    public AirLinesDataBase getAirlinesDataBase() {
        return airlines;
    }   

    /**
     * Gets the admins database
     * 
     * @return the admin
     */
    public User getAdmin() {
        return admin;
    }

    /**
     * Gets the customers database
     * 
     * @return the customer
     */
    public User getCustomer() {
        return customer;
    }  
}
