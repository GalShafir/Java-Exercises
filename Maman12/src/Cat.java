/**
 * Class representing a Cat, which is a type of Mammal.
 */
public class Cat extends Mammal {
    private Owner owner;

    /**
     * Constructs a Cat with the specified name, age, color, and owner.
     *
     * @param name  the name of the cat
     * @param age   the age of the cat
     * @param color the color of the cat
     * @param owner the owner of the cat
     */
    public Cat(String name, int age, String color, Owner owner) {
        super(name, age, color);
        this.owner = owner;
    }

    /**
     * Gets the owner of the cat.
     *
     * @return the owner of the cat
     */
    public Owner getOwner() {
        return owner;
    }

    /**
     * Sets the owner of the cat.
     *
     * @param owner the new owner of the cat
     */
    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    /**
     * Simulates the cat eating.
     */
    @Override
    public void eating() {
        System.out.println(getName() + " the cat is eating cat food.");
    }

    /**
     * Simulates the cat meowing.
     */
    public void meowing() {
        System.out.println(getName() + " is meowing.");
    }

    /**
     * Returns a string representation of the Cat.
     *
     * @return a string containing the cat's details and owner information
     */
    @Override
    public String toString() {
        return super.toString() + ", kind=Cat" + ", " + owner;
    }

    /**
     * Checks if this Cat is equal to another object.
     *
     * @param obj the object to compare with
     * @return true if the other object is a Cat with the same attributes and owner
     */
    @Override
    public boolean equals(Object obj) {
        if (!super.equals(obj))
            return false;
        if (!(obj instanceof Cat))
            return false;
        Cat other = (Cat) obj;
        return owner.equals(other.owner);
    }

    /**
     * Creates and returns a copy of this Cat.
     *
     * @return a clone of this Cat
     */
    @Override
    public Cat clone() {
        Cat cloned = (Cat) super.clone();
        cloned.owner = owner.clone(); // Deep copy owner
        return cloned;
    }
}
