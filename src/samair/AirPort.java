/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * @return the country
     */
    public String getCountry() {
        return country;
    }

    /**
     * @return the longitude
     */
    public float getLongitude() {
        return longitude;
    }

    /**
     * @return the latitude
     */
    public float getLatitude() {
        return latitude;
    }

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
