/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package samair;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Przemek Stepien
 */
public class PilotDataBase {
    private HashMap<String, Pilot> pilots;

    public PilotDataBase() {
        this.pilots = new HashMap<String, Pilot>();
    }

    /**
     * @return the pilots
     */
    public HashMap<String, Pilot> getPilots() {
        return pilots;
    }
    
        // Read in a list of names and return it as an ArrayList
    public ArrayList readTextFile(File file) throws FileNotFoundException {
        // ArrayList object to store names read in from the file
        ArrayList<String> fileAsList = new ArrayList<String>();
        // Scanner object to read the file
        Scanner scanFile = new Scanner(file);

        // Populate arraylist with names from the file passed in
        while (scanFile.hasNext()) {
            fileAsList.add(scanFile.next());
        }

        return fileAsList;
    }

    // Generate a random pilot
    public Pilot generatePilot(ArrayList<String> names, ArrayList<String> surnames,
            HashMap<String, Pilot> pilots) {
        // Random number generator with to randomly pick a name for the pilot
        Random randomGen = new Random();
        // Pilot's name and surname randomly chosen from name/surname lists
        String name = names.get(randomGen.nextInt(names.size()));
        String surname = surnames.get(randomGen.nextInt(surnames.size()));
        // Randomly chosen pilot's rating from 1-10
        byte rating = (byte) (randomGen.nextInt(10) + 1);
        // Check condition for duplicated keys
        boolean duplicatedID = true;
        // Empty String declared for unique pilot ID generation
        String pilotID = null;
        // Pseudo index of passed in hashmap to determine the end of it
        int index = 0;
        // Empty Pilot object refernce variable declared for created Pilot object
        Pilot pilot = null;

        if (pilots.isEmpty()) {
            // Random chars to compose pilot's ID
            char iDchar1 = (char) (randomGen.nextInt(35) + 65);
            char iDchar2 = (char) (randomGen.nextInt(35) + 65);
            char iDchar3 = (char) (randomGen.nextInt(10) + 48);
            // Pilot ID composed of size of pilots Hashmap size and 3 random chars
            pilotID = "" + pilots.size() + iDchar1 + iDchar2 + iDchar3;
            // New Pilot object created with details set before
            pilot = new Pilot((name + " " + surname), pilotID, rating);
        } else {
            Label:
            do {
                // Random chars to compose pilot's ID
                char iDchar1 = (char) (randomGen.nextInt(25) + 66);
                char iDchar2 = (char) (randomGen.nextInt(25) + 66);
                char iDchar3 = (char) (randomGen.nextInt(10) + 48);
                // Pilot ID composed of size of pilots Hashmap size and 3 random chars
                pilotID = "" + pilots.size() + iDchar1 + iDchar2 + iDchar3;
                // Iterator for passed in hashmap
                Iterator pilotIterator = pilots.entrySet().iterator();
                while (pilotIterator.hasNext()) {
                    // Single current entry of passed in hashmap
                    Map.Entry currentEntry = (Map.Entry) pilotIterator.next();
                    
                    // Current entrie's key is same as one generated before
                    // Generate a new key and restart the check for duplicate
                    if (currentEntry.getKey().equals(pilotID)) {
                        continue Label;
                    // Current entries key is different than generated one
                    // Move to the next entry
                    } else {
                        index++;
                    }
                    // Psuedo index reached the end of the map whitout finding duplicated keys
                    if (index == pilots.size()) {
                        // New Pilot object created with details set before
                        pilot = new Pilot((name + " " + surname), pilotID, rating);
                        duplicatedID = false;
                    }
                }
            } while (duplicatedID);
        }
        return pilot;
    }
}
