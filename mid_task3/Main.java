package mid_task3;
/*
Car and Bus are different types of vehicles. Car has its property
speed and no_ of passengers. The Bus also has the same properties.
Both the vehicle's speed depends on its engine and passengers depend
on number of sits given when a Car or a Bus object is created. You
are assigned to the task of comparing the two types of vehicles by
their speed.

Now translate the scenario to a java program, by creating the classes
and comparing the speed of two objects of the classes Bus and Car.
 */
public class Main {
    public static void main(String[] args) {
        Car car = new Car(100,4);
        Bus bus = new Bus(90,30);

        if (car.speed > bus.speed) {
            System.out.println("Car is faster");
        }
        else {
            System.out.println("Bus is faster");
        }
    }
}
