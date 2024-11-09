// BoardingHouse.java

import java.util.ArrayList;

/**
 * Main class to demonstrate polymorphism with a collection of animals.
 */
public class BoardingHouse {
    /**
     * The main method where execution begins.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        ArrayList<Animal> animals = new ArrayList<>();

        Owner owner1 = new Owner("Gal", "9720521231234");
        Owner owner2 = new Owner("Dan", "9720501230003");

        // That is actually my dog's name :)
        Dog dog1 = new Dog("Rio", 1, "White", owner1);
        Cat cat1 = new Cat("Milky", 3, "Black", owner2);
        Parrot parrot1 = new Parrot("Parry", 6, "Green");
        Snake snake1 = new Snake("Snookie", 8, "Yellow");
        Monkey monkey1 = new Monkey("Koko", 15, "Black");

        animals.add(dog1);
        animals.add(cat1);
        animals.add(parrot1);
        animals.add(snake1);
        animals.add(monkey1);

        // Iterate through the list
        for (Animal animal : animals) {
            System.out.println(animal.toString());
            animal.eating();
            animal.sleeping();

            // Invoke unique methods using instanceof
            if (animal instanceof Dog) {
                ((Dog) animal).barking();
            }
            if (animal instanceof Cat) {
                ((Cat) animal).meowing();
            }
            if (animal instanceof Monkey) {
                ((Monkey) animal).climbing();
            }
            if (animal instanceof Bird) {
                ((Bird) animal).flying();
            }
            if (animal instanceof Reptile) {
                ((Reptile) animal).crawling();
            }
            
            // Spacing
            System.out.println();
        }

        // Test equals method
        Cat cat2 = new Cat("Milky", 3, "Black", owner2.clone());
        System.out.println("Testing equals method:");
        System.out.println("cat1.equals(cat2): " + cat1.equals(cat2));

        // Part C
        System.out.println("\nCloning an animal with an owner:");
        Cat clonedCat = cat1.clone();
        System.out.println("Original Cat: " + cat1);
        System.out.println("Cloned Cat: " + clonedCat);

        // Change the owner's details of the cloned animal
        clonedCat.getOwner().setName("Shlomo");
        clonedCat.getOwner().setPhoneNumber("972054556677");

        System.out.println("\nAfter changing the cloned cat's owner:");
        System.out.println("Original Cat: " + cat1);
        System.out.println("Cloned Cat: " + clonedCat);
    }
}
