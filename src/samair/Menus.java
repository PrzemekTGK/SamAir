/*
 * Manues class holds methods displaying different menus of the program
 */
package samair;

import java.util.Scanner;

/**
 *
 * @author Przemek Stepien
 */
public class Menus {

    public Menus() {
    }

    // Displays main manu of and asks user to choose one of its options
    public int displayMainMenu() {
        boolean invalidInput = true;
        int userChoice = 0;
        Scanner scanText = new Scanner(System.in);

        System.out.println("=============== Welcome to SamAir ===============");

        System.out.print("Please select:"
                + "\n1. Admin"
                + "\n2. Customer"
                + "\n: ");
        do {

            switch (scanText.nextLine()) {
                case "1":
                    System.out.println("Admin Login");
                    userChoice = 1;
                    invalidInput = false;
                    break;
                case "2":
                    System.out.println("Customer Login");
                    userChoice = 2;
                    invalidInput = false;
                    break;
                default:
                    System.out.println("Invalid Input");
                    System.out.print(": ");
            }
        } while (invalidInput);

        return userChoice;
    }

    // Displays login menu either for admin or customer which
    // is defined by the String user passed in as argument
    public int displayLoginMenu(String user) {
        boolean invalidInput = true;
        int userChoice = 0;
        Scanner scanText = new Scanner(System.in);

        System.out.print("Please select:"
                + "\n1. " + user + " Register"
                + "\n2. " + user + " Login"
                + "\n: ");
        do {

            switch (scanText.nextLine()) {
                case "1":
                    System.out.println(user + " Register");
                    userChoice = 1;
                    invalidInput = false;
                    break;
                case "2":
                    System.out.println(user + " Login");
                    userChoice = 2;
                    invalidInput = false;
                    break;
                default:
                    System.out.println("Invalid Input");
                    System.out.print(": ");
            }
        } while (invalidInput);

        return userChoice;
    }

    // Displays menu for logged in customer
    public int displayCustomerMenu(FlightDataBase fdb) {
        boolean invalidInput = true;
        Scanner scanText = new Scanner(System.in);
        int userChoice = 0;

        do {
            System.out.print("Please select:"
                    + "\n1. Book a specific Flight"
                    + "\n2. Display Flights by destination"
                    + "\n3. Display All Flights"
                    + "\n4. Log Out"
                    + "\n: ");

            switch (scanText.nextLine()) {
                case "1":
                    userChoice = 1;
                    break;
                case "2":
                    userChoice = 2;
                    break;
                case "3":
                    userChoice = 3;
                    fdb.getScheduledFlights().forEach((k, v) -> System.out.println(v));
                    break;
                case "4":
                    userChoice = 4;
                    invalidInput = false;
                    break;
                default:
                    System.out.println("Invalid Input");
                    System.out.print(": ");
            }
        } while (invalidInput);
        return userChoice;
    }

    // Displays menu for logged in admin
    public int displayAdminMenu(FlightDataBase fdb) {
        Scanner scanText = new Scanner(System.in);
        int userChoice = 0;        
            System.out.print("Please select:"
                    + "\n1. Create Fligth"
                    + "\n2. Update Flight"
                    + "\n3. Display All Flights"
                    + "\n4. Log Out"
                    + "\n: ");

            switch (scanText.nextLine()) {
                case "1":
                    userChoice = 1;
                    break;
                case "2":
                    userChoice = 2;
                    break;
                case "3":
                    userChoice = 3;                   
                    break;
                case "4":
                    userChoice = 4;
                    break;
                default:
                    System.out.println("Invalid Input");
                    System.out.print(": ");
            }
        return userChoice;
    }
}
