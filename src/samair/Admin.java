/*
 *
 */
package samair;

/**
 *
 * @author Przemek Stepien
 */
public class Admin extends User {

    public Admin() {
    }

    public Admin(String userName, String password) {
        setUserName(userName);
        setPassword(password);
    }

    // Creates a new Fligth object
    public Fligth createFlight() {
        Fligth flight = null;
        return flight;
    }

    // Schedules a flight that's passed in as argument
    public Fligth scheduleFlight(Fligth flight) {
        return flight;
    }

    /*
     * Adds a scheduled flight to the flight data base. Both flight and
     * database are passed as an argument
     */
    public void addFlight(Fligth flight, FligthDataBase fdb) {

    }

    /*
     * Updates a scheduled flight
     */
    public void updateFlight(Fligth flight, FligthDataBase fdb) {

    }

    @Override
    public String toString() {
        return "Admin:" 
                + "\nUser Name = " + getUserName() 
                + "\nPassword = " + getPassword();
    }
}
