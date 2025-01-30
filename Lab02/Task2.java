package Lab02;

import java.util.Scanner;

/*
Check a number is palindrome or not.
 */
public class Task2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        int x=num;
        int rev = 0;
        while (num != 0) {

            int digit = num % 10;

            rev = rev * 10 + digit;

            num = num / 10;
        }
        if (x == rev) {
            System.out.println("Number is Palindrome");
        } else {
            System.out.println("Number is not Palindrome");
        }

    }
}
