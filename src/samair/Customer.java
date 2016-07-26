/*
 * Customer class used to store all the details about the user and 
 * holds methods required for that class as well
 */
package samair;

import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author Przemek Stepien
 */
public class Customer extends User {
    
    private String firstName;
    private String lastName;
    private ArrayList<BookedFlight> cutomersFlights = new ArrayList<BookedFlight>();

    public Customer() {
    }

    public Customer(String userName, String password) {
        setUserName(userName);
        setPassword(password);
    }
    
    public void displayFlightsByDestination(JourneyDataBase fdb){
        String country = selectCountry(fdb);
        String city  = selectCity(fdb, country); 
        for (Map.Entry entry : fdb.getScheduledFlights().entrySet()) {
            Flight tempFlight = (Flight) entry.getValue();
            if (tempFlight.getDestination().getCountry().replaceAll("\"", "").equalsIgnoreCase(country)
                    && tempFlight.getDestination().getCity().replaceAll("\"", "").equalsIgnoreCase(city)) {
                System.out.println(tempFlight);
            }
        }
    }    

    public Flight selectFlight(JourneyDataBase fdb) {
        String country = selectCountry(fdb);
        String city = selectCity(fdb, country);
        Flight flight = null;
        System.out.println("=========================================================");

        for (Map.Entry entry : fdb.getScheduledFlights().entrySet()) {
            Flight tempFlight = (Flight) entry.getValue();
            if (tempFlight.getDestination().getCountry().replaceAll("\"", "").equalsIgnoreCase(country)
                    && tempFlight.getDestination().getCity().replaceAll("\"", "").equalsIgnoreCase(city)) {
                flight = tempFlight;
                System.out.println(tempFlight);
            }
        }
        return flight;
    }
    
    public void bookFlight(Flight flight, BookedFlightsDataBase bfdb){     
        boolean invalidFirstName = true;
        boolean invalidLastName = true;
        BookedFlight bookedFlight = new BookedFlight();
        Scanner scanText = new Scanner(System.in);
        
        do {            
            System.out.println("Please type in your First Name: ");
            firstName = scanText.nextLine().toUpperCase();
            if (firstName.isEmpty()) {
                System.out.println("Invalid input - No input!");
            } else {
                invalidFirstName = false;
            }            
        } while (invalidFirstName);
        firstName = getFirstName().charAt(0) + getFirstName().substring(1).toLowerCase();        
        do {            
            System.out.println("Please type in your Last Name: ");
            lastName = scanText.nextLine().toUpperCase();
            if (lastName.isEmpty()) {
                System.out.println("Invalid input - No input!");
            } else {
                invalidLastName = false;
            }            
        } while (invalidLastName);
        lastName = getLastName().charAt(0) + getLastName().substring(1).toLowerCase();  
        
        bookedFlight.setCustomerFirstName(getFirstName());
        bookedFlight.setCustomerLastName(getLastName());
        bookedFlight.setAirCraftAssigned(flight.getAirCraftAssigned());
        bookedFlight.setAirLines(flight.getAirLine());
        bookedFlight.setDateOfFlight(flight.getDateOfFlight());
        bookedFlight.setArrivalTime(flight.getArrivalTime());
        bookedFlight.setDepartureTime(flight.getDepartureTime());
        bookedFlight.setOrigin(flight.getOrigin());
        bookedFlight.setDestination(flight.getDestination());
        bookedFlight.setFlightDuration(flight.getFlightDuration());
        
        String key = bookedFlight.getFlightNumber();
        
        if (bfdb.getBookedFlights().containsKey(key)) {
            if (bfdb.getBookedFlights().get(key).size() <
                    ((AirPlane)flight.getAirCraftAssigned()).getSeatsCapacity()) {
                System.out.println("New Flight added");
                bfdb.getBookedFlights().get(key).add(bookedFlight);
                cutomersFlights.add(bookedFlight);                
            } else {
                bookedFlight = null;
                System.out.println("No more seats available on this flight!");
            }
        } else {
            System.out.println("New First Flight added");
            ArrayList<BookedFlight> bookedFlightsArrayList = new ArrayList<BookedFlight>();
            bookedFlightsArrayList.add(bookedFlight);
            cutomersFlights.add(bookedFlight);
            bfdb.getBookedFlights().put(key, bookedFlightsArrayList);
        }
    }
    
    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @return the cutomersFlights
     */
    public ArrayList<BookedFlight> getCutomersFlights() {
        return cutomersFlights;
    }

    @Override
    public String toString() {
        return "Customer:"
                + "\nUser Name = " + getUserName();
    }

}
