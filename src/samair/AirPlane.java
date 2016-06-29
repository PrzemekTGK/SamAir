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
     * @return the make
     */
    public String getMake() {
        return make;
    }

    /**
     * @return the model
     */
    public String getModel() {
        return model;
    }

    /**
     * @return the pilot
     */
    public Pilot getPilot() {
        return pilot;
    }
    
    /**
     * @param pilot the pilot to set
     */
    public void setPilot(Pilot pilot) {
        this.pilot = pilot;
    }

    /**
     * @return the seatsCapacity
     */
    public short getSeatsCapacity() {
        return seatsCapacity;
    }

    /**
     * @return the active
     */
    public boolean isActive() {
        return active;
    }

    /**
     * @param active the active to set
     */
    public void setActive(boolean active) {
        this.active = active;
    }
    
    @Override
    public String toString() {
        return "AirPlane:" 
                + "\nMake = " + make 
                + "\nModel = " + model 
                + "\nPilot = " + pilot.getName()
                + "\nSeats Capacity = " + seatsCapacity + "\n";
    }    

}
