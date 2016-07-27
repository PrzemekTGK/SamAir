/*
 * Abstract Parent class for User like classes 
 */
package samair;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author Przemek Stepien
 */
public abstract class User implements Serializable {

    public final static int UPDATE = 0;
    public final static int REMOVE = 1;
    private String userName;
    private String password;

    /**
     * Displays all flights from pseudo database of flights
     *
     * @param fdb is the date base of flights to be displayed
     */
    void viewFlights(JourneyDataBase fdb) {
        fdb.getScheduledFlights().forEach((k, v) -> System.out.println(v));
    }

    /**
     * Displays the list of all cities from the chosen country of chosen created
     * flight
     *
     * @param fdb is used to get access to all created flights
     * @param country is used to determine the country to search for the city in
     * @return the list of cities of all created flights
     */
    private ArrayList displayListOfAirPorts(JourneyDataBase fdb, String country) {
        ArrayList<String> cities = new ArrayList<>();
        ArrayList<String> citiesAndAirPorts = new ArrayList<>();
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
     *
     * @param adb is used to get access to all possible airports to create
     * flight for
     * @param country is used to determine the country to search for the city in
     * @return list of all possible cities to create the flight for
     */
    private ArrayList displayListOfAirPorts(AirPortDataBase adb, String country) {
        ArrayList<String> cities = new ArrayList<>();
        ArrayList<String> citiesAndAirPorts = new ArrayList<>();
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

    /**
     * Displays the list of all countries that there is flights for
     *
     * @param fdb is used to get the list of all the flights
     * @return list of countries that there's flight for
     */
    private ArrayList displayListOfCountries(JourneyDataBase fdb) {
        ArrayList<String> countries = new ArrayList<>();
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
     *
     * @param adb is used to get the list of all possible countries
     * @return list of all possible countries
     */
    private ArrayList displayListOfCountries(AirPortDataBase adb) {
        ArrayList<String> countries = new ArrayList<>();
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
     * Asks user to select the destination country of the flight to search for
     * from the list of already scheduled flights
     *
     * @param fdb is used to search the for the flight that has to be updated
     * @return String with destination country
     */
    String selectCountry(JourneyDataBase fdb) {
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
     *
     * @param adb to get the list of all possible countries for flight to be
     * created
     * @return String with destination country
     */
    String selectCountry(AirPortDataBase adb) {
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
     * Asks user to select the destination city of the flight to search for to
     * be updated
     *
     * @param fdb is used to get access to the list of all existing flights
     * @param country is used to determine the country of flight to be updated
     * @return chosen by user city from the given country
     */
    String selectCity(JourneyDataBase fdb, String country) {
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
     *
     * @param adb is used to get the list of all possible cities for flight
     * creation
     * @param country is used to determine the country of flight to created
     * @return chosen by user city from the given country
     */
    String selectCity(AirPortDataBase adb, String country) {
        Scanner scanText = new Scanner(System.in);
        String city = null;
        ArrayList<String> cities = displayListOfAirPorts(adb, country);
        boolean invalidCity = true;
        Label:
        do {
            System.out.println("Please select city of destination from the list above:");
            city = scanText.nextLine();
            if (city.length() > 3) {
                for (String tempCity : cities) {
                    if (tempCity.equalsIgnoreCase(city)) {
                        city = tempCity;
                        invalidCity = false;
                        System.out.println("Chosen city: " + city);
                        break Label;
                    }
                }
            } else if (city.length() <= 3) {
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

    ;
    
    /**
     * Gets user's name
     * 
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Sets user's name
     * 
     * @param userName the userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Gets user's password
     * 
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets user's password
     * 
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Displays user's name to console 
     * 
     * @return String containing user's name
     */   
    @Override
    public String toString() {
        return "user:"
                + "\nUser Name = " + userName;
    }
}
