/*
 *
 */
package samair;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

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
        // Initializer object declared 
        Initializer init = null;
        // Initializer object loaded in from the file or created and saved to the file
        try {
            FileInputStream fileIn = new FileInputStream("Data.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            init = (Initializer) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException i) {
            init = new Initializer();
            init.initialize(logic);
            serialize(init);
        } catch (ClassNotFoundException c) {
            System.out.println("Employee class not found");
            c.printStackTrace();
        }
        
        // Dummy data printed to the screen
        init.getPilots().getPilots().forEach((k, v) -> System.out.println("Key: " + k + "\nValue: " + v));
        init.getAirPorts().getAirPorts().forEach((k, v) -> System.out.println("Key: " + k + "\n" + v));
        init.getAirCrafts().getAirCrafts().forEach((k, v) -> System.out.println(v + "\n"));
        init.getFlights().getScheduledFlights().forEach((k,v) -> System.out.println(v));


//        logic.startProgram(users);
    }

    public static void serialize(Serializable data) {
        try {
            FileOutputStream fileOut
                    = new FileOutputStream("Data.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(data);
            out.close();
            fileOut.close();
            System.out.println("Data saved in Data.ser");
        } catch (IOException i) {
            i.printStackTrace();
        }
    }
}
