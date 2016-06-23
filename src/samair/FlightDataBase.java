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
    
    public void generateFlights(){
        
    }
    
    
    private double distance(double lat1, double lon1, double lat2, double lon2, String unit) {
            double theta = lon1 - lon2;
            double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
            dist = Math.acos(dist); // returns the arc cosine
            dist = rad2deg(dist);
            dist = dist * 60 * 1.1515;
            if (unit.equalsIgnoreCase("KM")) {
                    dist = dist * 1.609344;
            } else if (unit.equalsIgnoreCase("NT")) {
                    dist = dist * 0.8684;
            }
            return (dist);
    }
    
    
    private double deg2rad(double deg) {
            return (deg * Math.PI / 180.0);
    }
    
    
    private double rad2deg(double rad) {
            return (rad * 180 / Math.PI);
    }
}
