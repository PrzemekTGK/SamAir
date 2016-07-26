/*
 * Pseudo airlines data base class to store different airlines for flight creation
 */
package samair;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Przemek Stepien
 */
public class AirLinesDataBase implements Serializable{

    // ArrayList of airLines
    private ArrayList<String> airLines = new ArrayList<String>();
    
    /**
     * Constructor populates airLines ArrayList with text file passed in as argument
     * @param file text file of airlines
     */
    public AirLinesDataBase(File file) {
        // Declared Scanner object refernce vatiable 
        Scanner scanFile = null;
        try {
            // Read in the file
            scanFile = new Scanner(file);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(JourneyDataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
        // Populate the list from with the content of the file
        while (scanFile.hasNextLine()) {
            this.airLines.add(scanFile.nextLine());
        }
    }

    /**
     * @return the airLines
     */
    public ArrayList<String> getAirlines() {
        return airLines;
    }
}
