package implementations;

import advancedoperations.AdvancedMathOperations;

public class AdvancedMath implements AdvancedMathOperations {
    public void Subtract(int a, int b) {
        System.out.println("Subtract: " + (a - b));
    }

    public void Power(int base, int exponent) {
        System.out.println("Power: " + Math.pow(base, exponent));
    }

    public void Add(int a, int b) {
        System.out.println("Add: " + (a + b));
    }

    public void Divide(int a, int b) {
        if (b == 0) {
            System.out.println("b = 0");
        } else {
            int x = a / b;
            System.out.println("Divide: " + x);
        }
    }
}
