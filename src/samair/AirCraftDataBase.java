/*
 *
 */
package samair;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Przemek Stepien
 */
public class AirCraftDataBase {
    private HashMap<String, AirCraft> airCrafts;

    public AirCraftDataBase() {
        this.airCrafts = new HashMap<String, AirCraft>();
    }

    /**
     * @return the airCrafts
     */
    public HashMap<String, AirCraft> getAirCrafts() {
        return airCrafts;
    }
    
    
    // Read csv files of specific format conatining details about airplanes
    // File to be read from is passed as an argument of this method
    public ArrayList readAirplanesFromCsvFile(File file) throws FileNotFoundException {

        // ArrayList of ArrayLists object to store ArrayList each containing
        // details about individual model of an airplane
        ArrayList<ArrayList<String>> airplanesList = new ArrayList<ArrayList<String>>();
        // Csv ArrayList object to store read in cvs file 
        ArrayList<String> csvList = new ArrayList<String>();
        // ArrayList object reference variable declared to
        // store details about individual airplane
        ArrayList<String> airplaneAsList = null;

        // Scanner object to read in passed in csv file
        Scanner scanFile = new Scanner(file);
        // Set a delimiter for the csv file
        scanFile.useDelimiter(",");

        // Emtpy String declared to store an element of read in csv file
        String csvFileElement = null;
        // Empty String declared to store airplane's make for csvList separation
        String airplaneMake = null;

        // Populate csv ArrayList with elements from read in csv file
        while (scanFile.hasNext()) {
            csvFileElement = scanFile.next().trim();
            csvList.add(csvFileElement);
        }

        // Initialize airplane's make String to first index of csv ArrayList
        airplaneMake = csvList.get(0);

        // Loop thru csvList, break it down to individual airplane details
        // Save those details in an ArrayList airPlaneList and save that list
        // in an ArrayList of ArrayLists
        Label:
        for (int i = 0; i < csvList.size();) {
            // New airPlaneList ArrayList object created to store details
            // about individual airplane.
            airplaneAsList = new ArrayList<String>();

            // Check first index of csvList and if it's equal to
            // separation String airplaneMake set before            
            if (i == 0 && csvList.get(i).equals(airplaneMake)) {
                // Loops thru csvList from index 0 
                for (int j = 0;; j++) {
                    // String at index j of csvList is equal to airplaneMake String
                    if (j != 0 && csvList.get(j).equals(airplaneMake)) {
                        // ArrayList airplaneList is added to ArrayList airplanesList
                        airplanesList.add(airplaneAsList);
                        // indef i of outer loop is set to index j of inner loop
                        i = j;
                        // Outer loop skips to next iteratrion
                        continue Label;
                        // String at index j of csvList not equal to airplanMake String
                    } else {
                        // AirplaneList gets element index j feom csvList
                        airplaneAsList.add(csvList.get(j));
                    }
                }
                // Check non first index of csvList and if it's equal to
                // separation String airplaneMake set before
            } else if (i != 0 && csvList.get(i).equals(airplaneMake)) {
                // Loops thru csvList from index j set to outer loop's index i
                for (int j = i;; j++) {
                    // String at index j of csvList is equal to airplaneMake String
                    if (j != i && csvList.get(j).equals(airplaneMake)) {
                        // ArrayList airplaneList is added to ArrayList airplanesList
                        airplanesList.add(airplaneAsList);
                        // indef i of outer loop is set to index j of inner loop
                        i = j;
                        // Outer loop skips to next iteratrion
                        continue Label;
                        // String at index j of csvList not equal to airplanMake String
                    } else {
                        // ArrayList airplaneList gets element index j feom csvList
                        airplaneAsList.add(csvList.get(j));
                        // Index j is equal to last index of csvList
                        if (j == csvList.size() - 1) {
                            // ArrayList airplaneList gets element index j feom csvList
                            airplanesList.add(airplaneAsList);
                            // Outer loop is terminated
                            break Label;
                        }
                    }
                }
            }
        }
        // ArrayList of ArrayLists containing airplane detail is returned
        return airplanesList;
    }

    // Generate a random airCraft object from ArrayList of ArrayLists 
    // read in from the file containing details about airplanes
    public AirCraft generateAirCraft(ArrayList<ArrayList<String>> airPlanes) {
        // Randome number generetor object
        Random randomGen = new Random(System.nanoTime());
        // Randomly selecte airplane List from List passed in as argument
        ArrayList<String> airPlane = airPlanes.get(randomGen.nextInt(airPlanes.size()));
        // Set up airplanes make, model and random capacity
        String make = airPlane.get(0);
        String model = airPlane.get(1);
        short seats = Short.parseShort(airPlane.get(randomGen.nextInt(airPlane.size() - 3) + 2));
        // Create AirCraft object based arguments setup before
        AirCraft airCraft = new AirPlane(make, model, new Pilot(), seats);
        // New AirCraft object is returned
        return airCraft;
    }


}
