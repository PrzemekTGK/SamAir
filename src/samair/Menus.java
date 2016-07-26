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
                + "\n3. Exit"
                + "\n: ");
        do {

            switch (scanText.nextLine()) {
                case "1":
                    userChoice = 1;
                    invalidInput = false;
                    break;
                case "2":
                    userChoice = 2;
                    invalidInput = false;
                    break;
                case "3":
                    userChoice = 3;
                    invalidInput = false;
                    break;                    
                default:
                    System.out.println("Invalid Input");
                    System.out.print(": ");
            }
        } while (invalidInput);
        scanText = null;
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
                    userChoice = 1;
                    invalidInput = false;
                    break;
                case "2":
                    userChoice = 2;
                    invalidInput = false;
                    break;
                default:
                    System.out.println("Invalid Input");
                    System.out.print(": ");
            }
        } while (invalidInput);
        scanText = null;
        return userChoice;
    }

    // Displays menu for logged in customer
    public int displayCustomerMenu(JourneyDataBase fdb) {
        Scanner scanText = new Scanner(System.in);
        boolean invalidChoice = true;
        int userChoice = 0;
        System.out.print("Please select:"
                + "\n1. Display All Flights"
                + "\n2. Display Flights by destination"
                + "\n3. Book a Flight"
                + "\n4. Display Booked Flights"
                + "\n5. Log Out"
                + "\n: ");

        try {
            do {
                userChoice = Integer.parseInt(scanText.nextLine());
                if (userChoice > 0 && userChoice < 6) {
                    invalidChoice = false;
                } else {
                    System.out.println("Invalid input. Has to be a number between 1 and 5");
                }
            } while (invalidChoice);
        } catch (NumberFormatException nfe) {
            System.out.println("Invalid input. Has to be a number between 1 and 5");
        }
        scanText = null;
        return userChoice;
    }

    // Displays menu for logged in admin
    public int displayAdminMenu(JourneyDataBase fdb) {
        Scanner scanText = new Scanner(System.in);
        boolean invalidChoice = true;
        int userChoice = 0;
        System.out.print("Please select:"
                + "\n1. Create Flight"
                + "\n2. Update Flight"
                + "\n3. Delete Flight "
                + "\n4. Display All Flights"
                + "\n5. Log Out"
                + "\n: ");

        try {
            do {
                userChoice = Integer.parseInt(scanText.nextLine());
                if (userChoice > 0 && userChoice < 6) {
                    invalidChoice = false;
                } else {
                    System.out.println("Invalid input. Has to be a number between 1 and 5");
                }
            } while (invalidChoice);
        } catch (NumberFormatException nfe) {
            System.out.println("Invalid input. Has to be a number between 1 and 5");
        }
        scanText = null;
        return userChoice;
    }
}
