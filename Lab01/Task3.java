package Lab01;

/*
Understand loops (for and while).
Task:
* Write a program to print all even numbers between 1 and 20 using a for loop.
* Bonus: Use a while loop to print odd numbers in the same range.
 */

public class Task3 {
    public static void main(String[] args) {
        for (int i = 1; i <= 20; i++) {
            if (i % 2 == 0) {
                System.out.println(i);
            }
        }
    }
}
