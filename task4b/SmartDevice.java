package task4b;

public class SmartDevice implements KitchenAppliance, EntertainmentAppliance {
    public void applianceOn() {
        System.out.println("Appliance On......");
    }

    public void applianceOff() {
        System.out.println("Appliance Off......");
    }

    public void adjustingVolume() {
        System.out.println("Adjusting Volume......");
    }

    public void setTemperature() {
        System.out.println("Setting Temperature......");
    }
}
