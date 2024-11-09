// Monkey.java
/**
 * Class representing a Monkey, which is a type of Mammal.
 */
public class Monkey extends Mammal {
    /**
     * Constructs a Monkey with the specified name, age, and color.
     *
     * @param name  the name of the monkey
     * @param age   the age of the monkey
     * @param color the color of the monkey
     */
    public Monkey(String name, int age, String color) {
        super(name, age, color);
    }

    /**
     * Simulates the monkey eating.
     */
    @Override
    public void eating() {
        System.out.println(getName() + " the monkey is eating bananas.");
    }

    /**
     * Simulates the monkey climbing.
     */
    public void climbing() {
        System.out.println(getName() + " is climbing a tree.");
    }

    /**
     * Returns a string representation of the Monkey.
     *
     * @return a string containing the monkey's details
     */
    @Override
    public String toString() {
        return super.toString() + ", kind=Monkey" ;
    }

    /**
     * Creates and returns a copy of this Monkey.
     *
     * @return a clone of this Monkey
     */
    @Override
    public Monkey clone() {
        return (Monkey) super.clone();
    }
}
