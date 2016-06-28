package samair;

import java.util.Comparator;

/**
 *
 * @author Przemek Stepien
 */
public class StringComparator implements Comparator<String>  {
    
    
    @Override
    public int compare(String s1, String s2) {
        return s1.compareTo(s2);
    }
}