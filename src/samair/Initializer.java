/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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

        getUsers().getUsers().put(logic.generateUniqueKey(getUsers().getUsers()), getAdmin());
        getUsers().getUsers().put(logic.generateUniqueKey(getUsers().getUsers()), getCustomer());

        // Initializing Hashmaps with randomly created pilots, airplanes
        try {
            // Creat 100 random pilots
            for (int i = 0; i < 100; i++) {
                Pilot pilot = getPilots().generatePilot(getPilots().readTextFile(
                        new File("names.txt")),
                        getPilots().readTextFile(new File("surnames.txt")),
                        getPilots().getPilots());
                getPilots().getPilots().put(pilot.getPilotID(), pilot);
            }
            // Create 50 random Beoing airplanes
            for (int i = 0; i < 50; i++) {
                getAirCrafts().getAirCrafts().put(logic.generateUniqueKey(getAirCrafts().getAirCrafts()), getAirCrafts().generateAirCraft(getAirCrafts().readAirplanesFromCsvFile(
                        new File("boeing.csv"))));
            }
            // Create 50 random Airbus airplanes
            for (int i = 0; i < 50; i++) {
                getAirCrafts().getAirCrafts().put(logic.generateUniqueKey(getAirCrafts().getAirCrafts()), getAirCrafts().generateAirCraft(getAirCrafts().readAirplanesFromCsvFile(
                        new File("airbus.csv"))));
            }
            
            getAirPorts().generateAirportsFromCsvFile(new File("airports.csv"));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SamAir.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Generate 100 random flights
        for (int i = 0; i < 100; i++) {
            Flight fligth = getFlights().generateFlight(getAirPorts(), getAirCrafts(), getPilots());        
            ((Admin)getAdmin()).scheduleFlight(fligth);
            ((Admin)getAdmin()).addFlight(fligth, getFlights());            
        }
               
        // Display all airplanes and pilots created
//        getAirPorts().getAirPorts().forEach((k, v) -> System.out.println("Key: " + k + "\n" + v));
//        getAirCrafts().getAirCrafts().forEach((k, v) -> System.out.println(v + "\n"));
//        getPilots().getPilots().forEach((k, v) -> System.out.println("Key: " + k + "\nValue: " + v));
//        getFlights().getScheduledFlights().forEach((k,v) -> System.out.println(v));
        
        return getUsers();
    }

    /**
     * @return the users
     */
    public UserDataBase getUsers() {
        return users;
    }

    /**
     * @return the pilots
     */
    public PilotDataBase getPilots() {
        return pilots;
    }

    /**
     * @return the airPorts
     */
    public AirPortDataBase getAirPorts() {
        return airPorts;
    }

    /**
     * @return the airCrafts
     */
    public AirCraftDataBase getAirCrafts() {
        return airCrafts;
    }

    /**
     * @return the flight
     */
    public FlightDataBase getFlights() {
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
