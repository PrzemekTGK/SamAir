/*
 * FligthDataBase class is used to store all scheduled flights
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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Przemek Stepien
 */
public class FligthDataBase {    
    private HashMap<String, Journey> scheduledFlights;
    private ArrayList<String> airlines;

    public FligthDataBase(File file) {
        this.scheduledFlights = new HashMap<String, Journey>();
        this.airlines = new ArrayList<String>();
        Scanner scanFile = null;
        try {
            scanFile = new Scanner(file);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FligthDataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
        int i = 0;
        while (scanFile.hasNextLine()) {
            this.airlines.add(scanFile.nextLine());
            System.out.println(this.airlines.get(i++));
        }
    }

    /**
     * @return the scheduledFlights
     */
    public HashMap<String, Journey> getScheduledFlights() {
        return scheduledFlights;
    }
    
    public void generateFlight(AirPortDataBase airports){
        Random randomGen = new Random(System.nanoTime());
        Fligth fligth = new Fligth();
        AirPort origin = null;
        AirPort destination = null;
        AirPlane airplane = null;
        String airlines = this.airlines.get(this.airlines.size() - 1);
        
        
        Iterator airportsIterator = airports.getAirPorts().entrySet().iterator();
        System.out.println("In generate fligth");
        while (airportsIterator.hasNext()) {
            Map.Entry next = (Map.Entry) airportsIterator.next();
            AirPort tempAirport = (AirPort) next.getValue();
            if (tempAirport.getName().equalsIgnoreCase("dublin airport")){
                origin = tempAirport;
            }
            System.out.println(tempAirport);            
        }
    }
}
