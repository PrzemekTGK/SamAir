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
        Initializer init = null;

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
            System.out.printf("Data.ser");
        } catch (IOException i) {
            i.printStackTrace();
        }
    }
}
