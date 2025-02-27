package task4a;

public class ElectricCar extends Vehicle implements Electric{

    public void chargeBattery() {
        System.out.println("Charging Battery......");
    }

    void start() {
        System.out.println("Car starting.....");
    }

    void stop() {
        System.out.println("Car stopped......");
    }
}
