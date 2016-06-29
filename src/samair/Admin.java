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

    /**
     * Asks admin user for all the details about the flight to be created
     * and creates and returns a new flight based on those details
     * @param adb
     * @param fdb
     * @param acdb
     * @param pdb 
     */
    public Flight createFlight(AirPortDataBase adb, FlightDataBase fdb, AirCraftDataBase acdb,
            PilotDataBase pdb) {
        // Declare and instantiate scanner objects for user's input
        Scanner scanText = new Scanner(System.in);
        Scanner scanInt = new Scanner(System.in);
        // Select the Airlines
        String airLines = selectAirLines(fdb, scanInt);
        // Select origin AirPort
        AirPort originAirPort = selectOriginAirport(adb);
        // Select destination AirPort
        AirPort destinationAirPort = selectDestinationAirPort(adb);
        // Calculate flight duration between origin and destination AirPorts
        double flightDurationFloat = fdb.calculateFligthDurationInDecimal(fdb.calculateDistance(
                originAirPort.getLatitude(), originAirPort.getLongitude(),
                destinationAirPort.getLatitude(), destinationAirPort.getLongitude(), "KM"));
        // Convert flight's duration from decimal to text represantation
        String flightDurationText = fdb.convertDecimalToHours(flightDurationFloat);
        // Create a Calendar aboject with current date
        Calendar currentCalendar = Calendar.getInstance();
        // Create a Calendar object to set date and times for the flight
        Calendar calendarForFlight = new GregorianCalendar();
        // Set the year of the flight
        int year = selectYear(scanText, currentCalendar);
        calendarForFlight.set(Calendar.YEAR, year);
        // Ste the month of the flight
        int month = selectMonth(scanInt, year, currentCalendar);
        calendarForFlight.set(Calendar.MONTH, month);
        // Set the day of the flight
        int day = selectDay(calendarForFlight, currentCalendar, scanInt);
        calendarForFlight.set(Calendar.DAY_OF_MONTH, day);
        // Set the hour of the flight's time
        int hour = selectHour(scanInt);
        calendarForFlight.set(Calendar.HOUR_OF_DAY, hour);
        // Set the minute of the fligth's time
        int minute = selectMinute(scanInt);
        calendarForFlight.set(Calendar.MINUTE, minute);
        // Set the AirPLane and Pilot for the flight
        AirCraft airplane = null;
        airplane = fdb.setAirCraft(airplane, acdb, pdb, (int) flightDurationFloat);
        // Create and return the new flight object
        Flight flight = new Flight(airLines, originAirPort, destinationAirPort, airplane, flightDurationText, minute);
        return  flight;
    }

    /**
     * Displays a list of all possible airlines and asks admin user to select one
     * of those airlines for the flight to be created. 
     * @param fdb FlightDataBase is used to access list of all airlines
     * @param scanInt is used for user's input.
     * @return 
     */    
    private String selectAirLines(FlightDataBase fdb, Scanner scanInt) {
        String airLines = null;
        boolean invalidAirLines = true;
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
        return airLines;
    }

    /**
     * Automatically finds and selects Dublin Airport as origin AirPort and returns it
     * @param adb is used to get access to all AirPorts
     * @return origin AirPortObject
     */
    private AirPort selectOriginAirport(AirPortDataBase adb) {
        AirPort originAirPort = null;
        for (Map.Entry entry : adb.getAirPorts().entrySet()) {
            AirPort tempAirport = (AirPort) entry.getValue();
            if (tempAirport.getCountry().replaceAll("\"", "").equalsIgnoreCase("ireland")
                    && tempAirport.getCity().replaceAll("\"", "").equalsIgnoreCase("dublin")) {
                originAirPort = tempAirport;
                break;
            }
        }
        return originAirPort;
    }


    /**
     * Asks user to select the destination AirPort for the flight to be created.
     * @param adb is used to get access to all AirPorts
     * @return destination AirPort object
     */
    private AirPort selectDestinationAirPort(AirPortDataBase adb) {
        AirPort destinationAirPort = null;
        // Asks user to select the destination country of the flight
        String destinationCountry = selectCountry(adb);
        // Asks user to select the destination destination airport of the flight
        String destinationCity = selectCity(adb, destinationCountry);
        // Airport is selected based on user's city and country choice
        for (Map.Entry entry : adb.getAirPorts().entrySet()) {
            AirPort tempAirport = (AirPort) entry.getValue();
            if (tempAirport.getCountry().replaceAll("\"", "").equalsIgnoreCase(destinationCountry)
                    && tempAirport.getCity().replaceAll("\"", "").equalsIgnoreCase(destinationCity)) {
                destinationAirPort = tempAirport;
                break;
            }
        }
        return destinationAirPort;
    }

    /**
     * User is asked to select current or next year for the flight
     * @param scanText is used to get the input from the user
     * @param currentCalendar is used to retrieve current and next year
     * @return the year of the flight
     */
    private int selectYear(Scanner scanText, Calendar currentCalendar) {
        int year = 0;
        boolean invalidYear = true;
        do {
            System.out.println("Please select the year of flight\n1. 2016\n2. 2017");
            switch (scanText.nextLine()) {
                case "1":
                    year = currentCalendar.get(Calendar.YEAR);
                    invalidYear = false;
                    break;
                case "2":
                    currentCalendar.set(Calendar.YEAR, 1);
                    year = currentCalendar.get(Calendar.YEAR);
                    invalidYear = false;
                    break;
                default:
                    System.out.println("Invalid input. Please try again!");
            }
        } while (invalidYear);
        return year;
    }

    /**
     * User is asked to select the month of the flight
     * @param scanInt is used to get the input form the user
     * @param year is used to determine chosen year of the flight so user can't 
     * select a month before the current date is current year was selected for
     * the flight
     * @param currentCalendar is used to retrieve current month for validation
     * of user's month choice
     * @return the month of the flight
     */
    private int selectMonth(Scanner scanInt, int year, Calendar currentCalendar) {
        int month = 0;
        boolean invalidMonth = true;
        do {
            System.out.println("Please select the month of flight");
            if (year == 2016) {
                System.out.println((currentCalendar.get(Calendar.MONTH) + 1) + " - 12");
                try {
                    month = scanInt.nextInt() - 1;
                    if (month < currentCalendar.get(Calendar.MONTH)) {
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
        return month;
    }

    /**
     * User is asked to select the day of the flight
     * @param calendarForFlight is used to determine user's month choice so that
     * @param currentCalendar is used to retrieve current date so that the user
     * can't choose a date before the current date
     * @param scanInt is used to get the input from the user
     * @return the day of the flight
     */
    private int selectDay(Calendar calendarForFlight, Calendar currentCalendar, Scanner scanInt) {
        int day = 0;
        boolean invalidDay = true;
        do {
            System.out.println("Please select the day of flight");
            if (calendarForFlight.get(Calendar.MONTH) == currentCalendar.get(Calendar.MONTH)) {
                System.out.println((currentCalendar.get(Calendar.DAY_OF_MONTH))
                        + " - " + calendarForFlight.getActualMaximum(Calendar.DAY_OF_MONTH));
                try {
                    day = scanInt.nextInt();
                    if (day < (currentCalendar.get(Calendar.DAY_OF_MONTH))) {
                        System.out.println("Invalid input. Day can't be earlier than today!");
                    } else if (day > calendarForFlight.getActualMaximum(Calendar.DAY_OF_MONTH)) {
                        System.out.println("Invalid input. Maximum day is "
                                + calendarForFlight.getActualMaximum(Calendar.DAY_OF_MONTH) + "!");
                    } else {
                        invalidDay = false;
                    }
                } catch (InputMismatchException ime) {
                    scanInt.next();
                    System.out.println("Invalid input. Please try again!");
                }
            } else {
                System.out.println((calendarForFlight.getActualMinimum(Calendar.DAY_OF_MONTH))
                        + " - " + calendarForFlight.getActualMaximum(Calendar.DAY_OF_MONTH));
                try {
                    day = scanInt.nextInt();
                    if (day < calendarForFlight.get(calendarForFlight.getActualMinimum(Calendar.DAY_OF_MONTH))) {
                        System.out.println("Invalid input. Please try again!");
                    } else if (day > calendarForFlight.getActualMaximum(Calendar.DAY_OF_MONTH)) {
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
        return day;
    }

    /**
     * User is asked to select the hour of flight
     * @param scanInt is used to get the input from the user
     * @return the hour of the flight
     */
    private int selectHour(Scanner scanInt) {
        int hour = 0;
        boolean invalidHour = true;
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
        return hour;
    }

    /**
     * User is asked to select the minute of the flight
     * @param scanInt is used to get the input from the user
     * @return return the minute of the flight
     */
    private int selectMinute(Scanner scanInt) {
        int minute = 0;
        boolean invalidMinute = true;
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
        return minute;
    }

    /**
     * Schedules a newly created flight 
     * @param fligth is a newly created flight passed into the method
     * to be scheduled
     * @return a scheduled flight
     */
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

    /**
     * Adds a created and scheduled flight to FlightDataBase
     * @param fligth is the flight to be added to the database
     * @param fdb is the data base that the flight is added to
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

    /**
     * Updates a scheduled flight
     * @param fdb is used to get access to the flight that has to be updated
     */
    public void updateFlight(FlightDataBase fdb) {
        Scanner scanInt = new Scanner(System.in);
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

        int hour = selectHour(scanInt);
        int minute = selectMinute(scanInt);
        
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

    /**
     * Generate random date between now and 1 year 
     * from now to use in scheduleFlight method
     * @return a random Date object between now and year from now
     */
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

    /**
     * Generates a unique flight number based of the info about the flight itself
     * @param fligth is used to retrieve the info about that flight and to generate
     * the flight number for it
     * @return a Flight object with generated flight number
     */
    private Flight generateFligthNumber(Flight fligth) {
        Random randomGen = new Random(System.nanoTime());
        String A = fligth.getAirLine().substring(0, 2).toUpperCase();
        String B = fligth.getOrigin().getCity().substring(0, 3).replaceAll("\"", "").toUpperCase();
        String C = fligth.getDestination().getCity().substring(0, 3).replaceAll("\"", "").toUpperCase();

        String fligthNo = A + (randomGen.nextInt(91) + 10) + B + (randomGen.nextInt(91) + 10) + C + randomGen.nextInt(10);

        fligth.setFlightNumber(fligthNo);

        return fligth;
    }

    /**
     * Asks user to select the destination country of the flight to search for
     * from the list of already scheduled flights
     * @param fdb is used to search the for the flight that has to be updated
     * @return String with destination country
     */
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

    /**
     * Asks user to select the destination country of the flight to be created
     * @param adb to get the list of all possible countries for flight to be created
     * @return String with destination country
     */
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

    /**
     * Displays the list of all countries that there is flights for
     * @param fdb is used to get the list of all the flights 
     * @return list of countries that there's flight for 
     */
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

    /**
     * Displays the list of all possible countries to create flight for
     * @param adb is used to get the list of all possible countries
     * @return list of all possible countries 
     */
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
    
    /**
     * Asks user to select the destination city of the flight to search for to 
     * be updated
     * @param fdb is used to get access to the list of all existing flights
     * @param country is used to determine the country of flight to be updated
     * @return chosen by user city from the given country
     */
    private String selectCity(FlightDataBase fdb, String country) {
        Scanner scanText = new Scanner(System.in);
        String city = null;
        ArrayList<String> cities = displayListOfAirPorts(fdb, country);
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

    /**
     * Asks user to select the destination city of the flight to be created
     * @param adb is used to get the list of all possible cities for flight creation
     * @param country is used to determine the country of flight to created
     * @return chosen by user city from the given country
     */
    private String selectCity(AirPortDataBase adb, String country) {
        Scanner scanText = new Scanner(System.in);
        String city = null;
        ArrayList<String> cities = displayListOfAirPorts(adb, country);
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

    /**
     * Displays the list of all cities from the chosen country of chosen created flight
     * @param fdb is used to get access to all created flights 
     * @param country is used to determine the country to search for the city in 
     * @return the list of cities of all created flights
     */
    private ArrayList displayListOfAirPorts(FlightDataBase fdb, String country) {
        ArrayList<String> cities = new ArrayList<String>();
        ArrayList<String> citiesAndAirPorts = new ArrayList<String>();        
        String tempCity = null;
        String cityAndAirPort = null;
        for (Map.Entry entry : fdb.getScheduledFlights().entrySet()) {
            Flight tempFlight = (Flight) entry.getValue();
            if (tempFlight.getDestination().getCountry().replaceAll("\"", "").equalsIgnoreCase(country)) {
                tempCity = tempFlight.getDestination().getCity().replaceAll("\"", "");
                cityAndAirPort = tempFlight.getDestination().getCity().replaceAll("\"", "") + " - " + tempFlight.getDestination().getName().replaceAll("\"", "");
                if (cities.isEmpty()) {
                    cities.add(tempCity);
                    citiesAndAirPorts.add(cityAndAirPort);
                } else {
                    for (String city : cities) {
                        if (city.equalsIgnoreCase(tempFlight.getDestination().getCity().replaceAll("\"", ""))) {
                            continue;
                        }
                    }
                    cities.add(tempCity);
                    citiesAndAirPorts.add(cityAndAirPort);
                }
            }
            tempCity = null;
        }
        cities.sort(new StringComparator());
        citiesAndAirPorts.sort(new StringComparator());
        citiesAndAirPorts.forEach(city -> System.out.println((citiesAndAirPorts.indexOf(city) + 1) + ". " + city));
        return cities;
    }

    /**
     * Displays the list of all possible cities to create the flight for
     * @param adb is used to get access to all possible airports to create flight for
     * @param country is used to determine the country to search for the city in
     * @return list of all possible cities to create  the flight for
     */
    private ArrayList displayListOfAirPorts(AirPortDataBase adb, String country) {
        ArrayList<String> cities = new ArrayList<String>();
        ArrayList<String> citiesAndAirPorts = new ArrayList<String>();        
        String tempCity = null;
        String cityAndAirPort = null;
        for (Map.Entry entry : adb.getAirPorts().entrySet()) {
            AirPort tempAirport = (AirPort) entry.getValue();
            if (tempAirport.getCountry().replaceAll("\"", "").equalsIgnoreCase(country)) {
                tempCity = tempAirport.getCity().replaceAll("\"", "");
                cityAndAirPort = tempAirport.getCity().replaceAll("\"", "") + " - " + tempAirport.getName().replaceAll("\"", "");
                if (cities.isEmpty()) {
                    cities.add(tempCity);
                    citiesAndAirPorts.add(cityAndAirPort);
                } else {
                    for (String city : cities) {
                        if (city.equalsIgnoreCase(tempAirport.getCity().replaceAll("\"", ""))) {
                            continue;
                        }
                    }
                    cities.add(tempCity);
                    citiesAndAirPorts.add(cityAndAirPort);
                }
            }
            tempCity = null;
        }
        cities.sort(new StringComparator());;
        citiesAndAirPorts.sort(new StringComparator());
        citiesAndAirPorts.forEach(city -> System.out.println((citiesAndAirPorts.indexOf(city) + 1) + ". " + city));
        return cities;
    }

    @Override
    public String toString() {
        return "Admin:"
                + "\nUser Name = " + getUserName()
                + "\nPassword = " + getPassword();
    }
}
