package task4a;
/*
Create a class named ElectricCar that both extends an abstract
class and implements two interfaces. Specifically: The abstract class
Vehicle should contain abstract methods start() and stop(). The first
interface Electric should define a method chargeBattery(). The
second interface Autonomous should define a method
enableAutoPilot()
 */
public class Main {
    public static void main(String[] args) {
        ElectricCar electricCar = new ElectricCar();
        electricCar.chargeBattery();
        electricCar.start();
        electricCar.stop();

    }
}
