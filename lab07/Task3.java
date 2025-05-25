package lab07;

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
