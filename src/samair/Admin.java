/*
 *
 */
package samair;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.InputMismatchException;
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
    public void createFlight(AirPortDataBase adb, FlightDataBase fdb, AirCraftDataBase acdb,
            PilotDataBase pdb) {
        Scanner scanText = new Scanner(System.in);
        Scanner scanInt = new Scanner(System.in);
        AirPort originAirPort = null;
        AirPort destinationAirPort = null;
        String airLines = null;
        String flightDuration = null;
        double flightDurationFloat = 0;
        int year = 0;
        int month = 0;
        int day = 0;
        int hour = 0;
        int minute = 0;
        boolean invalidYear = true;
        boolean invalidMonth = true;
        boolean invalidDay = true;
        boolean invalidHour = true;
        boolean invalidMinute = true;
        boolean invalidAirLines = true;
        Date currentDate = new Date(System.currentTimeMillis());
        Calendar calendar = new GregorianCalendar();
        Calendar currentCalendar = Calendar.getInstance();
        currentCalendar.setTime(currentDate);
        
        fdb.getAirlines().forEach(airlines -> System.out.println((fdb.getAirlines().indexOf(airlines) + 1) + ". " + airlines));
        do {
            int index = 0;
            System.out.println("Please select the airlins for the flight!");
            try {
                index = scanInt.nextInt() - 1;
                if (index < 0) {
                    System.out.println("Invalid input. Input can't be cmaller than 0!");                    
                } else if (index >= fdb.getAirlines().size()) {
                    System.out.println("Invalid input. Input can't be higher than " 
                            + fdb.getAirlines().size() + "!");                    
                } else {
                    airLines = fdb.getAirlines().get(index);
                    System.out.println(airLines);
                    invalidAirLines = false;
                }
            } catch (InputMismatchException ime) {
                scanInt.next();
                System.out.println("Invalid input. Please try again!");
            }            
        } while (invalidAirLines);

        for (Map.Entry entry : adb.getAirPorts().entrySet()) {
            AirPort tempAirport = (AirPort) entry.getValue();
            if (tempAirport.getCountry().replaceAll("\"", "").equalsIgnoreCase("ireland")
                    && tempAirport.getCity().replaceAll("\"", "").equalsIgnoreCase("dublin")) {
                originAirPort = tempAirport;
                break;
            }
        }
        
        String destinationCountry = selectCountry(adb);
        String destinationCity = selectCity(adb, destinationCountry);
        for (Map.Entry entry : adb.getAirPorts().entrySet()) {
            AirPort tempAirport = (AirPort) entry.getValue();
            if (tempAirport.getCountry().replaceAll("\"", "").equalsIgnoreCase(destinationCountry)
                    && tempAirport.getCity().replaceAll("\"", "").equalsIgnoreCase(destinationCity)) {
                destinationAirPort = tempAirport;
                break;
            }
        }
        
        
        flightDurationFloat = fdb.calculateFligthDurationInDecimal(fdb.calculateDistance(
                originAirPort.getLatitude(), originAirPort.getLongitude(),
                destinationAirPort.getLatitude(), destinationAirPort.getLongitude(), "KM"));
        flightDuration = fdb.convertDecimalToHours(flightDurationFloat);        

        do {
            System.out.println("Please select the year of flight\n1. 2016\n2. 2017");
            switch (scanText.nextLine()) {
                case "1":
                    year = 2016;
                    invalidYear = false;
                    break;
                case "2":
                    year = 2017;
                    invalidYear = false;
                    break;
                default:
                    System.out.println("Invalid input. Please try again!");
            }
        } while (invalidYear);

        do {
            System.out.println("Please select the month of flight");
            if (year == 2016) {
                System.out.println((calendar.get(Calendar.MONTH) + 1) + " - 12");
                try {
                    month = scanInt.nextInt() - 1;
                    if (month < calendar.get(Calendar.MONTH)) {
                        System.out.println("Invalid input. Month can't be earlier than current month!");
                    } else if (month > 11) {
                        System.out.println("Invalid input. Maximum month is 12!");
                    } else {
                        invalidMonth = false;
                    }
                } catch (InputMismatchException ime) {
                    scanInt.next();
                    System.out.println("Invalid input. Please try again!");
                }
            } else {
                System.out.println("1 - 12");
                try {
                    month = scanInt.nextInt() - 1;
                    if (month < 0) {
                        System.out.println("Invalid input. Minimum month is 1!");
                    } else if (month > 11) {
                        System.out.println("Invalid input. Maximum month is 12!");
                    } else {
                        invalidMonth = false;
                    }
                } catch (InputMismatchException ime) {
                    scanInt.next();
                    System.out.println("Invalid input. Please try again!");
                }
            }
        } while (invalidMonth);

        do {
            System.out.println("Please select the day of flight");
            if (calendar.get(Calendar.MONTH) == currentCalendar.get(Calendar.MONTH)) {
                System.out.println((currentCalendar.get(Calendar.DAY_OF_MONTH) + 1)
                        + " - " + calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
                try {
                    day = scanInt.nextInt();
                    if (day < (currentCalendar.get(Calendar.DAY_OF_MONTH) + 1)) {
                        System.out.println("Invalid input. Day can't be earlier than today!");
                    } else if (day > calendar.getActualMaximum(Calendar.DAY_OF_MONTH)) {
                        System.out.println("Invalid input. Maximum day is "
                                + calendar.getActualMaximum(Calendar.DAY_OF_MONTH) + "!");
                    } else {
                        invalidDay = false;
                    }
                } catch (InputMismatchException ime) {
                    scanInt.next();
                    System.out.println("Invalid input. Please try again!");
                }
            } else {
                System.out.println((calendar.getActualMinimum(Calendar.DAY_OF_MONTH))
                        + " - " + calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
                try {
                    day = scanInt.nextInt();
                    if (day < calendar.get(calendar.getActualMinimum(Calendar.DAY_OF_MONTH))) {
                        System.out.println("Invalid input. Please try again!");
                    } else if (day > calendar.getActualMaximum(Calendar.DAY_OF_MONTH)) {
                        System.out.println("Invalid input. Please try again!");
                    } else {
                        invalidDay = false;
                    }
                } catch (InputMismatchException ime) {
                    scanInt.next();
                    System.out.println("Invalid input. Please try again!");
                }
            }
        } while (invalidDay);

        do {
            System.out.println("Please select the hour of the flight!\n(0-23)");
            try {
                hour = scanInt.nextInt();
                if (hour < 0) {
                    System.out.println("Invalid input. Minimum hour is 0!");
                } else if (hour > 23) {
                    System.out.println("Invalid input. Maximum hour is 23!");
                } else {
                    invalidHour = false;
                }
            } catch (InputMismatchException ime) {
                scanInt.next();
                System.out.println("Invalid input. Please try again!");
            }
        } while (invalidHour);

        do {
            System.out.println("Please select the minute of the flight!\n(0-59)");
            try {
                minute = scanInt.nextInt();
                if (minute < 0) {
                    System.out.println("Invalid input. Minimum hour is 0!");
                } else if (minute > 59) {
                    System.out.println("Invalid input. Maximum hour is 59!");
                } else {
                    invalidMinute = false;
                }
            } catch (InputMismatchException ime) {
                scanInt.next();
                System.out.println("Invalid input. Please try again!");
            }
        } while (invalidMinute);

        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, day);
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);        
        System.out.println(calendar.getTime());
        
        AirCraft airplane = null;
        airplane = fdb.setAirCraft(airplane, acdb, pdb, (int)flightDurationFloat);
        
        Flight flight = new Flight(airLines, originAirPort, destinationAirPort, airplane, flightDuration, minute);
        addFlight(generateFligthNumber(flight), fdb);
        
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
        return generateFligthNumber(fligth);
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
                    System.out.println("Invalid input. Input can't be less than 0!");
                } else if (hour > 59) {
                    System.out.println("Invalid input. Input can't be higher than 59!");
                } else {
                    invalidMinute = false;
                }
            } catch (NumberFormatException nfe) {
                System.out.println("Invalid input. The input has to be an integer within range 0-59");
            }
        } while (invalidMinute);

        Calendar cal = Calendar.getInstance();
        cal.setTime(flight.getDateOfFlight());
        cal.set(Calendar.HOUR_OF_DAY, hour);
        cal.set(Calendar.MINUTE, minute);
        Time departureTime = new Time(cal.getTimeInMillis());
        cal.add(Calendar.MILLISECOND, (int) flight.getFlightDurationInMilliseconds());
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
        cal.set(Calendar.HOUR_OF_DAY, randomGen.nextInt(24));
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

    // Asks user to select the destination country of the flight to search for
    private String selectCountry(AirPortDataBase adb) {
        Scanner scanText = new Scanner(System.in);
        String countryChoice = null;
        boolean invalidCountry = true;
        ArrayList<String> countries = displayListOfCountries(adb);
        Label:
        do {
            System.out.println("Please select country of destination from the list above:");
            countryChoice = scanText.nextLine();
            if (countryChoice.length() > 3) {
                for (String tempCountry : countries) {
                    if (tempCountry.equalsIgnoreCase(countryChoice)) {
                        countryChoice = tempCountry;
                        invalidCountry = false;
                        System.out.println("Chosen country: " + countryChoice);
                        break Label;
                    }
                }
            } else if (countryChoice.length() <= 3) {
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

    private ArrayList displayListOfCountries(AirPortDataBase adb) {
        ArrayList<String> countries = new ArrayList<String>();
        Label:
        for (Map.Entry entry : adb.getAirPorts().entrySet()) {
            AirPort tempAirPort = (AirPort) entry.getValue();
            if (countries.isEmpty()) {
                countries.add(tempAirPort.getCountry().replaceAll("\"", ""));
                continue;
            } else {
                for (String tempCountry : countries) {
                    if (tempCountry.equalsIgnoreCase(tempAirPort.getCountry().replaceAll("\"", ""))) {
                        continue Label;
                    }
                }
                countries.add(tempAirPort.getCountry().replaceAll("\"", ""));
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

    // Asks user to select the destination city of the flight to search for
    private String selectCity(AirPortDataBase adb, String country) {
        Scanner scanText = new Scanner(System.in);
        String city = null;
        ArrayList<String> cities = displayListOfCities(adb, country);
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
            }
            tempCity = null;
        }
        cities.sort(new StringComparator());;
        cities.forEach(city -> System.out.println((cities.indexOf(city) + 1) + ". " + city));
        return cities;
    }

    // Displayes cities of all flights to chosen country
    private ArrayList displayListOfCities(AirPortDataBase adb, String country) {
        ArrayList<String> cities = new ArrayList<String>();
        String tempCity = null;
        for (Map.Entry entry : adb.getAirPorts().entrySet()) {
            AirPort tempAirport = (AirPort) entry.getValue();
            if (tempAirport.getCountry().replaceAll("\"", "").equalsIgnoreCase(country)) {
                tempCity = tempAirport.getCity().replaceAll("\"", "");
                if (cities.isEmpty()) {
                    cities.add(tempCity);
                } else {
                    for (String city : cities) {
                        if (city.equalsIgnoreCase(tempAirport.getCity().replaceAll("\"", ""))) {
                            continue;
                        }
                    }
                    cities.add(tempCity);
                }
            }
            tempCity = null;
        }
        cities.sort(new StringComparator());;
        cities.forEach(city -> System.out.println((cities.indexOf(city) + 1) + ". " + city));
        return cities;
    }

    @Override
    public String toString() {
        return "Admin:"
                + "\nUser Name = " + getUserName()
                + "\nPassword = " + getPassword();
    }
}
