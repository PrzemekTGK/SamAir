/*
 *
 */
package samair;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Przemek Stepien
 */
public class Flight implements Journey{
    
    private String airLine;
    private String origin;
    private String destination;
    private Date departureTime;
    private Date arrivalTime;
    private Date dateOfFlight;
    private String flightNumber;
    private AirCraft airCraftAssigned;
    private SimpleDateFormat timeParser = new SimpleDateFormat("kk:mm");
    private SimpleDateFormat dateParser = new SimpleDateFormat("dd-MM-yyyy");

    public Flight(String airLine,String origin, String destination) {
        this.airLine = airLine;
        this.origin = "Dublin";
        this.destination = destination;
    }
    
    public void schedule(Date arrivalTime){
        
    }
    
    public void schedule(Date arrivalTime, Date departureTime){
        
    }   
    
    /**
     * @return the origin
     */
    public String getOrigin() {
        return origin;
    }

    /**
     * @param origin the origin to set
     */
    public void setOrigin(String origin) {
        this.origin = origin;
    }

    /**
     * @return the destination
     */
    public String getDestination() {
        return destination;
    }

    /**
     * @param destination the destination to set
     */
    public void setDestination(String destination) {
        this.destination = destination;
    }

    /**
     * @return the departureTime
     */
    public Date getDepartureTime() {
        return departureTime;
    }

    /**
     * @param departureTime the departureTime to set
     */
    public void setDepartureTime(Date departureTime) {
        this.departureTime = departureTime;
    }

    /**
     * @return the arrivalTime
     */
    public Date getArrivalTime() {
        return arrivalTime;
    }

    /**
     * @param arrivalTime the arrivalTime to set
     */
    public void setArrivalTime(Date arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    /**
     * @return the dateOfFlight
     */
    public Date getDateOfFlight() {
        return dateOfFlight;
    }

    /**
     * @param dateOfFlight the dateOfFlight to set
     */
    public void setDateOfFlight(Date dateOfFlight) {
        this.dateOfFlight = dateOfFlight;
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
     * @return the airLine
     */
    public String getAirLine() {
        return airLine;
    }

    /**
     * @param airLine the airLine to set
     */
    public void setAirLine(String airLine) {
        this.airLine = airLine;
    }

    @Override
    public String toString() {
        return "Flight:" 
                + "\nAir Lines = " + airLine 
                + "\nOrigin = " + origin 
                + "\nDestination = " + destination 
                + "\nDeparture Time = " + departureTime 
                + "\nArrival Time=" + arrivalTime 
                + "\nDate Of Flight = " + dateOfFlight 
                + "\nFlight Number = " + flightNumber 
                + "\nAir Craft Assigned = " + airCraftAssigned
                + "\nPilot assinged = " + ((AirPlane)airCraftAssigned).getPilot();
    }
    
    
}
