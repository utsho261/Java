package Lab02;

import java.util.Scanner;

/*
Check Prime Number: Write a program that checks whether a given number
is a prime number.
 */
public class Task1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();

        if (num <= 1) {
            System.out.println("Not Prime Number");
        } else {
            int count = 0;
            for (int i = 2; i <= num / 2; i++) {
                if (num % i == 0) {
                    count = 1;
                    break;
                }
            }

            if (count == 0) {
                System.out.println("Prime Number");
            } else {
                System.out.println("Not Prime Number");
            }
        }
    }
}
