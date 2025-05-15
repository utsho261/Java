package lab010;
/*
Populate an ArrayList&lt;Integer&gt;. Now find the maximum and minimum of the list.
 */
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class task3 {
    public static void main(String[] args) {
        ArrayList<Integer>numbers = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter number of elements: ");
        int n = sc.nextInt();
        System.out.println("Enter the numbers: ");
        for(int i=0; i<n; i++) {
            numbers.add(sc.nextInt());
        }

        int max = Collections.max(numbers);
        int min = Collections.min(numbers);

        System.out.println("List: "+numbers);
        System.out.println("Max: "+max);
        System.out.println("Min: "+min);


    }
}
