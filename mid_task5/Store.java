package mid_task5;

abstract class Store {
   void showItem() {
       System.out.println("Showing Item in Store");
   }
   abstract void get_price();
   abstract void display_info();
}
