/*
 * Abstract Parent class for User like classes 
 */
package samair;

import java.io.Serializable;

/**
 *
 * @author Przemek Stepien
 */
public abstract class User implements Serializable{

    private String userName;
    private String password;
    /**
     * Displays all flights from pseudo database of flights
     * @param fdb is the date base of flights to be displayed
     */
    void viewFlights(FlightDataBase fdb){
        fdb.getScheduledFlights().forEach((k,v) -> System.out.println(v));
    };
    
    /**
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName the userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }
    
    
    @Override
    public String toString() {
        return "user:" 
                + "\nUser Name = " + userName
                + "\nPassword = " + password;
    }
}
