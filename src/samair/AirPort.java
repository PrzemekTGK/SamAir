/*
 * AirPort object class storing all the info about the airports for the flight
 */
package samair;

import java.io.Serializable;

/**
 *
 * @author Przemek Stepien
 */
public class AirPort implements Serializable{
    
    private String name;
    private String city;
    private String country;
    private float longitude;
    private float latitude;

    public AirPort(String name, String city, String country, float longitude, float latitude) {
        this.name = name;
        this.city = city;
        this.country = country;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    /**
     * Gets the name of the AirPort
     * 
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the city of the AirPort
     * 
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * Gets the country of the AirPort
     * 
     * @return the country
     */
    public String getCountry() {
        return country;
    }

    /**
     * Gets the longitude of the AirPort
     * 
     * @return the longitude
     */
    public float getLongitude() {
        return longitude;
    }

    /**
     * Gets the latitude of the AirPort
     * 
     * @return the latitude
     */
    public float getLatitude() {
        return latitude;
    }

    /**
     * Displays all the details about the AirPort to the console
     * 
     * @return String representation of AirPort class
     */
    @Override
    public String toString() {
        return "AirPort:"
                + "\nName = " + name 
                + "\nCity = " + city 
                + "\nCountry = " + country 
                + "\nLongitude = " + longitude 
                + "\nLatitude = " + latitude + "\n";
    }
}
