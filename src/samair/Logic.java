/*
 *
 */
package samair;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Przemek Stepien
 */
public class Logic {

    // Declared Customer object reference variable
    private User customer = null;
    // Declared Admin object reference variable
    private User admin = null;
    // Menus object for acces to various menus
    private final Menus MENUS = new Menus();
    // Initializer object declared 
    private Initializer init = null;

    /**
     * Initializes Initializer object to file or new instance
     *
     * @return Initializer object initialized with a file or a new instance of
     * an object
     */
    public Initializer init() {
        // Initializer object loaded in from the file or created and saved to the file
        try (FileInputStream fileIn = new FileInputStream("Data.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);){            
            init = (Initializer) in.readObject();
            System.out.println("Data.ser was loaded");
        } catch (IOException i) {
            init = new Initializer();
            init.initialize(this);
            serialize(init);
        } catch (ClassNotFoundException c) {
            System.out.println("Initializer class not found");
        }
        return init;
    }

    /**
     * Starting point of the program. Allows for admin/customer registration and
     * login. Admin needs to know the passweord to be able to do either one.
     *
     * @param users is used to get access to pseudo data base of users
     */
    public void startProgram(UserDataBase users) {
        // Scanner object for User input
        Scanner scanText = new Scanner(System.in);

        // Main program loop condition
        boolean userContinue = true;
        // Main program loop
        Label:
        while (userContinue) {
            // Displays main menu and asks user for input saved in an int.
            int mainMenuChoice = MENUS.displayMainMenu();
            // User chose an option for admin
            if (mainMenuChoice == 1) {
                // Admin's password check condition
                boolean invalidAdminPassword = true;
                // Admins password check
                do {
                    // User asked for amins password
                    System.out.println("Admin Password: ");
                    String adminPassword = scanText.nextLine();
                    // Admin's password accepted
                    if (adminPassword.equals("pass")) {
                        invalidAdminPassword = false;
                        // User resigned from admin registration/login
                    } else if (adminPassword.equals("back")) {
                        continue Label;
                        // Admin's password declined
                    } else {
                        System.out.println("Invalid admin password! Try Again"
                                + "or type in 'back' to go back.");
                    }
                } while (invalidAdminPassword);
                // Admin's password accepeted and admin can register/login
                // Admin is asked to choose an option that's saved in an int
                int loginMenuChoice = MENUS.displayLoginMenu("Admin");
                // Admin chose to register
                if (loginMenuChoice == 1) {
                    users.getUsers().put(
                            verifyUniqueKey(users.getUsers()),
                            registerAdmin(users.getUsers()));
                    // Admin chose to login
                } else {
                    admin = adminLogin(users.getUsers());
                    boolean adminContinue = true;
                    do {
                        int userChoice = MENUS.displayAdminMenu(init.getFlightsDataBase());
                        switch (userChoice) {
                            case 1:
                                ((Admin) init.getAdmin()).
                                        addFlight(((Admin) init.getAdmin()).
                                                createFlight(init.getAirPortsDataBase(),
                                                        init.getFlightsDataBase(),
                                                        init.getAirCraftsDataBase(),
                                                        init.getPilotsDataBase(),
                                                        init.getAirlinesDataBase()),
                                                init.getFlightsDataBase());
                                break;
                            case 2:
                                ((Admin) init.getAdmin()).updateFlight(init.getFlightsDataBase());
                                break;
                            case 3:
                                // TODO: Functionality to remove a flight
                                break;
                            case 4:
                                admin.viewFlights(init.getFlightsDataBase());
                                break;
                            case 5:
                                admin = null;
                                adminContinue = false;
                                break;
                        }
                    } while (adminContinue);

                }
                // User chose an option for customer
            } else {
                // Displays register/login menu for customer to choose.
                // Customer's choice is saved in an int.
                int loginMenuChoice = MENUS.displayLoginMenu("Customer");
                // Customer chose to register
                if (loginMenuChoice == 1) {
                    users.getUsers().put(verifyUniqueKey(
                            users.getUsers()),
                            registerCustomer(users.getUsers()));
                    // Customer chose to login
                } else {
                    customer = customerLogin(users.getUsers());
                    boolean customerContinue = true;
                    do {
                        int userChoice = MENUS.displayCustomerMenu(init.getFlightsDataBase());
                        switch (userChoice) {
                            case 1:
                                // TODO: Functionality to book a specific flight
                                break;
                            case 2:
                                // TODO: Functionality to search and display flights by destination
                                break;
                            case 3:
                                customer.viewFlights(init.getFlightsDataBase());
                                break;
                            case 4:
                                customer = null;
                                customerContinue = false;
                                break;
                        }
                    } while (customerContinue);
                }
            }
        }
    }

    /**
     * Method for admin registration
     *
     * @param userDataBase is used to verify that there is no admins with same
     * user name as new admin user to be registered
     * @return new Admin object to be added to userDataBase
     */
    public Admin registerAdmin(HashMap<String, User> userDataBase) {

        // Declared Admin object reference variable
        Admin localAdmin = null;
        // Name check condition
        boolean duplicatedName = true;
        // Scanner object for user input
        Scanner scanText = new Scanner(System.in);

        // Declared empty strings for user's input
        String userName = null;
        String password = null;

        // Check if there is no Admin object with same 
        // user name as given by user to register
        Label:
        do {
            // User asked to type in name and password for registration
            System.out.println("Please type in your user name: ");
            userName = scanText.nextLine();
            System.out.println("Please type in your password");
            password = scanText.nextLine();

            // Pseudo index of passed in hashmap to determine the end of the loop
            int index = 0;
            // Iterator of passed in hashmap
            Iterator usersIterator = userDataBase.entrySet().iterator();

            // Check passed in hashmap for Admin object 
            // with same name as given by the  user
            while (usersIterator.hasNext()) {

                // Single current entry of passed in hashmap
                Map.Entry entry = (Map.Entry) usersIterator.next();

                // Check if entry is an Admin
                if (entry.getValue() instanceof Admin) {

                    // Admin with same name as given by user already exists
                    // Admin user is given an info message and check is done again
                    if (userName.equals(((Admin) entry.getValue()).getUserName())) {
                        System.out.println("User with given name already exists. "
                                + "\nPlease type in a different name");
                        continue Label;
                    }
                }

                // Current entry's user name didn't match user name
                // given by Admin user and pseudo index is incremented
                index++;

                // End of the loop and no admins with same name found
                if (index == userDataBase.size()) {
                    duplicatedName = false;
                }
            }
        } while (duplicatedName);

        // New Admin object is created with user name and password given by the user
        localAdmin = new Admin(userName, password);
        // Scanner object nullified for garbage collection       
        scanText = null;
        // Admin object retruned
        return localAdmin;
    }

    /**
     * Method for customer registration
     *
     * @param userDataBase is used to verify that there is no customers with
     * same user name as new customer user to be registered
     * @return new Customer object to be added to userDataBase
     */
    public Customer registerCustomer(HashMap<String, User> userDataBase) {

        // Declared Customer object reference variable
        Customer customer = null;
        // Name check condition
        boolean duplicatedName = true;
        // Scanner object for user input
        Scanner scanText = new Scanner(System.in);

        // Declared empty strings for user's input
        String userName = null;
        String password = null;

        // Check if there is no customers with
        // same user name as given by the user to register
        Label:
        do {
            // User asked to type in name and password for registration
            System.out.println("Please type in your user name: ");
            userName = scanText.nextLine();
            System.out.println("Please type in your password");
            password = scanText.nextLine();

            // Pseudo index of passed in hashmap to determine the end of that map
            int index = 0;
            // Iterator of passed in hashmap
            Iterator usersIterator = userDataBase.entrySet().iterator();

            // Check passed in hashmap for Customer object with 
            // same user name as given by the user
            while (usersIterator.hasNext()) {

                // Single current entry of in passed hashmap
                Map.Entry entry = (Map.Entry) usersIterator.next();

                // Check if entry is a Customer
                if (entry.getValue() instanceof Customer) {

                    // Customer with same name as given by user already exists
                    // Customer user is given an info message and check is done again
                    if (userName.equals(((Customer) entry.getValue()).getUserName())) {
                        System.out.println("User with given name already exists. "
                                + "Please type in a different name");
                        userName = scanText.nextLine();
                        continue Label;
                    }
                }

                // Current entry's user name didn't match user name
                // given by Customer user and pseudo index is incremented
                index++;

                // End of the loop and no admins with same name found
                if (index == userDataBase.size()) {
                    duplicatedName = false;
                }
            }
        } while (duplicatedName);

        // New Customer object is created with userName and password given by the user
        customer = new Customer(userName, password);
        // Scanner object nullified for garbage collection
        scanText = null;
        // Customer object retruned
        return customer;
    }

    /**
     * Method for admin login
     *
     * @param userDataBase is used to verify that there is an admin with same
     * user name and password as given by user
     * @return a Admin object found in data base passed as argument
     */
    public Admin adminLogin(HashMap<String, User> userDataBase) {

        // Declared Admin object reference variable    
        Admin localAdmin = null;
        // Valid admin check condition
        boolean invalidUser = true;
        // Scanner object declared for user input
        Scanner scanText = new Scanner(System.in);

        // Valid admin check for Admin object with user name
        // and password same given as given by the user
        do {

            // User asked for user name and password for login
            System.out.println("Admin user name:");
            String userName = scanText.nextLine();
            System.out.println("Password:");
            String password = scanText.nextLine();

            // Check passed in hashmap for Admin object with same
            // user name and password as given by the user            
            for (Map.Entry entry : userDataBase.entrySet()) {
                // Check if entry is an Admin
                if (entry.getValue() instanceof Admin) {                    
                    // Checks if Admin entry's user name 
                    // and password are same as given by the user
                    if (((Admin) entry.getValue()).getUserName().equals(userName)
                            && ((Admin) entry.getValue()).getPassword().equals(password)) {
                        // Admin user login accepted. Admin object reference
                        // variable declared before it's initialized to entry's
                        // value casted as Admin
                        localAdmin = (Admin) entry.getValue();
                        // Check condition met and check is over
                        invalidUser = false;
                        break;
                    }
                }
            }
            if (invalidUser) {
                System.out.println("\nThere is no users with given user name."
                        + "\nPlease try again.\n");
            } else {
                System.out.println("User: " + localAdmin + " found!");
            }
        } while (invalidUser);
        scanText = null;
        // Returns succesfully logged in Admin user
        return localAdmin;
    }

    /**
     * Method for customer login
     *
     * @param userDataBase is used to verify that there is a customer with same
     * user name and password as given by user
     * @return a Customer object found in data base passed as argument
     */
    public Customer customerLogin(HashMap<String, User> userDataBase) {

        // Declared Customer object reference variable
        Customer localCustomer = null;
        // Valid customer check condition
        boolean invalidUser = true;
        // Scanner object for user input
        Scanner scanText = new Scanner(System.in);

        // Valid Customer check
        do {
            // User asked for user name and password for login
            System.out.println("Customer user name:");
            String userName = scanText.nextLine();
            System.out.println("Password:");
            String password = scanText.nextLine();
            
            // Check passed in hashmap for Customer object with same
            // user name and password as given by the user
            for (Map.Entry entry : userDataBase.entrySet()) {
                // Check if entry is an Customer
                if (entry.getValue() instanceof Customer) {
                    // Checks if Admin entry's user name 
                    // and password are same as given by the user
                    if (((Customer) entry.getValue()).getUserName().equals(userName)
                            && ((Customer) entry.getValue()).getPassword().equals(password)) {
                        // Customer user login accepted. Customer object reference
                        // variable declared before it's initialized to entry's
                        // value casted as Customer
                        localCustomer = (Customer) entry.getValue();                        
                        // Check condition met and check is over
                        invalidUser = false;
                        break;
                    }
                }
            }
            if (invalidUser) {
                System.out.println("\nThere is no users with given user name."
                        + "\nPlease try again.\n");
            } else {
                System.out.println("User: " + localCustomer + " found!");
            }
                
        } while (invalidUser);
        scanText = null;
        // Returns succesfully logged in Customer user
        return localCustomer;
    }

    /**
     * Method verifying a unique key for userDataBase
     *
     * @param userDataBase is used for verification of uniqueness of generated
     * key
     * @return String with a unique key for User object in userDataBase
     */
    public String verifyUniqueKey(HashMap userDataBase) {

        // String with key generated by random String method
        String key = generateKey();
        // Key check condition
        boolean duplicatedKey = true;

        // Check if passed in hashmap is empty
        // Passed in hashmap is empty, 
        // key is returned as it's first entry to the map
        if (userDataBase.isEmpty()) {
            return key;
            // Passed in hashmap is not empty, check for duplicated key exectus
        } else {
            // Check passed in hashmap for entries with same key as generated before
            Label:
            do {
                // Pseudo index to determine the end of passed in hashmap
                int index = 0;
                // Iterator for passed in hashmap
                Iterator usersIterator = userDataBase.entrySet().iterator();

                // Check fot duplicated key
                while (usersIterator.hasNext()) {

                    // Single current entry of the map
                    Map.Entry entry = (Map.Entry) usersIterator.next();

                    // Entry with duplicated key found
                    // New key is genereted and check is done again
                    if (key.equals(entry.getKey())) {
                        key = generateKey();
                        continue Label;
                    }

                    // Current entry's key didn't match one generated before
                    // Pseudo index is incremented
                    index++;

                    // Pseudo index reached end of the map withou finding entries
                    // with duplicated key. Duplicted key check condition is met
                    if (index == userDataBase.size()) {
                        duplicatedKey = false;
                    }
                }
            } while (duplicatedKey);

            // New unique key is returned
            return key;
        }
    }

    /**
     * Generates a random key and saves it as String
     *
     * @return String with generated key
     */
    public String generateKey() {
        // char declared for random char value
        char randomChar;
        // Empty String is declared for key generation
        String key = "";
        // Random object for random char generation;
        Random randomGen = new Random(System.nanoTime());

        // Generation of 6 random characters key 
        for (int i = 0; i < 6; i++) {
            randomChar = (char) (randomGen.nextInt(94) + 33);
            key += randomChar;
        }
        // New key is returned
        return key;
    }

    /**
     * Serializes any Serializable objects passed as argument to a file Data.ser
     *
     * @param data is used to define the object to be serialized
     */
    public static void serialize(Serializable data) {
        try (FileOutputStream fileOut = new FileOutputStream("Data.ser");
                ObjectOutputStream out = new ObjectOutputStream(fileOut);) {
            out.writeObject(data);
            out.flush();
            fileOut.flush();
            System.out.println("Data saved in Data.ser");
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
