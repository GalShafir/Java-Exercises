/**
 * Person class that implements Comparable<Person>.
 * Includes ID, last name, first name, and birth year.
 */
public class Person implements Comparable<Person> {
    private String id;
    private String lastName;
    private String firstName;
    private int birthYear;

    /**
     * Constructs a Person object with the specified details.
     *
     * @param id        the ID of the person
     * @param lastName  the last name of the person
     * @param firstName the first name of the person
     * @param birthYear the birth year of the person
     */
    public Person(String id, String lastName, String firstName, int birthYear) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.birthYear = birthYear;
    }

    /**
     * Compares this person with the specified person for order.
     * Returns a negative integer, zero, or a positive integer as this person's ID
     * is lexicographically less than, equal to, or greater than the specified person's ID.
     *
     * @param other the person to be compared
     * @return a negative integer, zero, or a positive integer as this person's ID
     * is less than, equal to, or greater than the specified person's ID
     */
    @Override
    public int compareTo(Person other) {
        return this.id.compareTo(other.id);
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     * Two people are considered equal if their ID numbers are identical.
     *
     * @param obj the reference object with which to compare
     * @return true if this person is the same as the obj argument; false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj instanceof Person) {
            Person other = (Person) obj;
            return this.id.equals(other.id);
        }

        return false;
    }

    /**
     * Returns a hash code value for the object.
     * Consistent with equals method.
     * If two objects are equal according to equals(), they must have the same hashCode().
     * That's why I implemented this.
     *
     * @return a hash code value for this person
     */
    @Override
    public int hashCode() {
        return id.hashCode();
    }

    /**
     * Returns a string representation of the person.
     *
     * @return a string representing the person's details
     */
    @Override
    public String toString() {
        return String.format("[ID: %s, First Name: %s, Last Name: %s, Birth Year: %d]", id, firstName, lastName, birthYear);
    }
}
