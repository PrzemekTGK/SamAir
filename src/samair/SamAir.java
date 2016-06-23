/*
 *
 */
package samair;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Przemek Stepien
 */
public class SamAir {
    
    // Declared pseudo data base objects to store various items neeeded for the program
    private static AirCraftDataBase airCrafts = new AirCraftDataBase();
    private static FlightDataBase flights = new FlightDataBase();
    private static UserDataBase users = new UserDataBase();
    private static PilotDataBase pilots = new PilotDataBase();
    private static AirPortDataBase airports = new AirPortDataBase();

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        // Logic object created to call its startProgram method
        Logic logic = new Logic();
        
        // Dummy users data
        User admin = new Admin("admin", "123");
        User customer = new Customer("Sam", "123");
        users.getUsers().put(logic.generateUniqueKey(users.getUsers()), admin);
        users.getUsers().put(logic.generateUniqueKey(users.getUsers()), customer);
        
        // Initializing Hashmaps with randomly created pilots, airplanes and flghts
        try {
            for (int i = 0; i < 100; i++) {
                Pilot pilot = pilots.generatePilot(pilots.readTextFile(
                        new File("names.txt")),
                        pilots.readTextFile(new File("surnames.txt")),
                        pilots.getPilots());
                pilots.getPilots().put(pilot.getPilotID(), pilot);
            }
            for (int i = 0; i < 50; i++) {
                airCrafts.getAirCrafts().put(
                        logic.generateUniqueKey(airCrafts.getAirCrafts())
                        , airCrafts.generateAirCraft(airCrafts.readAirplanesFromCsvFile(
                                new File("boeing.csv"))));
            }
            for (int i = 0; i < 50; i++) {
                airCrafts.getAirCrafts().put(
                        logic.generateUniqueKey(airCrafts.getAirCrafts())
                        , airCrafts.generateAirCraft(airCrafts.readAirplanesFromCsvFile(
                                new File("airbus.csv"))));
            }
            airports.generateAirportsFromCsvFile(new File("airports.txt"));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SamAir.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        // Display all airplanes and pilots created
        airports.getAirPorts().forEach((k, v) -> System.out.println(v));
//        airCrafts.getAirCrafts().forEach((k, v) -> System.out.println(v + "\n"));
//        pilots.getPilots().forEach((k, v) -> System.out.println("Key: " + k + "\nValue: " + v));


//        logic.startProgram();
    }
}
