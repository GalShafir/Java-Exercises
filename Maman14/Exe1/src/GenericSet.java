/**
 * GenericSet class that defines a set of elements of any type.
 * Implemented using an ArrayList to store elements.
 * Duplicate elements are not allowed.
 * Equality of elements is determined using the equals method.
 *
 * @param <T> the type of elements in the set
 */
import java.util.ArrayList;
import java.util.Iterator;

public class GenericSet<T> {
    private ArrayList<T> elements;

    /**
     * No-argument constructor that initializes the set as an empty set.
     */
    public GenericSet() {
        elements = new ArrayList<>();
    }

    /**
     * Constructor that accepts an array of elements of the type
     * and creates a set containing these elements.
     * Duplicate elements are not added to the set.
     *
     * @param arr the array of elements to be added to the set
     */
    public GenericSet(T[] arr) {
        elements = new ArrayList<>();
        for (T item : arr) {
            if (!elements.contains(item)) {
                elements.add(item);
            }
        }
    }

    /**
     * Union operation that accepts a set and merges it with the current set.
     * The current set will represent the union of both sets after this operation.
     *
     * @param otherSet the set to be united with the current set
     */
    public void union(GenericSet<T> otherSet) {
        for (T item : otherSet.elements) {
            if (!elements.contains(item)) {
                elements.add(item);
            }
        }
    }

    /**
     * Intersection operation that accepts a set and performs set intersection.
     * The current set will represent the intersection of both sets after this operation.
     *
     * @param otherSet the set to intersect with the current set
     */
    public void intersect(GenericSet<T> otherSet) {
        Iterator<T> iterator = elements.iterator();
        while (iterator.hasNext()) {
            T item = iterator.next();
            if (!otherSet.elements.contains(item)) {
                iterator.remove();
            }
        }
    }

    /**
     * Checks if the provided set is a subset of the current set.
     *
     * @param otherSet the set to check for subset relation
     * @return true if otherSet is a subset of the current set, false otherwise
     */
    public boolean isSubset(GenericSet<T> otherSet) {
        for (T item : otherSet.elements) {
            if (!elements.contains(item)) {
                return false;
            }
        }
        return true;
    }
    /**
     * Checks if the provided element is a member of the set.
     *
     * @param element the element to check for membership
     * @return true if the element belongs to the set, false otherwise
     */
    public boolean isMember(T element) {
        return elements.contains(element);
    }

    /**
     * Inserts an element into the set.
     * If an equivalent element already exists, the operation does nothing.
     *
     * @param element the element to be added to the set
     */
    public void insert(T element) {
        if (!elements.contains(element)) {
            elements.add(element);
        }
    }

    /**
     * Deletes an element from the set.
     * If the element does not exist, the operation does nothing.
     *
     * @param element the element to be removed from the set
     */
    public void delete(T element) {
        elements.remove(element);
    }

    /**
     * Returns an iterator to iterate over the elements of the set.
     *
     * @return an Iterator over the elements in the set
     */
    public Iterator<T> iterator() {
        return elements.iterator();
    }

    /**
     * Returns a string representation of the set.
     *
     * @return a string representing the elements of the set
     */
    @Override
    public String toString() {
        return elements.toString();
    }
}
