package lab010;
/*
Populate an ArrayList&lt;Integer&gt;. Now find the maximum and minimum of the list.
 */
import java.util.ArrayList;
import java.util.Scanner;

public class task3 {
    public static void main(String[] args) {
        ArrayList<Integer>numbers = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter number of elements: ");
        int n = sc.nextInt();
        System.out.println("Enter the numbers: ");
        for(int i=0; i<n; i++) {
            numbers.add(sc.nextInt());
        }

        int max = numbers.get(0);
        int min = numbers.get(0);
        System.out.println("List: "+numbers);

        for(int num : numbers){
            if(num > max)
                max = num;
        }

        for(int num : numbers){
            if(num < min)
                min = num;
        }
        System.out.println("Max: "+max);
        System.out.println("Min: "+min);


    }
}
