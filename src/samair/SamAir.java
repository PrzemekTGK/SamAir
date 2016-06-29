/*
 * Main driver class, and starting point of the program
 */
package samair;

/**
 *
 * @author Przemek Stepien
 */
public class SamAir {

    /**
     * @param args the command line arguments
     * 
     */
    public static void main(String[] args) {
        // Logic object created to call its startProgram method
        Logic logic = new Logic();
        Initializer init = logic.init(logic);
        
        ((Admin)init.getAdmin()).createFlight(init.getAirPorts(), init.getFlights(), init.getAirCrafts(), init.getPilots());
        
        // Dummy data printed to the screen
//        init.getPilots().getPilots().forEach((k, v) -> System.out.println("Key: " + k + "\nValue: " + v));
//        init.getAirPorts().getAirPorts().forEach((k, v) -> System.out.println("Key: " + k + "\n" + v));
//        init.getAirCrafts().getAirCrafts().forEach((k, v) -> System.out.println(v + "\n"));
//        init.getFlights().getScheduledFlights().forEach((k,v) -> System.out.println(v));

//        logic.startProgram(init.getUsers());
    }
}