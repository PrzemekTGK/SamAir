/*
 * Customer class used to store all the details about the user and 
 * holds methods required for that class as well
 */
package samair;

/**
 *
 * @author Przemek Stepien
 */
public class Customer extends User {


    public Customer() {
    }

    public Customer(String userName, String password) {
        setUserName(userName);
        setPassword(password);
    }
    
    
    @Override
    public String toString() {
        return "Customer:" 
                + "\nUser Name = " + getUserName() 
                + "\nPassword = " + getPassword();
    }
}
