package qns3_a;

import java.util.*;

/*
Create a class named Book. Write a program to insert 5 Book objects in a
Stack list.
Now take user input for a variable named "option". If option is 1 then insert
another Book object. If option is 2, delete the top Book object from the stack list.
If option is 3, just output the top Book object. Use proper stack list methods.
 */
public class Book {
    private String title;

    public Book(String title) {
        this.title = title;
    }
    public String toString() {
        return title;
    }

    public static void main(String[] args) {
        Stack<Book> books = new Stack<Book>();
        books.push(new Book("Java"));
        books.push(new Book("Python"));
        books.push(new Book("JavaScript"));
        books.push(new Book("C++"));
        books.push(new Book("DBMS"));

        Scanner sc = new Scanner(System.in);
        System.out.println("1. Insert Book");
        System.out.println("2. Delete Book");
        System.out.println("3. Display Book");
        System.out.println("Enter your choice: ");
        int option = sc.nextInt();

        if (option == 1) {
            books.push(new Book("Machine Learning"));
        }
        else if (option == 2) {
            books.pop();
        }
        else if (option == 3) {
            System.out.println(books.peek());
        }
//        for (Book b : books) {
//            System.out.println(b);
//        }
    }
}
