package task4b;
/*
 There is a top-level interface Appliance that provides a method for
turning an appliance on or off. The KitchenAppliance interface
extends Appliance and adds a method for setting the temperature of
the appliance. The EntertainmentAppliance interface also extends
Appliance and adds a method for adjusting the volume of the
appliance. The class SmartDevice should implement both
KitchenAppliance and EntertainmentAppliance interfaces. This
device can control both types of appliances, adjusting temperature and
volume as required
Create the interfaces Appliance, KitchenAppliance, and
EntertainmentAppliance to represent the functionality described.
Implement the SmartDevice class that handles both the temperature
and volume
 */
public class Main {
    public static void main(String[] args) {
        SmartDevice smartDevice = new SmartDevice();
        smartDevice.applianceOn();
        smartDevice.applianceOff();
        smartDevice.adjustingVolume();
        smartDevice.setTemperature();
    }
}
