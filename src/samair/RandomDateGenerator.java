/*
 * RandomDateGenerator class generates a random date between now and 1 year from now
 */
package samair;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Random;

/**
 * @author Przemek Stepien
 */
public class RandomDateGenerator {
    private Date dateMin = null;
    private Date dateMax = null;
        

    /**
     * Creates a new instance of RandomDateGenerator
     * 
     * @param min defines minimum date for random date generation
     * @param max defines maximum date for random date generation
     */
    public RandomDateGenerator(Date min, Date max) {
        dateMin = min;
        dateMax = max;
    }

    /**
     * Generates a random date between today and 1 year from today
     * 
     * @return Date object with random date between today and 1 year from today
     */
    public Date generate() {
        // Calculate miliseconds per dey
        final long MILLIS_PER_DAY = 1000 * 60 * 60 * 24;
        // Create 2 new instances of GregoriaCalendar objects
        GregorianCalendar start = new GregorianCalendar();
        GregorianCalendar end = new GregorianCalendar();
        start.setTimeInMillis(dateMin.getTime());
        end.setTimeInMillis(dateMax.getTime());

        // Get difference in milliseconds
        long endLong = end.getTimeInMillis() + end.getTimeZone().getOffset(end.getTimeInMillis());
        long startLong = start.getTimeInMillis() + start.getTimeZone().getOffset(start.getTimeInMillis());
        long dayDiff = (endLong - startLong) / MILLIS_PER_DAY;

        // Create ne instance of Calendar object
        Calendar calendar = Calendar.getInstance();
        // Set minimal dat in miliseconds
        calendar.setTime(dateMin);
        // Add random date in miliseconds to minimal date
        calendar.add(Calendar.DATE, new Random().nextInt((int) dayDiff));
        // Display random date
        return calendar.getTime();
}
    }