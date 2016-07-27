/*
 * Pilot class storing all the info about the Pilot object
 */
package samair;

import java.io.Serializable;

/**
 *
 * @author Przemek Stepien
 */
public class Pilot implements Serializable{
    
    private String name;
    private String pilotID;
    private byte rating;

    public Pilot() {
    }

    public Pilot(String name, String pilotID, byte rating) {
        this.name = name;
        this.pilotID = pilotID;
        this.rating = rating;
    }

    /**
     * Gets pilot's name
     * 
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets pilot's name
     * 
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets pilot's ID 
     * 
     * @return the pilotID
     */
    public String getPilotID() {
        return pilotID;
    }

    /**
     * Sets pilot's ID 
     * 
     * @param pilotID the pilotID to set
     */
    public void setPilotID(String pilotID) {
        this.pilotID = pilotID;
    }

    /**
     * Gets pilot's rating
     * 
     * @return the rating
     */
    public byte getRating() {
        return rating;
    }

    /**
     * Sets pilot's rating
     * 
     * @param rating the rating to set
     */
    public void setRating(byte rating) {
        this.rating = rating;
    }

    /**
     * Displays all the details about the pilot to the console
     * 
     * @return String representation of Pilot class
     */
    @Override
    public String toString() {
        return "Pilot:" 
                + "\nName = " + name 
                + "\nPilot ID = " + pilotID 
                + "\nRating = " + rating + "\n";
    }
}
