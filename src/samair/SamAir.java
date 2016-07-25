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
     */
    public static void main(String[] args) {
        // Logic object created to call its startProgram method
        Logic logic = new Logic();
        // Start the program
        logic.startProgram();
    }
}