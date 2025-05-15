package lab010;

import java.util.ArrayList;
import java.util.Scanner;
/*
Insert and Remove Elements
    ● Create an ArrayList&lt;Integer&gt;.
    ● Add 10 numbers.
    ● Remove the element at index 5.
    ● Insert a number at index 2.
    ● Display the list after each operation.
 */
public class Task1 {
    public static void main(String[] args) {
        ArrayList<Integer> numbers = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the elements: ");
        for(int i=0;i<10; i++) {
            numbers.add(sc.nextInt());
        }
        System.out.println("Array List: "+numbers);

        System.out.println("Remove the element at index 5");
        numbers.remove(5);
        System.out.println("After remove Array List: "+numbers);

        System.out.println("Insert a number at index 2");
        System.out.println("Enter the number: ");
        numbers.add(2,sc.nextInt());
        System.out.println("After Insert Array List: "+numbers);


    }
}
