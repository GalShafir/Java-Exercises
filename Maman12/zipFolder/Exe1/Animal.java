// Animal.java
/**
 * Abstract class representing a generic animal with common attributes and methods.
 */
public abstract class Animal implements Cloneable {
    private String name;
    private int age;
    private String color;

    /**
     * Constructs an Animal with the specified name, age, and color.
     *
     * @param name  the name of the animal
     * @param age   the age of the animal
     * @param color the color of the animal
     */
    public Animal(String name, int age, String color) {
        this.name = name;
        this.age = age;
        this.color = color;
    }

    /**
     * Gets the name of the animal.
     *
     * @return the name of the animal
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the animal.
     *
     * @param name the new name of the animal
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the age of the animal.
     *
     * @return the age of the animal
     */
    public int getAge() {
        return age;
    }

    /**
     * Sets the age of the animal.
     *
     * @param age the new age of the animal
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * Gets the color of the animal.
     *
     * @return the color of the animal
     */
    public String getColor() {
        return color;
    }

    /**
     * Sets the color of the animal.
     *
     * @param color the new color of the animal
     */
    public void setColor(String color) {
        this.color = color;
    }

    /**
     * Simulates the animal sleeping.
     */
    public void sleeping() {
        System.out.println(name + " is sleeping.");
    }

    /**
     * Abstract method for eating, to be implemented by subclasses.
     */
    public abstract void eating();

    /**
     * Returns a string representation of the Animal.
     *
     * @return a string containing the animal's name, age, and color
     */
    public String toString() {
        return "Animal Description: name='" + name + "', age=" + age + ", color='" + color + "'";
    }

    /**
     * Checks if this Animal is equal to another object.
     *
     * @param obj the object to compare with
     * @return true if the other object is an Animal of the same type with the same attributes
     */
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof Animal))
            return false;
        Animal other = (Animal) obj;
        return this.getClass() == other.getClass()
                && name.equals(other.name)
                && age == other.age
                && color.equals(other.color);
    }

    /**
     * Creates and returns a copy of this Animal.
     *
     * @return a clone of this Animal
     */
    public Animal clone() {
        try {
            Animal cloned = (Animal) super.clone();
            return cloned;
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }
}
