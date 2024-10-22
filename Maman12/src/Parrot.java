// Parrot.java
/**
 * Class representing a Parrot, which is a type of Bird.
 */
public class Parrot extends Bird {
    /**
     * Constructs a Parrot with the specified name, age, and color.
     *
     * @param name  the name of the parrot
     * @param age   the age of the parrot
     * @param color the color of the parrot
     */
    public Parrot(String name, int age, String color) {
        super(name, age, color);
    }

    /**
     * Simulates the parrot eating.
     */
    @Override
    public void eating() {
        System.out.println(getName() + " the parrot is eating seeds.");
    }

    /**
     * Returns a string representation of the Parrot.
     *
     * @return a string containing the parrot's details
     */
    @Override
    public String toString() {
        return super.toString() + ", kind=Parrot";
    }

    /**
     * Creates and returns a copy of this Parrot.
     *
     * @return a clone of this Parrot
     */
    @Override
    public Parrot clone() {
        return (Parrot) super.clone();
    }
}
