package lab0003;

public class Vehicle {
    private String vehicleType;
    private String licensePlate;
    private double hourlyRate;
    private double hoursRented;

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public double getHourlyRate() {
        return hourlyRate;
    }

    public void setHourlyRate(double hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    public double getHoursRented() {
        return hoursRented;
    }

    public void setHoursRented(double hoursRented) {
        this.hoursRented = hoursRented;
    }

    public double calculateRentalCost() {
        return hourlyRate*hoursRented;
    }
}
