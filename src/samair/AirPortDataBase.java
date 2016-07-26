/*
 * Psuedo database class storing all AirPort objects
 */
package samair;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 *
 * @author Przemek Stepien
 */
public class AirPortDataBase implements Serializable{

    private HashMap<Integer, AirPort> airPorts;

    public AirPortDataBase() {
        this.airPorts = new HashMap<>();
    }

    /**
     * @return the airPorts
     */
    public HashMap<Integer, AirPort> getAirPorts() {
        return airPorts;
    }

    /**
     * Generates AirPort objects from csv file and stores them in the HashMap airPorts
     * 
     * @param file is a csv file containing info about the airport
     * @throws FileNotFoundException if file is not found
     */
    public void generateAirportsFromCsvFile(File file) throws FileNotFoundException {
        final int name = 1;
        final int city = 2;
        final int country = 3;
        final int longitude = 6;
        final int latitude = 7;
        int index = 0;
        ArrayList<String> airportAsList = new ArrayList<>();
        Scanner scanFile = new Scanner(file);
        scanFile.useDelimiter(",");

        while (scanFile.hasNext()) {
            String csvElement = scanFile.next();
//            System.out.println(csvElement);
            airportAsList.add(csvElement);
            index++;

            if (index == 11) {
                if (airportAsList.get(name).contains("Station")
                        || airportAsList.get(name).contains("Train")
                        || airportAsList.get(name).contains("Base")
                        || airportAsList.get(name).contains("Transportation")
                        || airportAsList.get(name).contains("Transit")
                        || airportAsList.get(name).contains(" Centre")) {
                    index = 0;
                    airportAsList.clear();
                    continue;
                }
                AirPort airport = new AirPort(airportAsList.get(name),
                        airportAsList.get(city), airportAsList.get(country),
                        Float.parseFloat(airportAsList.get(longitude)),
                        Float.parseFloat(airportAsList.get(latitude)));

                this.airPorts.put((this.airPorts.size()), airport);
                index = 0;
                airportAsList.clear();
            }
        }
    }
}
