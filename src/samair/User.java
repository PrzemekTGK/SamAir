/*
 *
 */
package samair;

/**
 *
 * @author Przemek Stepien
 */
public abstract class User {

    private String userName;
    private String password;
    
    void viewFlights(){
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
