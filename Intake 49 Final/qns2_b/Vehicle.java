package qns2_b;
/*
Create a class named Vehicle which has the property named price(double),
vehicle-type(string), speed(double) etc. The value of price, vehicle-type and speed
will be initialized when we create an object of that class. Make 5 objects of
Vehicle class and add them into an ArrayList. Those vehicles whose price is over
150000 taka will be shown in output. Complete the Program.
 */
import java.util.ArrayList;

class Vehicle {
    double price, speed;
    String type;

    Vehicle(double price, String type, double speed) {
        this.price = price;
        this.type = type;
        this.speed = speed;
    }



    public static void main(String[] args) {
        ArrayList<Vehicle> list = new ArrayList<>();
        list.add(new Vehicle(120000, "Bike", 120));
        list.add(new Vehicle(200000, "Car", 150));
        list.add(new Vehicle(170000, "Truck", 100));
        list.add(new Vehicle(140000, "Scooter", 80));
        list.add(new Vehicle(250000, "Bus", 90));

        for (Vehicle v : list) {
            if (v.price > 150000)
                System.out.println("Type: " + v.type + ", Price: " + v.price + ", Speed: " + v.speed);
        }
    }
}
