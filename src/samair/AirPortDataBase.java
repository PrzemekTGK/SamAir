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
import java.util.Scanner;

/**
 *
 * @author Przemek Stepien
 */
public class AirPortDataBase {

    private HashMap<String, AirPort> airPorts;

    public AirPortDataBase() {
        this.airPorts = new HashMap<String, AirPort>();
    }

    /**
     * @return the airPorts
     */
    public HashMap<String, AirPort> getAirPorts() {
        return airPorts;
    }

    public void generateAirportsFromCsvFile(File file) throws FileNotFoundException {
        final int name = 1;
        final int city = 2;
        final int country = 3;
        final int longitude = 6;
        final int latitude = 7;
        int index = 0;
        ArrayList<String> airportAsList = new ArrayList<String>();
        Scanner scanFile = new Scanner(file);
        scanFile.useDelimiter(",");

        while (scanFile.hasNext()) {
            String csvElement = scanFile.next();
//            System.out.println(csvElement);
            airportAsList.add(csvElement);
            index++;
            if (index == 11) {
                AirPort airport = new AirPort(airportAsList.get(name),
                        airportAsList.get(city), airportAsList.get(country),
                        Float.parseFloat(airportAsList.get(longitude)),
                        Float.parseFloat(airportAsList.get(latitude)));
               
                if (airport.getName().contains("Station") || airport.getName().contains("Train")) {
                    continue;
                } else {
                    this.airPorts.put(("" + this.airPorts.size()), airport);
                    index = 0;
                    airportAsList.clear();                    
                }
            }
        }
    }
}
