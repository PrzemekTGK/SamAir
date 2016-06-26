/*
 *
 */
package samair;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.Random;

/**
 *
 * @author Przemek Stepien
 */
public class Admin extends User {

    public Admin() {
    }

    public Admin(String userName, String password) {
        setUserName(userName);
        setPassword(password);
    }

    // Creates a new Flight object
    public Flight createFlight() {
        Flight flight = null;
        return flight;
    }

    // Schedules a flight that's passed in as argument
    public Flight scheduleFlight(Flight fligth) {
        Date departureDate = generateRandomDate();
        Calendar cal = Calendar.getInstance();
        cal.setTime(departureDate);
        cal.add(Calendar.MILLISECOND, (int) fligth.getFlightDurationInMilliseconds());
        Date arrivalDate = cal.getTime();
        fligth.scheduleFlight(departureDate, arrivalDate);
        generateFligthNumber(fligth);
        return fligth;
    }

    /*
     * Adds a scheduled flight to the flight data base. Both flight and
     * database are passed as an argument
     */
    public void addFlight(Flight fligth, FlightDataBase fdb) {
        boolean duplicatedKey = true;
        do {                
            fdb.getScheduledFlights().forEach((k,v) -> {
                if(((Flight)v).getFlightNumber().equalsIgnoreCase(fligth.getFlightNumber())){
                    System.out.println("DUPLICATED KEY!!!" + "\n" + v + "DUPLICATED KEY!!!");
                    generateFligthNumber(fligth);
                }
            });
            duplicatedKey = false;
        } while (duplicatedKey);
        

        fdb.getScheduledFlights().put(fligth.getFlightNumber(), fligth);
    }

    /*
     * Updates a scheduled flight
     */
    public void updateFlight(Flight flight, FlightDataBase fdb) {

    }

    // Generate random date between today and 1 year 
    // ahead to use in scheduleFlight method
    private Date generateRandomDate() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date(System.currentTimeMillis())); // today
        Date dMin = cal.getTime();
        cal.add(Calendar.YEAR, 1); // today plus one year
        Date dMax = cal.getTime();

        RandomDateGenerator rnd = new RandomDateGenerator(dMin, dMax);
        Date date = rnd.generate();
        return date;
    }

    private Flight generateFligthNumber(Flight fligth) {
        Random randomGen = new Random(System.nanoTime());
        String A = fligth.getAirLine().substring(0, 2).toUpperCase();
        String B = fligth.getOrigin().getCity().substring(0, 3).replaceAll("\"", "").toUpperCase();
        String C = fligth.getDestination().getCity().substring(0, 3).replaceAll("\"", "").toUpperCase();

        String fligthNo =  A + (randomGen.nextInt(91) + 10) + B + (randomGen.nextInt(91)+10) + C + randomGen.nextInt(10);
        
        fligth.setFlightNumber(fligthNo);

        return fligth;
    }

    @Override
    public String toString() {
        return "Admin:"
                + "\nUser Name = " + getUserName()
                + "\nPassword = " + getPassword();
    }
}
