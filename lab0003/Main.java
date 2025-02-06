package lab0003;
/*
Create a class Vehicle with the following private attributes:
o String vehicleType (e.g., &quot;Car&quot;, &quot;Bike&quot;, &quot;Truck&quot;)
o String licensePlate
o double hourlyRate
o double hoursRented
- Implement getter and setter methods for all attributes.
- Implement a method calculateRentalCost() that returns the total rental cost (hourlyRate *
hoursRented).
- In the Main class, create multiple Vehicle objects, set their details, and calculate their rental
costs.
 */
public class Main {
    public static void main(String[] args) {
        Vehicle Car = new Vehicle();
        Vehicle Bike = new Vehicle();
        Vehicle Truck = new Vehicle();

        Car.setVehicleType("Car");
        Car.setLicensePlate("ABC-01");
        Car.setHourlyRate(200.50);
        Car.setHoursRented(2.5);

        Bike.setVehicleType("Bike");
        Bike.setLicensePlate("ABC-02");
        Bike.setHourlyRate(150.50);
        Bike.setHoursRented(2.0);

        Truck.setVehicleType("Truck");
        Truck.setLicensePlate("ABC-03");
        Truck.setHourlyRate(300.50);
        Truck.setHoursRented(5.5);

        System.out.println("Vehicle Type: "+Car.getVehicleType());
        System.out.println("License Plate: "+Car.getLicensePlate());
        System.out.println("Hourly Rate: "+Car.getHourlyRate()+" TK");
        System.out.println("Hours Rented: "+Car.getHoursRented());
        System.out.println("Rental Cost: "+Car.calculateRentalCost()+" TK");

        System.out.println("\nVehicle Type: "+Bike.getVehicleType());
        System.out.println("License Plate: "+Bike.getLicensePlate());
        System.out.println("Hourly Rate: "+Bike.getHourlyRate()+" TK");
        System.out.println("Hours Rented: "+Bike.getHoursRented());
        System.out.println("Rental Cost: "+Bike.calculateRentalCost()+" TK");

        System.out.println("\nVehicle Type: "+Truck.getVehicleType());
        System.out.println("License Plate: "+Truck.getLicensePlate());
        System.out.println("Hourly Rate: "+Truck.getHourlyRate()+" TK");
        System.out.println("Hours Rented: "+Truck.getHoursRented());
        System.out.println("Rental Cost: "+Truck.calculateRentalCost()+" TK");
    }
}
