package implementations;
/*
In package mathoperations, there is an interface MathOperations
with an abstract method Add(int a, int b) that performs addition.
In package advancedoperations, create another interface
AdvancedMathOperations that extends MathOperations and has
two abstract methods:
Subtract(int a, int b) (performs subtraction) and Power(int base, int
exponent) (raises the base to the power of the exponent).
In package implementations, create a class BasicMath that
implements the MathOperations interface,
BasicMath must have a concrete method Multiply(int a, int b) that
performs multiplication.
In the same package, create another class AdvancedMath that
implements the AdvancedMathOperations
interface.AdvancedMath must also implement a method Divide(int
a, int b) that performs integer division and checks for division by
zero. Implement the code for the given scenario.
 */
public class Main {
    public static void main(String[] args) {
        AdvancedMath advancedMath = new AdvancedMath();
        advancedMath.Add(10,20);
        advancedMath.Subtract(10,20);
        advancedMath.Power(5,2);
        advancedMath.Divide(10,2);
        advancedMath.Divide(10,0);
    }
}
