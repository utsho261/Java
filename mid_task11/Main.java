package mid_task11;
/*
Implement a class Car that has a static variable count and a static
method getCount()
 */
public class Main {
    public static void main(String[] args) {
        Car car1 = new Car();
        Car car2 = new Car();
        Car car3 = new Car();
        Car car4 = new Car();

        System.out.println(Car.getCount());
    }
}
