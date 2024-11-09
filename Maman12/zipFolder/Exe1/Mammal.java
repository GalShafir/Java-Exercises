// Mammal.java
/**
 * Abstract class representing mammals, extending the Animal class.
 */
public abstract class Mammal extends Animal {
    /**
     * Constructs a Mammal with the specified name, age, and color.
     *
     * @param name  the name of the mammal
     * @param age   the age of the mammal
     * @param color the color of the mammal
     */
    public Mammal(String name, int age, String color) {
        super(name, age, color);
    }
}
