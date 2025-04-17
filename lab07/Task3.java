package lab07;
/*
Ask the user to enter a number as a string. Convert the string to
an integer using Integer.parseInt(). Handle
NumberFormatException if the input is not a valid number.
 */
import java.util.Scanner;

public class Task3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the String: ");
        String s = sc.nextLine();

        try {
            int result = Integer.parseInt(s);
            System.out.println("Result: "+result);
        } catch (NumberFormatException e) {
            System.out.println(e);
        }

    }
}
