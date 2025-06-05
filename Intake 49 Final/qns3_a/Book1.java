package qns3_a;

import java.util.ArrayList;
import java.util.Scanner;

/*
Create a class named Book. Write a program to insert 5 Book objects in a
Array list.
Now take user input for a variable named "option". If option is 1 then insert
another Book object. If option is 2, delete the top Book object from the Array list.
If option is 3, just output the top Book object. Use proper Array list methods.
 */
public class Book1 {
    private String title;

    public Book1(String title) {
        this.title = title;
    }


    public static void main(String[] args) {
        ArrayList<Book1> books = new ArrayList<>();
        books.add(new Book1("Java"));
        books.add(new Book1("Python"));
        books.add(new Book1("JavaScript"));
        books.add(new Book1("C++"));
        books.add(new Book1("DBMS"));

        Scanner sc = new Scanner(System.in);
        System.out.println("1. Insert Book");
        System.out.println("2. Delete Book");
        System.out.println("3. Display Book");
        System.out.println("Enter your choice: ");
        int option = sc.nextInt();

        if (option == 1) {
            books.add(new Book1("Machine Learning"));
        }
        else if (option == 2) {
            books.remove(books.size() - 1);
        }
        else if (option == 3) {
            System.out.println(books.get(books.size() - 1).title);
        }
//        for (Book b : books) {
//            System.out.println(b);
//        }
    }
}
