package lab004;
/*
Task 2: Multilevel Inheritance
Objective: Implement multilevel inheritance and observe method inheritance.
Task:
1. Create a base class Person with:
    name and age attributes
    display() method to print name and age
2.Create a subclass Employee that extends Person:
    Add salary attribute
    Override display() to also print salary
3. Create another subclass Manager that extends Employee:
    Add department attribute
    Override display() to print all details
4. In the Main class:
    Create a Manager object and set attributes
    Call display()
 */

public class Main {
    public static void main(String[] args) {
        Manager manager = new Manager();
        manager.setName("Utsho Roy");
        manager.setAge(22);
        manager.setSalary(50000.00);
        manager.setDepartment("CSE");

        manager.display();
    }
}
