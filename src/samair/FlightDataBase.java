/*
 * FlightDataBase class is used to store all scheduled flights
 */
package samair;

import java.util.HashMap;

/**
 *
 * @author Przemek Stepien
 */
public class FlightDataBase {    
    private HashMap<String, Journey> scheduledFlights;

    public FlightDataBase() {
        this.scheduledFlights = new HashMap<String, Journey>();
    }

    /**
     * @return the scheduledFlights
     */
    public HashMap<String, Journey> getScheduledFlights() {
        return scheduledFlights;
    }        
}
