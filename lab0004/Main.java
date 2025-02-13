package lab0004;
/*
Task 3: Using super Keyword
Objective: Understand how super is used to call parent class methods and constructors.
Task:
1. Create a base class Parent with:
    Constructor that prints "Parent class constructor"
    Method show() that prints "Parent method"
2. Create a subclass Child that:
    Has a constructor calling super()
    Overrides show() and calls super.show()
3. In Main class:
    Create a Child object and observe the constructor call
    Call show()
 */

public class Main {
    public static void main(String[] args) {
        Child child = new Child();

        child.show();
    }
}
