package lab004;

public class Employee extends Person {
    public double salary;

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public void display() {
        super.display();
        System.out.println("Salary: "+salary+" TK");
    }
}
