/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
        this.bookedFlights = new HashMap<String, ArrayList<BookedFlight>>();
    }

    /**
     * @return the airCrafts
     */
    public HashMap<String, ArrayList<BookedFlight>> getBookedFlights() {
        return bookedFlights;
    }
    
}
