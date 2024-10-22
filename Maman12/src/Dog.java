// Dog.java
/**
 * Class representing a Dog, which is a type of Mammal.
 */
public class Dog extends Mammal {
    private Owner owner;

    /**
     * Constructs a Dog with the specified name, age, color, and owner.
     *
     * @param name  the name of the dog
     * @param age   the age of the dog
     * @param color the color of the dog
     * @param owner the owner of the dog
     */
    public Dog(String name, int age, String color, Owner owner) {
        super(name, age, color);
        this.owner = owner;
    }

    /**
     * Gets the owner of the dog.
     *
     * @return the owner of the dog
     */
    public Owner getOwner() {
        return owner;
    }

    /**
     * Sets the owner of the dog.
     *
     * @param owner the new owner of the dog
     */
    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    /**
     * Simulates the dog eating.
     */
    @Override
    public void eating() {
        System.out.println(getName() + " the dog is eating dog food.");
    }

    /**
     * Simulates the dog barking.
     */
    public void barking() {
        System.out.println(getName() + " is barking.");
    }

    /**
     * Returns a string representation of the Dog.
     *
     * @return a string containing the dog's details and owner information
     */
    @Override
    public String toString() {
        return super.toString() + ", kind=Dog" + ", " + owner;
    }

    /**
     * Checks if this Dog is equal to another object.
     *
     * @param obj the object to compare with
     * @return true if the other object is a Dog with the same attributes and owner
     */
    @Override
    public boolean equals(Object obj) {
        if (!super.equals(obj))
            return false;
        if (!(obj instanceof Dog))
            return false;
        Dog other = (Dog) obj;
        return owner.equals(other.owner);
    }

    /**
     * Creates and returns a copy of this Dog.
     *
     * @return a clone of this Dog
     */
    @Override
    public Dog clone() {
        Dog cloned = (Dog) super.clone();
        cloned.owner = owner.clone(); // Deep copy owner
        return cloned;
    }
}
