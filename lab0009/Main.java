package lab0009;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<Book>bookArrayList = new ArrayList<>();
        Book book = new Book();
        Book book1 = new Book();

        book.setTitle("Head First Java");
        book.setAuthor("Kathy Sierra");
        book.setPrice(500.50);
        bookArrayList.add(book);

        book1.setTitle("Java Concurrency in Practice");
        book1.setAuthor("Brian Goetz ");
        book1.setPrice(450.50);
        bookArrayList.add(book1);


        for(Book b : bookArrayList){
            System.out.println("Title: "+b.getTitle());
            System.out.println("Author: "+b.getAuthor());
            System.out.println("Price: "+b.getPrice()+" Tk");
            System.out.println();
        }

    }
}
