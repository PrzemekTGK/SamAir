/*
 *
 */
package samair;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
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
    private Menus menus = new Menus();

    /*
     Starting point of the program. Allows for admin/customer 
     registration and login. Admin needs to know the passweord
     to be able to do either one.
     */
    public void startProgram(UserDataBase users) {

        // Scanner object for user input
        Scanner scanText = new Scanner(System.in);
        // Main program loop condition
        boolean userContinue = true;

        // Main program loop
        Label:
        while (userContinue) {

            // Displays main menu and asks user for input saved in an int.
            int mainMenuChoice = menus.displayMainMenu();
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

                // Admins password accepeted and admin can register/login
                // Admin is asked to choose an option that's saved in an int
                int loginMenuChoice = menus.displayLoginMenu("Admin");

                // Admin chose to register
                if (loginMenuChoice == 1) {
                    users.getUsers().put(
                            generateUniqueKey(users.getUsers()),
                            registerAdmin(users.getUsers()));
                    // Admin chose to login
                } else {
                    admin = adminLogin(users.getUsers());
                }
                // User chose an option for customer
            } else {
                // Displays register/login menu for customer to choose.
                // Customer's choice is saved in an int.
                int loginMenuChoice = menus.displayLoginMenu("Customer");

                // Customer chose to register
                if (loginMenuChoice == 1) {
                    users.getUsers().put(generateUniqueKey(
                            users.getUsers()),
                            registerCustomer(users.getUsers()));
                    // Customer chose to login
                } else {
                    customer = customerLogin(users.getUsers());
                }
            }
        }
    }

    // Admin registration
    public Admin registerAdmin(HashMap<String, User> userDataBase) {

        // Declared Admin object reference variable
        Admin admin = null;
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
        admin = new Admin(userName, password);
        // Scanner object nullified for garbage collection
        scanText = null;
        // Admin object retruned
        return admin;
    }

    // Customer registration
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

    // Admin login
    public Admin adminLogin(HashMap<String, User> userDataBase) {

        // Declared Admin object reference variable    
        Admin admin = null;
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

            // Pseudo index for passed in hashmap to determine the end of that map
            int index = 0;
            // Iterator for passed in hashmap
            Iterator adminIterator = userDataBase.entrySet().iterator();

            // Check passed in hashmap for Admin object with same
            // user name and password as given by the user
            while (adminIterator.hasNext()) {

                // Single current entry of passed in hashmap
                Map.Entry entry = (Map.Entry) adminIterator.next();

                // Check if entry is an Admin
                if (entry.getValue() instanceof Admin) {

                    // Checks if Admin entry's user name 
                    // and password are same as given by the use
                    if (((Admin) entry.getValue()).getUserName().equals(userName)
                            && ((Admin) entry.getValue()).getPassword().equals(password)) {

                        // Admin user login accepted. Admin object reference
                        // variable declared before is initialized to entry's
                        // value casted as Admin
                        admin = (Admin) entry.getValue();

                        // Check condition met and check is over
                        invalidUser = false;
                        break;
                    }
                }

                // Current entry's user name or password didn't match user name
                // and password given by Admin user and pseudo index is incremented
                index++;

                // Pseudo index reached end of the map without finding matching
                // admin to log in. Admin user is given an info message.
                if (index == userDataBase.size()) {
                    System.out.println("There is no users with given user name");
                }
            }
        } while (invalidUser);

        // Returns succesfully logged in Admin user
        return admin;
    }

    // Customer to login
    public Customer customerLogin(HashMap<String, User> userDataBase) {

        // Declared Customer object reference variable
        Customer customer = null;
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

            // Pseudo index for passed in hashmap to determine the end of that map 
            int index = 0;
            // Iterator for passed in hashmap
            Iterator adminIterator = userDataBase.entrySet().iterator();

            // Check passed in hashmap for Customer object with same
            // user name and password as given by the user
            while (adminIterator.hasNext()) {

                // Single current entry of passed in hashmap
                Map.Entry entry = (Map.Entry) adminIterator.next();
                if (entry.getValue() instanceof Customer) {
                    if (((Customer) entry.getValue()).getUserName().equals(userName)
                            && ((Customer) entry.getValue()).getPassword().equals(password)) {
                        admin = (Customer) entry.getValue();
                        invalidUser = false;
                        break;
                    }
                }

                // Current entry's user name or password didn't match user name
                // and password given by Customer user and pseudo index is incremented
                index++;

                // Pseudo index reached end of the map without finding matching
                // admin to log in. Customer user is given an info message.
                if (index == userDataBase.size()) {
                    System.out.println("There is no users with given user name");
                }
            }
        } while (invalidUser);

        // Returns succesfully logged in Customer user
        return customer;
    }

    // Generates and verifies a unique 
    public String generateUniqueKey(HashMap userDataBase) {

        // String with key generated by random String method
        String key = randomString();
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
                        key = randomString();
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

    // Returns key as String of 6 random characters
    public String randomString() {
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
}
