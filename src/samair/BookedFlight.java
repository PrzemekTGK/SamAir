/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package samair;

import java.io.Serializable;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Przemek Stepien
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
     * @return the airLines
     */
    public String getAirLines() {
        return airLines;
    }

    /**
     * @param airLines the airLines to set
     */
    public void setAirLines(String airLines) {
        this.airLines = airLines;
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
     * @return the customerFirstName
     */
    public String getCustomerFirstName() {
        return customerFirstName;
    }

    /**
     * @param customerFirstName the customerFirstName to set
     */
    public void setCustomerFirstName(String customerFirstName) {
        this.customerFirstName = customerFirstName;
    }

    /**
     * @return the customerLastName
     */
    public String getCustomerLastName() {
        return customerLastName;
    }

    /**
     * @param customerLastName the customerLastName to set
     */
    public void setCustomerLastName(String customerLastName) {
        this.customerLastName = customerLastName;
    }

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
