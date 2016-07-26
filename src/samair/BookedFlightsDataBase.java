/*
 * BoookedFlightDataBase class is used to store all booked flights
 */
package samair;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Przemek Stepien
 */
public class BookedFlightsDataBase implements Serializable{
    
        private HashMap<String, ArrayList<BookedFlight>> bookedFlights;

    public BookedFlightsDataBase() {
        this.bookedFlights = new HashMap<>();
    }

    /**
     * Gets the HashMap object bookedFlights that's storing all the bookedFlight objects
     * 
     * @return the airCrafts
     */
    public HashMap<String, ArrayList<BookedFlight>> getBookedFlights() {
        return bookedFlights;
    }
    
}
