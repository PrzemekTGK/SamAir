/*
 * Flight class stroing all the info about the flight
 */
package samair;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Przemek Stepien
 */
public class Flight implements Journey {

    private long flightDurationInMilliseconds;
    private SimpleDateFormat timeParser = new SimpleDateFormat("HH:mm");
    private SimpleDateFormat dateParser = new SimpleDateFormat("dd-MM-yyyy");    
    private String flightDuration;
    private String airLines;
    private AirPort origin;
    private AirPort destination;
    private Date dateOfFlight;
    private Time arrivalTime;
    private Time departureTime;
    private String flightNumber;
    private AirCraft airCraftAssigned;

    public Flight() {
    }

    public Flight(String airLine, AirPort origin, AirPort destination,
            AirCraft airCraftAssigned, String flightDuration, long flightInMillis) {
        this.airLines = airLine;
        this.origin = origin;
        this.destination = destination;
        this.airCraftAssigned = airCraftAssigned;
        this.flightDuration = flightDuration;
        this.flightDurationInMilliseconds = flightInMillis;
    }

    /**
     * Schedules a created flight
     * 
     * @param dateOfFlight is used to set the date of the flight
     * @param departureTime is used to set the departure time of the flight
     * @param arrivalTime  is used to set the arrival time of the flight
     */
    public void scheduleFlight(Date dateOfFlight, Time departureTime, Time arrivalTime) {
        this.dateOfFlight = dateOfFlight;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
    }

    /**
     * Updates a created, scheduled and active flight
     * 
     * @param arrivalTime is used to update the arrival time of the flight
     */
    public void scheduleFlight(Time arrivalTime) {
        this.setArrivalTime(arrivalTime);
        System.gc();
    }
    
    /**
     * Update a created and scheduled but not active flight
     * 
     * @param departureTime is used to set the departure time of flight
     * @param flightDuration is used to set the duration of the flight
     */
    public void scheduleFlight(Time departureTime, long flightDuration){
        this.departureTime = departureTime;
        this.flightDurationInMilliseconds = flightDuration;
    }
    
    /**
     * @return the origin
     */
    public AirPort getOrigin() {
        return origin;
    }

    /**
     * @param origin the origin to set
     */
    public void setOrigin(AirPort origin) {
        this.origin = origin;
    }

    /**
     * @return the destination
     */
    public AirPort getDestination() {
        return destination;
    }

    /**
     * @param destination the destination to set
     */
    public void setDestination(AirPort destination) {
        this.destination = destination;
    }

    /**
     * @return the arrivalTime
     */
    public Time getArrivalTime() {
        return arrivalTime;
    }

    /**
     * @param arrivalTime the arrivalTime to set
     */
    public void setArrivalTime(Time arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    /**
     * @return the departureTime
     */
    public Time getDepartureTime() {
        return departureTime;
    }

    /**
     * @param departureTime the departureTime to set
     */
    public void setDepartureTime(Time departureTime) {
        this.departureTime = departureTime;
    }

    /**
     * @return the flightNumber
     */
    public String getFlightNumber() {
        return flightNumber;
    }

    /**
     * @param flightNumber the flightNumber to set
     */
    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    /**
     * @return the airCraftAssigned
     */
    public AirCraft getAirCraftAssigned() {
        return airCraftAssigned;
    }

    /**
     * @param airCraftAssigned the airCraftAssigned to set
     */
    public void setAirCraftAssigned(AirCraft airCraftAssigned) {
        this.airCraftAssigned = airCraftAssigned;
    }

    /**
     * @return the timeParser
     */
    public SimpleDateFormat getTimeParser() {
        return timeParser;
    }

    /**
     * @return the dateParser
     */
    public SimpleDateFormat getDateParser() {
        return dateParser;
    }

    /**
     * @return the airLines
     */
    public String getAirLine() {
        return airLines;
    }

    /**
     * @param airLine the airLines to set
     */
    public void setAirLine(String airLine) {
        this.airLines = airLine;
    }

    /**
     * @return the flightDuration
     */
    public String getFlightDuration() {
        return flightDuration;
    }

    /**
     * @param flightDuration the flightDuration to set
     */
    public void setFlightDuration(String flightDuration) {
        this.flightDuration = flightDuration;
    }

    /**
     * @return the flightDurationInMilliseconds
     */
    public long getFlightDurationInMilliseconds() {
        return flightDurationInMilliseconds;
    }

    /**
     * @param flightDurationInMilliseconds the flightDurationInMilliseconds to set
     */
    public void setFlightDurationInMilliseconds(long flightDurationInMilliseconds) {
        this.flightDurationInMilliseconds = flightDurationInMilliseconds;
    }

    /**
     * @return the dateOfFlight
     */
    public Date getDateOfFlight() {
        return dateOfFlight;
    }
    
    @Override
    public String toString() {
        return "=============================================="
                + "\nFlight:"
                + "\nAir Lines: " + airLines + "\n"
                + "\nOrigin: " + origin
                + "\nDestination: " + destination
                + "\nDate of Flight: " + dateParser.format(getDateOfFlight())
                + "\nDeparture Time: " + timeParser.format(departureTime)
                + "\nArrival Time: " + timeParser.format(arrivalTime)
                + "\nFlight Duration: " + flightDuration
                + "\nFlight Number: " + flightNumber + "\n"
                + "\nPilot Assinged: " + ((AirPlane) airCraftAssigned).getPilot()
                + "\nAir Craft Assigned: " + airCraftAssigned 
                + "==============================================\n";
        
    }
}
