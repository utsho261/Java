package mid_task5;
/*
Create an abstract class named Store. Store will have one
non-abstract function showItem() and two abstract functions
get_price() and display_info(). Create two derived classes Bookstore
and Ricestore. In the main() function, design Store class in such a
way that implements the idea of abstraction.
 */
public class Main {
    public static void main(String[] args) {
        Bookstore store1 = new Bookstore();
        store1.get_price();
        store1.display_info();

        Ricestore store2 = new Ricestore();
        store2.get_price();
        store2.display_info();
    }
}
