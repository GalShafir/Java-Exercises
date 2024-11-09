// Snake.java
/**
 * Class representing a Snake, which is a type of Reptile.
 */
public class Snake extends Reptile {
    /**
     * Constructs a Snake with the specified name, age, and color.
     *
     * @param name  the name of the snake
     * @param age   the age of the snake
     * @param color the color of the snake
     */
    public Snake(String name, int age, String color) {
        super(name, age, color);
    }

    /**
     * Simulates the snake eating.
     */
    @Override
    public void eating() {
        System.out.println(getName() + " the snake is eating a mouse.");
    }

    /**
     * Returns a string representation of the Snake.
     *
     * @return a string containing the snake's details
     */
    @Override
    public String toString() {
        return super.toString() + ", kind=Snake";
    }

    /**
     * Creates and returns a copy of this Snake.
     *
     * @return a clone of this Snake
     */
    @Override
    public Snake clone() {
        return (Snake) super.clone();
    }
}
