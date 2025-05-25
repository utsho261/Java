package lab07;
/*
Take a number as input for withdrawing the money from the bank.
If the number is greater than deposit money, then throw an object
of class InsufficientMoneyException. Create custom exception
class InsufficientMoneyException.*/

import java.util.Scanner;

public class Task3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int deposit = 50000;
        System.out.println("Enter withdrawal amount: ");
        int withdrawal = sc.nextInt();
        try {
            if(withdrawal > deposit) {
                throw new InsufficientMoneyException();
            }
            else
                System.out.println("New balance: "+(deposit - withdrawal));
        }catch(InsufficientMoneyException e) {
            System.out.println(e);

        }

    }
}
