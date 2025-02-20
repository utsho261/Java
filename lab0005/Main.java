package lab0005;
/*
Design a system where an interface Animal defines general behaviors of animals, and another
interface Pet extends Animal to add pet-specific behaviors. Implement these interfaces in a
class Dog, which must provide implementations for all inherited methods.
Requirements:
1. Create an interface Animal with the following methods:
○ void eat();
○ void sleep();
2. Create another interface Pet that extends Animal and adds the following method:
○ void play();
3. Create a class Dog that implements Pet and provides implementations for all inherited
methods.
4. In the main method, create an instance of Dog and call all three methods (eat(), sleep(),
and play()).
 */
public class Main {
    public static void main(String[] args) {
        Dog dog = new Dog();
        dog.eat();
        dog.sleep();
        dog.play();
    }
}
