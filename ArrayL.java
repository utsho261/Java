package lab10;

import java.util.ArrayList;
import java.util.List;

public class ArrayL {
    public static void main(String[] args) {
        // Creating the list
        List<Integer> utsho = new ArrayList<>();
        utsho.add(1); // add
        utsho.add(2);
        utsho.add(3);
        utsho.add(4);
        utsho.add(5);
        System.out.println("Original list: " + utsho);

        // add with index
        utsho.add(2, 99); // Insert 99 at index 2
        System.out.println("After adding 99 at index 2: " + utsho);

        // remove
        utsho.remove(3); // Remove element 3
        System.out.println("After removing 3: " + utsho);

        // contains
        System.out.println("List contains 4? " + utsho.contains(4));
        System.out.println("List contains 100? " + utsho.contains(100));

        // equals
        List<Integer> anotherList = new ArrayList<>();
        anotherList.add(1);
        anotherList.add(2);
        anotherList.add(99);
        anotherList.add(4);
        anotherList.add(5);
        System.out.println("utsho equals anotherList? " + utsho.equals(anotherList));

        // set
        utsho.set(1, 88); // Set index 1 to 88
        System.out.println("After setting index 1 to 88: " + utsho);

        // subList
        System.out.println("Sublist from index 0 to 3: " + utsho.subList(0, 3));

        // clear
        utsho.clear();
        System.out.println("After clearing the list: " + utsho);
    }
}
