/*
 * Pseudo database class storing all User objects
 */
package samair;

import java.io.Serializable;
import java.util.HashMap;

/**
 *
 * @author Przemek Stepien
 */
public class UserDataBase implements Serializable{
    private HashMap<String, User> users;

    public UserDataBase() {
        this.users = new HashMap<String, User>();
    }   
    

    /**
     * Gets the HashMap object users that's storing all the Users
     * 
     * @return the users
     */
    public HashMap<String, User> getUsers() {
        return users;
    }
}
