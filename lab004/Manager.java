package lab004;

public class Manager extends Employee {
    public String department;

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void display() {
        super.display();
        System.out.println("Department: "+department);
    }
}
