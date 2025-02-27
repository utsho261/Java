package implementations;
import mathoperations.MathOperations;
public class BasicMath implements MathOperations {
    void Multiply(int a, int b) {
        System.out.println("Multiply: "+(a*b));
    }

    public void Add(int a, int b) {
        System.out.println("Add: " + (a + b));
    }

}
