package lab003;
/*
Create a class Employee with:
 String name
 int employeeId
 double hourlyWage
 double totalHoursWorked
- Implement getter and setter methods.
- Create a method calculateSalary() that returns the total salary (hourlyWage * totalHoursWorked).
- In the Main class, create multiple employees, set their details, and calculate salaries.
 */
public class Main {
    public static void main(String[] args) {
        Employee employee1 = new Employee();
        Employee employee2 = new Employee();

        employee1.setEmployeeId(101);
        employee1.setName("Utsho");
        employee1.setHourlyWage(60.5);
        employee1.setTotalHoursWorked(8.5);

        employee2.setEmployeeId(102);
        employee2.setName("Achol");
        employee2.setHourlyWage(60.0);
        employee2.setTotalHoursWorked(8.0);

        System.out.println("Employee 1:");
        System.out.println("Id: "+employee1.getEmployeeId());
        System.out.println("Name: "+employee1.getName());
        System.out.println("Hourly Wage: "+employee1.getHourlyWage());
        System.out.println("Total Hours Worked: "+employee1.getTotalHoursWorked());
        System.out.println("Salary: "+employee1.calculateSalary()+" TK");

        System.out.println("\nEmployee 2:");
        System.out.println("Id: "+employee2.getEmployeeId());
        System.out.println("Name: "+employee2.getName());
        System.out.println("Hourly Wage: "+employee2.getHourlyWage());
        System.out.println("Total Hours Worked: "+employee2.getTotalHoursWorked());
        System.out.println("Salary: "+employee2.calculateSalary()+" TK");

    }
}
