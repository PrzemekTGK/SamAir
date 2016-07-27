/*
 * StringComparator class used to sort ArrayLists of Strings
 */
package samair;

import java.util.Comparator;

/**
 *
 * @author Przemek Stepien
 */
public class StringComparator implements Comparator<String>  {
    
    /**
     * Used to sort items in ArrayLists
     * 
     * @param s1 used to compare with s2
     * @param s2 used to compare with s1
     * @return 
     */
    @Override
    public int compare(String s1, String s2) {
        return s1.compareTo(s2);
    }
}