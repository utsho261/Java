package qns2_a;
/*
Imagine you have a bank account. You can deposit and withdraw money from
your account. You should keep in mind that the total amount of money withdrawn
from your account must not exceed the total balance present in your account. If
such a scenario happens, you need to safely execute from the banking system.
Implement the above case in Java with the proper utilization of user-defined
exception mechanisms.
 */
import java.util.Scanner;

class BankAccount {
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
