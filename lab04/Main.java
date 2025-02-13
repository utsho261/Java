package lab04;
/*
Task 1: Single Inheritance
Objective: Understand how one class can inherit properties from another.
Task:
1. Create a base class Animal with:
    A String attribute name
    A method makeSound() that prints "Animal makes a sound"
2. Create a derived class Dog that inherits from Animal and:
    Overrides the makeSound() method to print "Dog barks"
3. Create a Main class where you:
    Instantiate a Dog object
    Call the makeSound() method
 */

public class Main {
    public static void main(String[] args) {
        Dog dog = new Dog();
        dog.makeSound();

    }
}
