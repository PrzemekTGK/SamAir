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

    private byte fligthDuration;
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
            AirCraft airCraftAssigned) {
        this.airLine = airLine;
        this.origin = origin;
        this.destination = destination;
        this.airCraftAssigned = airCraftAssigned;
    }

    public void schedule(Date arrivalTime) {

    }

    public void schedule(Date arrivalTime, Date departureTime, Date dateOfFligth) {

    }

    private double distance(double lat1, double lon1, double lat2, double lon2, String unit) {
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

    private double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    private double rad2deg(double rad) {
        return (rad * 180 / Math.PI);
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

    @Override
    public String toString() {
        return "Flight:"
                + "\nAir Lines = " + airLine
                + "\nOrigin = " + origin
                + "\nDestination = " + destination
                + "\nDeparture Time = " + departureTime
                + "\nArrival Time=" + arrivalTime
                + "\nDate Of Flight = " + dateOfFligth
                + "\nFlight Number = " + fligthNumber
                + "\nAir Craft Assigned = " + airCraftAssigned
                + "\nPilot assinged = " + ((AirPlane) airCraftAssigned).getPilot();
    }

}
