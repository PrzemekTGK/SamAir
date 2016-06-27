/*
 *
 */
package samair;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

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
        Date randomDate = generateRandomDate();
        Calendar cal = Calendar.getInstance();
        cal.setTime(randomDate);
        Time departureTime = new Time(cal.getTimeInMillis());
        cal.add(Calendar.MILLISECOND, (int) fligth.getFlightDurationInMilliseconds());
        Time arrivalTime = new Time(cal.getTimeInMillis());
        fligth.scheduleFlight(randomDate, departureTime, arrivalTime);
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
            fdb.getScheduledFlights().forEach((k, v) -> {
                if (((Flight) v).getFlightNumber().equalsIgnoreCase(fligth.getFlightNumber())) {
                    System.out.println("DUPLICATED KEY!!!" + "\n" + v + "DUPLICATED KEY!!!");
                    generateFligthNumber(fligth);
                }
            });
            duplicatedKey = false;
        } while (duplicatedKey);
        fdb.getScheduledFlights().put(fligth.getFlightNumber(), fligth);
    }

    // Updates a scheduled flight
    public void updateFlight(FlightDataBase fdb) {
        Scanner scanInt = new Scanner(System.in);
        String country = selectCountry(fdb);
        String city = selectCity(fdb, country);
        Flight flight = null;
        int hour = 0;
        int minute = 0;
        System.out.println("=========================================================");
        
        for (Map.Entry entry : fdb.getScheduledFlights().entrySet()) {
            Flight tempFlight = (Flight) entry.getValue();
            if (tempFlight.getDestination().getCountry().replaceAll("\"", "").equalsIgnoreCase(country)
                    && tempFlight.getDestination().getCity().replaceAll("\"", "").equalsIgnoreCase(city)) {
                flight = tempFlight;
                System.out.println(tempFlight);
            }
        }
        
        System.out.println("Please select a new departure time ");
        boolean invalidHour = true;
        do {            
            System.out.println("Please Select the hour (0-23)");
            try {
                hour = scanInt.nextInt();
                if (hour < 0) {
                    System.out.println("Invaliv input. Input can't be less than 0!");
                } else if (hour > 23) {
                    System.out.println("Invaliv input. Input can't be higher than 23!");
                } else {
                    invalidHour = false;
                }
            } catch (NumberFormatException nfe) {
                System.out.println("Invalid input. The input has to be an integer within range 0-23");
            }            
        } while (invalidHour);
        
        boolean invalidMinute = true;
        do {            
            System.out.println("Please Select the minute (0-59)");
            try {
                minute = scanInt.nextInt();
                if (hour < 0) {
                    System.out.println("Invaliv input. Input can't be less than 0!");
                } else if (hour > 59) {
                    System.out.println("Invaliv input. Input can't be higher than 59!");
                } else {
                    invalidMinute = false;
                }
            } catch (NumberFormatException nfe) {
                System.out.println("Invalid input. The input has to be an integer within range 0-59");
            }            
        } while (invalidMinute);
        
        Calendar cal = Calendar.getInstance();
        cal.setTime(flight.getDateOfFlight());
        cal.set(Calendar.HOUR, hour);
        cal.set(Calendar.MINUTE, minute);
        Time departureTime = new Time(cal.getTimeInMillis());
        cal.add(Calendar.MILLISECOND, (int)flight.getFlightDurationInMilliseconds());
        Time arrivalTime = new Time(cal.getTimeInMillis());
        flight.setDepartureTime(departureTime);
        flight.setArrivalTime(arrivalTime);
        System.out.println(flight);
    }

    // Generate random date between today and 1 year 
    // ahead to use in scheduleFlight method
    private Date generateRandomDate() {
        Random randomGen = new Random(System.nanoTime());
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date(System.currentTimeMillis())); // today
        cal.set(Calendar.HOUR, randomGen.nextInt(24));
        cal.set(Calendar.MINUTE, randomGen.nextInt(60));
        Date dMin = cal.getTime();
        cal.add(Calendar.YEAR, 1); // today plus one year
        Date dMax = cal.getTime();

        RandomDateGenerator rnd = new RandomDateGenerator(dMin, dMax);
        Date date = rnd.generate();
        return date;
    }

    // Generates a unique flight number based of the info of the flight itself
    private Flight generateFligthNumber(Flight fligth) {
        Random randomGen = new Random(System.nanoTime());
        String A = fligth.getAirLine().substring(0, 2).toUpperCase();
        String B = fligth.getOrigin().getCity().substring(0, 3).replaceAll("\"", "").toUpperCase();
        String C = fligth.getDestination().getCity().substring(0, 3).replaceAll("\"", "").toUpperCase();

        String fligthNo = A + (randomGen.nextInt(91) + 10) + B + (randomGen.nextInt(91) + 10) + C + randomGen.nextInt(10);

        fligth.setFlightNumber(fligthNo);

        return fligth;
    }

    // Asks user to select the destination country of the flight to search for
    private String selectCountry(FlightDataBase fdb) {
        Scanner scanText = new Scanner(System.in);
        String countryChoice = null;
        boolean invalidCountry = true;
        ArrayList<String> countries = displayListOfCountries(fdb);
        Label:
        do {
            System.out.println("Please select country of destination from the list above:");
            countryChoice = scanText.nextLine();
            if (countryChoice.length() > 2) {
                for (String tempCountry : countries) {
                    if (tempCountry.equalsIgnoreCase(countryChoice)) {
                        countryChoice = tempCountry;
                        invalidCountry = false;
                        System.out.println("Chosen country: " + countryChoice);
                        break Label;
                    }
                }
            } else if (countryChoice.length() <= 2) {
                try {
                    countryChoice = countries.get(Integer.parseInt(countryChoice) - 1);
                    invalidCountry = false;
                    System.out.println("Chosen country: " + countryChoice);
                    break Label;
                } catch (NumberFormatException nfe) {
                } catch (ArrayIndexOutOfBoundsException aiobe) {
                } catch (IndexOutOfBoundsException aiobe) {
                }
            }
            System.out.println("Invalid Input. Please try again!");
        } while (invalidCountry);
        return countryChoice;
    }

    
    // Displays list of destination countries of all of the flights
    private ArrayList displayListOfCountries(FlightDataBase fdb) {
        ArrayList<String> countries = new ArrayList<String>();
        Label:
        for (Map.Entry entry : fdb.getScheduledFlights().entrySet()) {
            Flight tempFlight = (Flight) entry.getValue();
            if (countries.isEmpty()) {
                countries.add(tempFlight.getDestination().getCountry().replaceAll("\"", ""));
                continue;
            } else {
                for (String tempCountry : countries) {
                    if (tempCountry.equalsIgnoreCase(tempFlight.getDestination().getCountry().replaceAll("\"", ""))) {
                        continue Label;
                    }
                }
                countries.add(tempFlight.getDestination().getCountry().replaceAll("\"", ""));
            }
        }
        System.out.println("=========================================================");
        Collections.sort(countries);
        int index = 0;
        for (String tempCountry : countries) {
            System.out.println(++index + ". " + tempCountry);
        }
        return countries;
    }
    
    // Asks user to select the destination city of the flight to search for
    private String selectCity(FlightDataBase fdb, String country) {
        Scanner scanText = new Scanner(System.in);
        String city = null;
        ArrayList<String> cities = displayListOfCities(fdb, country);
        boolean invalidCity = true;
        Label:
        do {
            System.out.println("Please select city of destination from the list above:");
            city = scanText.nextLine();
            if (city.length() > 2) {
                for (String tempCity : cities) {
                    if (tempCity.equalsIgnoreCase(city)) {
                        city = tempCity;
                        invalidCity = false;
                        System.out.println("Chosen city: " + city);
                        break Label;
                    }
                }
            } else if (city.length() <= 2) {
                try {
                    city = cities.get(Integer.parseInt(city) - 1);
                    invalidCity = false;
                    System.out.println("Chosen city: " + city);
                    break Label;
                } catch (NumberFormatException nfe) {
                } catch (ArrayIndexOutOfBoundsException aiobe) {
                } catch (IndexOutOfBoundsException aiobe) {
                }
            }
            System.out.println("Invalid Input. Please try again!");
        } while (invalidCity);
        return city;
    }

    // Displayes cities of all flights to chosen country
    private ArrayList displayListOfCities(FlightDataBase fdb, String country) {
        ArrayList<String> cities = new ArrayList<String>();
        String tempCity = null;
        int index = 0;
        for (Map.Entry entry : fdb.getScheduledFlights().entrySet()) {
            Flight tempFlight = (Flight) entry.getValue();
            if (tempFlight.getDestination().getCountry().replaceAll("\"", "").equalsIgnoreCase(country)) {
                tempCity = tempFlight.getDestination().getCity().replaceAll("\"", "");
                if (cities.isEmpty()) {
                    cities.add(tempCity);
                } else {
                    for (String city : cities) {
                        if (city.equalsIgnoreCase(tempFlight.getDestination().getCity().replaceAll("\"", ""))) {
                            continue;
                        }
                    }
                    cities.add(tempCity);
                }
                System.out.println(++index + ". " + tempCity);
            }
            tempCity = null;
        }
        return cities;
    }

    @Override
    public String toString() {
        return "Admin:"
                + "\nUser Name = " + getUserName()
                + "\nPassword = " + getPassword();
    }
}
