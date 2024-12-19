/**
 * Utility class containing generic methods for GenericSet.
 */
import java.util.Iterator;

public class SetUtilities {
    /**
     * Generic method that accepts a set with elements of any type that implements the Comparable interface.
     * The method returns the minimum element in the set without destroying the contents of the set.
     *
     * @param set the GenericSet containing elements of type T
     * @param <T> the type parameter extending Comparable
     * @return the minimum element in the set
     */
    public static <T extends Comparable<T>> T findMin(GenericSet<T> set) {
        Iterator<T> iterator = set.iterator();
        if (!iterator.hasNext()) {
            return null; // Return null if the set is empty
        }
        T min = iterator.next();
        while (iterator.hasNext()) {
            T current = iterator.next();
            if (current.compareTo(min) < 0) {
                min = current;
            }
        }
        return min;
    }
}
