package Lab01;

/*
Understand loops (for and while).
Task:
* Write a program to print all even numbers between 1 and 20 using a for loop.
* Bonus: Use a while loop to print odd numbers in the same range.
 */
public class Task3_While {
    public static void main(String[] args) {
        int a = 1;
        while (a <= 20) {
            if (a % 2 != 0) {
                System.out.println(a);
            }
            a++;
        }
    }
}
