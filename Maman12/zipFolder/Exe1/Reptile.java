// Reptile.java
/**
 * Abstract class representing reptiles, extending the Animal class.
 */
public abstract class Reptile extends Animal {
    /**
     * Constructs a Reptile with the specified name, age, and color.
     *
     * @param name  the name of the reptile
     * @param age   the age of the reptile
     * @param color the color of the reptile
     */
    public Reptile(String name, int age, String color) {
        super(name, age, color);
    }

    /**
     * Simulates the reptile crawling.
     */
    public void crawling() {
        System.out.println(getName() + " is crawling.");
    }
}
