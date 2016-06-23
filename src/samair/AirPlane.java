/*
 *
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

    public AirPlane() {
    }   
    
    public AirPlane(String make, String model, Pilot pilot, short seatsCapacity) {
        this.make = make;
        this.model = model;
        this.pilot = pilot;
        this.seatsCapacity = seatsCapacity;
    }
    
    public void assignPilot(Pilot pilot){
        
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
     * @return the seatsCapacity
     */
    public short getSeatsCapacity() {
        return seatsCapacity;
    }

    @Override
    public String toString() {
        return "AirPlane:" 
                + "\nMake = " + make 
                + "\nModel = " + model 
                + "\nPilot = " + pilot 
                + "\nSeats Capacity = " + seatsCapacity;
    }
    
    
    
}
