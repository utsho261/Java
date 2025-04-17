package lab07;
/*
Create an array of 5 integers.Ask the user to enter an index to
access. Use try-catch to handle
ArrayIndexOutOfBoundsException if the user enters an invalid
index.
 */
import java.util.Scanner;

public class Task2 {
    public static void main(String[] args) {
        int[] arr = {10, 20, 30, 40, 50};
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the index number: ");
        int n = sc.nextInt();
        try {
            System.out.println("Output: "+arr[n-1]);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(e);
        }
    }
}
