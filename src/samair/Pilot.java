/*
 *
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
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the pilotID
     */
    public String getPilotID() {
        return pilotID;
    }

    /**
     * @param pilotID the pilotID to set
     */
    public void setPilotID(String pilotID) {
        this.pilotID = pilotID;
    }

    /**
     * @return the rating
     */
    public byte getRating() {
        return rating;
    }

    /**
     * @param rating the rating to set
     */
    public void setRating(byte rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Pilot:" 
                + "\nName = " + name 
                + "\nPilot ID = " + pilotID 
                + "\nRating = " + rating + "\n";
    }
}
