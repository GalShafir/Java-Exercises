// Bird.java
/**
 * Abstract class representing birds, extending the Animal class.
 */
public abstract class Bird extends Animal {
    /**
     * Constructs a Bird with the specified name, age, and color.
     *
     * @param name  the name of the bird
     * @param age   the age of the bird
     * @param color the color of the bird
     */
    public Bird(String name, int age, String color) {
        super(name, age, color);
    }

    /**
     * Simulates the bird flying.
     */
    public void flying() {
        System.out.println(getName() + " is flying.");
    }
}
