/*
 * FlightDataBase class is used to store all scheduled flights
 */
package samair;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Random;

/**
 *
 * @author Przemek Stepien
 */
public class FlightDataBase implements Serializable {

    // HashMap of upcoming flights
    private HashMap<String, Journey> scheduledFlights;

    
    /**
     * Constructor initializes the hashmap of flights to a instance
     * of hashmap object.
     */
    public FlightDataBase( ) {
        // Initialized HashMap to new instance of this object
        this.scheduledFlights = new HashMap<String, Journey>();
    }

    /**
     * Generate a random Flight
     * @param airports
     * @param airCrafts
     * @param pilots
     * All parameters are passed in to this method to get access to all
     * available airports, aircrafts and pilots needed to generate the Flight
     * @return New Flight object with random details
     */
    public Flight generateFlight(AirPortDataBase airports, AirCraftDataBase airCrafts,
            PilotDataBase pilots, AirLinesDataBase airlines) {
        // Random object created for random choice of specific elements
        Random randomGen = new Random(System.nanoTime());
        // Declared fields needed for Flight object creation
        String airline = airlines.getAirlines().get(randomGen.nextInt(airlines.getAirlines().size() - 1));
        AirPort origin = null;
        AirPort destination = null;
        AirPlane airplane = null;
        Flight flight = new Flight();
        String flightDurationText = null;
        double flightDurationFloat = 0;
        long flightDurationMillis = 0;

        // Random Key selection to select a random airport
        int randomKey = randomGen.nextInt(airports.getAirPorts().size() - 1);

        // Loop thru HashMap from AirPortDataBase object passed in as argument
        for (Entry entry : airports.getAirPorts().entrySet()) {
            // Set specific origin airport
            if (((AirPort) entry.getValue()).getName().contains("Dublin Airport")) {
                origin = (AirPort) entry.getValue();
            }
            // Set random destination airport
            if (entry.getKey().equals(randomKey)) {
                destination = (AirPort) entry.getValue();
            }
        }

        // Calculate flight distance with calculate method that's using haversine formula
        double distance = calculateDistance(origin.getLatitude(),
                origin.getLongitude(), destination.getLatitude(), destination.getLongitude());
        
        // Calculate flight duration in floating point numbers
        flightDurationFloat = calculateFlightDurationInDecimal(distance);
        // Convert flight duration from floating point numbers to 
        // text represantation of actual hours and minutes
        flightDurationText = convertDecimalToHours(flightDurationFloat);
        // Convert duration to miliseconds
        flightDurationMillis = (long) (flightDurationFloat * 60 * 60 * 1000);
        // Set the airplane for the flight
        airplane = (AirPlane) setAirCraft(airplane, airCrafts, pilots, flightDurationFloat);

        // Create a flight wth all detailes aquired before
        flight = new Flight(airline, origin, destination, airplane,
                flightDurationText, flightDurationMillis);
        return flight;
    }
    
    /**
     * Select the pilot and an AirPlane for the flight based on distance
     * between origin and destination. Flights are split in 2 categories:
     * Less or equal to 12 hours and more than 12 hours.
     * @param flightDurationFloat is passed to determine type of AirPlane to create     * 
     * @param airplane
     * @param airCrafts
     * @param pilots
     * Remaining parameters are passed in to get access to all
     * available airports,aircrafts and pilots needed to generate the AirPlane.
     * @return new AirPlane object
     */
    protected AirCraft setAirCraft(AirCraft airplane, AirCraftDataBase airCrafts,
            PilotDataBase pilots, double flightDurationFloat){
        // Flight longer than 12h
        if (flightDurationFloat > 12) {
            // Select pilot with rating higher than 5 for fligths longer than 12h
            Pilot pilot = null;
            for (Entry entry : pilots.getPilots().entrySet()) {
                pilot = (Pilot) entry.getValue();
                if (pilot.getRating() > 5) {
                    break;
                }
            }
            // Select an airplane capable of trip longer than 12h
            for (Entry entry : airCrafts.getAirCrafts().entrySet()) {
                if ((((AirPlane) entry.getValue()).getModel().contains("400")
                        || ((AirPlane) entry.getValue()).getModel().contains("8I")
                        || ((AirPlane) entry.getValue()).getModel().contains("340-600")
                        || ((AirPlane) entry.getValue()).getModel().contains("380"))
                        && !((AirPlane) entry.getValue()).isActive()) {
                    airplane = (AirPlane) entry.getValue();
                    ((AirPlane)airplane).setPilot(pilot);
                    break;
                }
            }
        // Flight shoerter than 12h
        } else {
            // Select pilot with rating up to 5 for fligths up to 12h
            Pilot pilot = null;
            for (Entry entry : pilots.getPilots().entrySet()) {
                pilot = (Pilot) entry.getValue();
                if (pilot.getRating() <= 5) {
                    break;
                }
            }
            // Select aiplane for flight up to 12h long
            for (Entry entry : airCrafts.getAirCrafts().entrySet()) {
                if ((!((AirPlane) entry.getValue()).getModel().contains("400")
                        && !((AirPlane) entry.getValue()).getModel().contains("8I")
                        && !((AirPlane) entry.getValue()).getModel().contains("340-600")
                        && !((AirPlane) entry.getValue()).getModel().contains("380"))
                        && !((AirPlane) entry.getValue()).isActive()) {
                    airplane = (AirPlane) entry.getValue();
                    ((AirPlane)airplane).setPilot(pilot);
                    break;
                }
            }
        }
        return airplane;
    }

    /**
     * Converts hours as floating point number String representation
     * of actual hours and minutes
     * @param decimalDuration is passed to be converted to String version of flight duration
     * @return String representation of flight duration
     */
    protected String convertDecimalToHours(double decimalDuration) {
        // Convert hours as floating point to minutes as a floating points 
        double minutesAndSeconds = decimalDuration * 60;
        // Round minutes to full nummber and convert to int to remove floating point
        int minutes = (int) Math.round(minutesAndSeconds);
        // Calculate how many hours is in all the minutes calculated before
        int hours = minutes / 60;
        // Calculate remaining minutes after calculating all the hours
        minutes = minutes % 60;

        // Concatonate and format minutes and hours as String and return it
        String fligthDuration = hours + "h " + minutes + "m";
        return fligthDuration;
    }

    /**
     * Calculates duration of flight in hours as floating point numbers
     * @param distance in Km is passed to calculate flight's duration 
     * @return flight's duration in hours as float;
     */
    protected float calculateFlightDurationInDecimal(double distance) {
        // Distance is passed in as argument and divided by speed of airplane (~800km/h)
        float flightDuration = (float) distance / 800;
        return flightDuration;
    }

    /**
     * Using Haversine formula to calculate the distance between airports
     * Method acquired from MobiTechTutor.
     * @param lat1 is the latitude of origin airport
     * @param lon1 is the longitude of origin airport
     * @param lat2 is the latitude of destination airport
     * @param lon2 is the longitude of destination airport
     * @return distance in Km as double
     */
    protected double calculateDistance(double lat1, double lon1, double lat2,
            double lon2) {
        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2))
                + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2))
                * Math.cos(deg2rad(theta));
        dist = Math.acos(dist); // returns the arc cosine
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;
        dist = dist * 1.609344;
        return (dist);
    }

    /**
     * Convert degrees to radians
     * @param deg degrees to be converted
     * @return degrees converted to radians
     */
    private double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    /**
     * Convert radians to degrees
     * @param rad radians to be converted 
     * @return radians converted to degrees
     */
    private double rad2deg(double rad) {
        return (rad * 180 / Math.PI);
    }

    /**
     * @return the scheduledFlights
     */
    public HashMap<String, Journey> getScheduledFlights() {
        return scheduledFlights;
    }
}
