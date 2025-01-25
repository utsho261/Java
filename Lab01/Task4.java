package Lab01;

/*
Introduce arrays and loops.
Task:
* Create an array of 5 integers.
* Use a loop to take input for the array.
* Find the sum and average of the array elements.
 */

import java.util.Scanner;

public class Task4 {
    public static void main(String[] args) {
        int[] arr = new int[5];
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter 5 numbers: ");
        for (int i = 0; i < 5; i++) {
            arr[i] = sc.nextInt();
        }
        for (int i = 0; i < 5; i++) {
            System.out.println(arr[i]);
        }
        int sum = 0;
        for (int i = 0; i < 5; i++) {
            sum += arr[i];
        }

        System.out.println("Sum: "+sum);
        System.out.println("Average: "+(sum/5));


    }
}
