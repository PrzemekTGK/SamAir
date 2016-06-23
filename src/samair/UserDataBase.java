/*
 * 
 */
package samair;

import java.util.HashMap;

/**
 *
 * @author Przemek Stepien
 */
public class UserDataBase {
    private HashMap<String, User> users;

    public UserDataBase() {
        this.users = new HashMap<String, User>();
    }   
    

    /**
     * @return the users
     */
    public HashMap<String, User> getUsers() {
        return users;
    }
}
