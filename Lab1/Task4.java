package Task4;

import java.util.Scanner;

public class Task4 {
    public static void main(String[] args)
    {
        int[] arr=new int[5];
        Scanner sc=new Scanner(System.in);
        for(int i=0;i<5;i++)
        {
            arr[i]= sc.nextInt();
        }
        for (int i=0;i<5;i++)
        {
            System.out.println(arr[i]);
        }


    }
}
