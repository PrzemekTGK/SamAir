/*
 * BookedFlight class used to represent flight booked by the customer
 */
package samair;

import java.io.Serializable;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Przemek Stepien
 * 
 */
public class BookedFlight implements Serializable{

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
    private String customerFirstName;
    private String customerLastName;

    public BookedFlight() {
    }
    
    /**
     * Gets flight duration as String
     * 
     * @return the flightDuration
     */
    public String getFlightDuration() {
        return flightDuration;
    }

    /**
     * Sets flight duration.
     * 
     * @param flightDuration the flightDuration to set
     */
    public void setFlightDuration(String flightDuration) {
        this.flightDuration = flightDuration;
    }

    /**
     * Gets AirLines assigned to the flight
     * 
     * @return the airLines
     */
    public String getAirLines() {
        return airLines;
    }

    /**
     * Sets AirLines to the flight
     * 
     * @param airLines the airLines to set
     */
    public void setAirLines(String airLines) {
        this.airLines = airLines;
    }

    /**
     * Gets origin of the flight
     *
     * @return the origin
     */
    public AirPort getOrigin() {
        return origin;
    }

    /**
     * Sets origin of the flight
     * 
     * @param origin the origin to set
     */
    public void setOrigin(AirPort origin) {
        this.origin = origin;
    }

    /**
     * Gets destination of the flight
     * 
     * @return the destination
     */
    public AirPort getDestination() {
        return destination;
    }

    /**
     * Sets destination of the flight
     * 
     * @param destination the destination to set
     */
    public void setDestination(AirPort destination) {
        this.destination = destination;
    }

    /**
     * Gets date of the flight
     * 
     * @return the dateOfFlight
     */
    public Date getDateOfFlight() {
        return dateOfFlight;
    }

    /**
     * Sets date of the flight
     * 
     * @param dateOfFlight the dateOfFlight to set
     */
    public void setDateOfFlight(Date dateOfFlight) {
        this.dateOfFlight = dateOfFlight;
    }

    /**
     * Gets arrival time of the flight
     * 
     * @return the arrivalTime
     */
    public Time getArrivalTime() {
        return arrivalTime;
    }

    /**
     * Sets arrival time of the flight
     * 
     * @param arrivalTime the arrivalTime to set
     */
    public void setArrivalTime(Time arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    /**
     * Gets departure time of the flight
     * 
     * @return the departureTime
     */
    public Time getDepartureTime() {
        return departureTime;
    }

    /**
     * Sets departure time of the flight
     * 
     * @param departureTime the departureTime to set
     */
    public void setDepartureTime(Time departureTime) {
        this.departureTime = departureTime;
    }

    /**
     * Gets the number of the flight
     * 
     * @return the flightNumber
     */
    public String getFlightNumber() {
        return flightNumber;
    }

    /**
     * Sets the number of the flight
     * 
     * @param flightNumber the flightNumber to set
     */
    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    /**
     * Gets AirCraft assigned to the flight
     * 
     * @return the airCraftAssigned
     */
    public AirCraft getAirCraftAssigned() {
        return airCraftAssigned;
    }

    /**
     * Sets AirCraft assigned to the flight
     * 
     * @param airCraftAssigned the airCraftAssigned to set
     */
    public void setAirCraftAssigned(AirCraft airCraftAssigned) {
        this.airCraftAssigned = airCraftAssigned;
    }

    /**
     * Gets the first name of the customer that booked the flight
     * 
     * @return the customerFirstName
     */
    public String getCustomerFirstName() {
        return customerFirstName;
    }

    /**
     * Sets the first name of the customer that booked the flight
     * 
     * @param customerFirstName the customerFirstName to set
     */
    public void setCustomerFirstName(String customerFirstName) {
        this.customerFirstName = customerFirstName;
    }

    /**
     * Gets the last name of the customer that booked the flight
     * 
     * @return the customerLastName
     */
    public String getCustomerLastName() {
        return customerLastName;
    }

    /**
     * Sets the last name of the customer that booked the flight
     * 
     * @param customerLastName the customerLastName to set
     */
    public void setCustomerLastName(String customerLastName) {
        this.customerLastName = customerLastName;
    }

    /**
     * Prints out all the fields of this class and their values to the console
     * 
     * @return String representation of BookedFlight object
     */
    @Override
    public String toString() {
        return "=============================================="
                + "\nBooked Flight:"
                + "\nPassanger name: " + customerFirstName + " " + customerLastName + "\n"
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
