package Task2;

import java.util.Scanner;

public class Task2 {
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter a: ");
        int a = sc.nextInt();

        if(a==0)
        {
            System.out.println("Zero");
        }
        else if(a>0)
        {
            System.out.println("Positive");
        }
        else
        {
            System.out.println("Negative");
        }

    }
}
