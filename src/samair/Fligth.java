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
public class Fligth implements Journey {

    private String fligthDuration;
    private String airLine;
    private AirPort origin;
    private AirPort destination;
    private Date departureTime;
    private Date arrivalTime;
    private Date dateOfFligth;
    private String fligthNumber;
    private AirCraft airCraftAssigned;
    private SimpleDateFormat timeParser = new SimpleDateFormat("kk:mm");
    private SimpleDateFormat dateParser = new SimpleDateFormat("dd-MM-yyyy");

    public Fligth() {
    }

    public Fligth(String airLine, AirPort origin, AirPort destination,
            AirCraft airCraftAssigned, String fligthDuration) {
        this.airLine = airLine;
        this.origin = origin;
        this.destination = destination;
        this.airCraftAssigned = airCraftAssigned;
        this.fligthDuration = fligthDuration;
    }

    public void schedule(Date arrivalTime) {

    }

    public void schedule(Date arrivalTime, Date departureTime, Date dateOfFligth) {

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
     * @return the dateOfFligth
     */
    public Date getDateOfFligth() {
        return dateOfFligth;
    }

    /**
     * @param dateOfFligth the dateOfFligth to set
     */
    public void setDateOfFligth(Date dateOfFligth) {
        this.dateOfFligth = dateOfFligth;
    }

    /**
     * @return the fligthNumber
     */
    public String getFligthNumber() {
        return fligthNumber;
    }

    /**
     * @param fligthNumber the fligthNumber to set
     */
    public void setFligthNumber(String fligthNumber) {
        this.fligthNumber = fligthNumber;
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

    /**
     * @return the fligthDuration
     */
    public String getFligthDuration() {
        return fligthDuration;
    }

    /**
     * @param fligthDuration the fligthDuration to set
     */
    public void setFligthDuration(String fligthDuration) {
        this.fligthDuration = fligthDuration;
    }

    @Override
    public String toString() {
        return "Flight:"
                + "\nAir Lines = " + airLine
                + "\nOrigin " + origin
                + "\nDestination " + destination
                + "\nDeparture Time = " + departureTime
                + "\nArrival Time = " + arrivalTime
                + "\nDate Of Flight = " + dateOfFligth
                + "\nFlight Number = " + fligthNumber
                + "\nAir Craft Assigned = " + airCraftAssigned
                + "\nPilot assinged = " + ((AirPlane) airCraftAssigned).getPilot();
    }
}
