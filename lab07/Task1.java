package lab07;
/*
Write a program that takes two integers from the user and divides
them. Handle the case where the denominator is zero
(ArithmeticException).
 */
import java.util.Scanner;

public class Task1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the Number 1: ");
        int num1= sc.nextInt();
        System.out.println("Enter the Number 1: ");
        int num2= sc.nextInt();

        try {
            int a = num1/num2;
            System.out.println("Result: "+a);
        } catch (ArithmeticException e) {
            System.out.println(e);
        }
    }
}
