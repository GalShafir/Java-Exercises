// Owner.java
/**
 * Represents an owner of an animal, including name and phone number.
 */
public class Owner {
    private String name;
    private String phoneNumber;

    /**
     * Constructs an Owner with the specified name and phone number.
     *
     * @param name        the name of the owner
     * @param phoneNumber the phone number of the owner
     */
    public Owner(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    /**
     * Copy constructor for Owner.
     *
     * @param owner the Owner to copy
     */
    public Owner(Owner owner) {
        this.name = owner.name;
        this.phoneNumber = owner.phoneNumber;
    }

    /**
     * Gets the name of the owner.
     *
     * @return the name of the owner
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the owner.
     *
     * @param name the new name of the owner
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the phone number of the owner.
     *
     * @return the phone number of the owner
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Sets the phone number of the owner.
     *
     * @param phoneNumber the new phone number of the owner
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Returns a string representation of the Owner.
     *
     * @return a string containing the owner's name and phone number
     */
    public String toString() {
        return "Owner detalis: (name='" + name + "', phoneNumber='" + phoneNumber + ")";
    }

    /**
     * Checks if this Owner is equal to another object.
     *
     * @param obj the object to compare with
     * @return true if the other object is an Owner with the same name and phone number
     */
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof Owner))
            return false;
        Owner other = (Owner) obj;
        return name.equals(other.name) && phoneNumber.equals(other.phoneNumber);
    }

    /**
     * Creates and returns a copy of this Owner.
     *
     * @return a clone of this Owner
     */
    public Owner clone() {
        return new Owner(this);
    }
}
