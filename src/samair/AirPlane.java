/*
 * AirPlane object class storing all the details about the airplane for the flight
 */
package samair;

/**
 *
 * @author Przemek Stepien
 */
public class AirPlane implements AirCraft{
    
    private String make;
    private String model;
    private Pilot pilot;
    private short seatsCapacity;
    private boolean active;

    public AirPlane() {
    }   
    
    public AirPlane(String make, String model, Pilot pilot, short seatsCapacity, boolean active) {
        this.make = make;
        this.model = model;
        this.pilot = pilot;
        this.seatsCapacity = seatsCapacity;
        this.active = active;
    }
  
    /**
     * Gets the make of the AirPlane
     * 
     * @return the make
     */
    public String getMake() {
        return make;
    }

    /**
     * Gets the make of the AirPlane
     * 
     * @return the model
     */
    public String getModel() {
        return model;
    }

    /**
     * Gets the pilot of the AirPlane
     * 
     * @return the pilot
     */
    public Pilot getPilot() {
        return pilot;
    }
    
    /**
     * Sets the Pilot for the AirPlane
     * 
     * @param pilot the pilot to set
     */
    public void setPilot(Pilot pilot) {
        this.pilot = pilot;
    }

    /**
     * Gets seats capacity of the AirPlane
     * 
     * @return the seatsCapacity
     */
    public short getSeatsCapacity() {
        return seatsCapacity;
    }

    /**
     * Checks if the AirPlane is active and assigned to the flight or not
     * 
     * @return the active
     */
    public boolean isActive() {
        return active;
    }

    /**
     * Sets the AirPlane to be active (assigned to the flight)
     * 
     * @param active the active to set
     */
    public void setActive(boolean active) {
        this.active = active;
    }
    
    /**
     * Displays the details of the AirPlane to the console
     * 
     * @return String representation of AirPlane class
     */
    @Override
    public String toString() {
        return "AirPlane:" 
                + "\nMake = " + make 
                + "\nModel = " + model 
                + "\nPilot = " + pilot.getName()
                + "\nSeats Capacity = " + seatsCapacity + "\n";
    }    

}
