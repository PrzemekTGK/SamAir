/*
 * FlightDataBase class is used to store all scheduled flights
 */
package samair;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Przemek Stepien
 */
public class FlightDataBase implements Serializable {

    // HashMap of upcoming flights
    private HashMap<String, Journey> scheduledFlights;
    // ArrayList of airLines
    private ArrayList<String> airLines;

    /* 
     Constructor initializes the hashmap of flights to new instance of hashmap
     object. Initializes the ArrayList of airLines to new instance of ArrayList object
     and populates that list with list of airLines read in from a file passed as argument
     */
    public FlightDataBase(File file) {

        // Initialized HashMap and ArrayList to new instances of those objects
        this.scheduledFlights = new HashMap<String, Journey>();
        this.airLines = new ArrayList<String>();
        // Declared Scanner object refernce vatiable
        Scanner scanFile = null;
        try {
            // Read in the file
            scanFile = new Scanner(file);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FlightDataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
        // Populate the list from with the content of the file
        while (scanFile.hasNextLine()) {
            this.airLines.add(scanFile.nextLine());
        }
    }

    /*
     Generate a random flight. AirPortDataBase, AirCraftDataBase and PilotDataBase
     objects are passed in to this method to get access to all available airports,
     aircrafts and pilots need to generate the fligth.
     */
    public Flight generateFlight(AirPortDataBase airports, AirCraftDataBase airCrafts,
            PilotDataBase pilots) {
        // Random object created for random choice of specific elements
        Random randomGen = new Random(System.nanoTime());
        // Declared fields needed for Flight object creation
        String airlines = this.getAirlines().get(randomGen.nextInt(this.getAirlines().size() - 1));
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
                origin.getLongitude(), destination.getLatitude(), destination.getLongitude(), "KM");
        
        // Calculate fligth duration in floating point numbers
        flightDurationFloat = calculateFligthDurationInDecimal(distance);
        // Convert fligth duration from floating point numbers to 
        // text represantation of actual hours and minutes
        flightDurationText = convertDecimalToHours(flightDurationFloat);
        // Convert duration to miliseconds
        flightDurationMillis = (long) (flightDurationFloat * 60 * 60 * 1000);        

        // Create a flight wth all detailes aquired before
        flight = new Flight(airlines, origin, destination, airplane,
                flightDurationText, flightDurationMillis);
        return flight;
    }
    
    /*
     Select the pilot and an AirPlane for the fligth based on distance
     between origin and destination. Fligths are split in 2 categories:
     Less or equal to 12 hours and more than 12 hours.
     */
    protected AirCraft setAirCraft(AirCraft airplane, AirCraftDataBase airCrafts,
            PilotDataBase pilots, int flightDurationFloat){
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

    // Converts hours as floating point number to actual hours and minutes
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

    // Calculates duration of fligth in hours as floating point numbers
    protected float calculateFligthDurationInDecimal(double distance) {
        // Distance is passed in as argument and divided by speed of airplane (~800km/h)
        float flightDuration = (float) distance / 800;
        return flightDuration;
    }

    // Using haversine formula to calculate the distance between airports
    // Mathod aquired from MobiTechTutor.
    protected double calculateDistance(double lat1, double lon1, double lat2,
            double lon2, String unit) {
        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2))
                + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2))
                * Math.cos(deg2rad(theta));
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

    // Convert degrees to radians
    private double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    // Convert radians to degrees
    private double rad2deg(double rad) {
        return (rad * 180 / Math.PI);
    }

    /**
     * @return the scheduledFlights
     */
    public HashMap<String, Journey> getScheduledFlights() {
        return scheduledFlights;
    }

    /**
     * @return the airLines
     */
    public ArrayList<String> getAirlines() {
        return airLines;
    }   
}
