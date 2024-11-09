/**
 * Program that demonstrates the usage of the GenericSet class.
 */
import java.util.Random;
import java.util.Scanner;

public class GenericSetMain {
    public static void main(String[] args) {
    	
        // Create 3 sets of type Integer
        GenericSet<Integer> set1 = new GenericSet<>();
        GenericSet<Integer> set2 = new GenericSet<>();
        GenericSet<Integer> set3 = new GenericSet<>();

        Random rand = new Random();

        // Fill each set with 10 random values in the range 0..100
        for (int i = 0; i < 10; i++) {
            set1.insert(rand.nextInt(101));
            set2.insert(rand.nextInt(101));
            set3.insert(rand.nextInt(101));
        }

        // Display the contents of the sets
        System.out.println("Set 1: " + set1);
        System.out.println("Set 2: " + set2);
        System.out.println("Set 3: " + set3);

        // Perform a union of the first set with the second set and display the resulting set
        set1.union(set2);
        System.out.println("Set 1 after union with Set 2: " + set1);

        // Perform an intersection of the first set (the union set obtained from the previous operation) with the third set and display the resulting set
        set1.intersect(set3);
        System.out.println("Set 1 after intersection with Set 3: " + set1);

        // Get 2 numbers from the user and create a fourth set
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter first number for Set 4: ");
        int num1 = scanner.nextInt();
        System.out.print("Enter second number for Set 4: ");
        int num2 = scanner.nextInt();

        GenericSet<Integer> set4 = new GenericSet<>();
        set4.insert(num1);
        set4.insert(num2);

        // Check if this set is a subset of one of the other sets and display the result
        if (set1.isSubset(set4)) {
            System.out.println("Set 4 is a subset of Set 1");
        } else if (set2.isSubset(set4)) {
            System.out.println("Set 4 is a subset of Set 2");
        } else if (set3.isSubset(set4)) {
            System.out.println("Set 4 is a subset of Set 3");
        } else {
            System.out.println("Set 4 is not a subset of Set 1, Set 2, or Set 3");
        }

        // Get a number from the user and perform the following actions:
        System.out.print("Enter a number: ");
        int number = scanner.nextInt();

        // Check if it belongs to the first set and display the result
        if (set1.isMember(number)) {
            System.out.println(number + " belongs to Set 1");
        } else {
            System.out.println(number + " does not belong to Set 1");
        }

        // Add it to the second set and display the result after the addition
        set2.insert(number);
        System.out.println("Set 2 after inserting " + number + ": " + set2);

        // Remove it from the third set and display the result after the removal
        set3.delete(number);
        System.out.println("Set 3 after deleting " + number + ": " + set3);

        // Part C: Using Person class and findMin method
        // Create a set for elements of type Person
        GenericSet<Person> personSet = new GenericSet<>();

        // Add 5 objects to it
        personSet.insert(new Person("1234", "Artzi", "Shlomo", 1949));
        personSet.insert(new Person("5678", "Ravitz", "Yehudit", 1956));
        personSet.insert(new Person("8899", "Banai", "Eviatar", 1973));
        personSet.insert(new Person("4682", "Gov", "Gidi", 1950));
        personSet.insert(new Person("1111", "Einstein", "Arik", 1939));

        // Display the set
        System.out.println("Person Set: " + personSet);

        // Invoke the method with the set and display the minimum element returned by the method
        Person minPerson = SetUtilities.findMin(personSet);
        System.out.println("Minimum Person: " + minPerson);

        scanner.close();
    }
}
