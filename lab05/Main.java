package lab05;
/*
- Create an interface GPS with a method showLocation().
- Create a class Vehicle, then derive Car from Vehicle, and implement GPS in Car.
- Show how Car can access both Vehicle methods and GPS methods.
 */
public class Main {
    public static void main(String[] args) {
        Car car = new Car();
        car.Brand();
        car.showLocation();
    }
}
