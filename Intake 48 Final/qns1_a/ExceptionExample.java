package qns1_a;
/*
 What will be the output of the following code? Give proper reasoning.
 */
public class ExceptionExample {
    public static void main(String[] args) {
        try {
            outerTry();
        } catch (Exception e) {
            System.out.println("Exception caught in the main method: " + e);
        }
    }

    public static void outerTry() {
        try {
            innerTry();
        } catch (ArithmeticException e) {
            System.out.println("Exception in the outerTry method: " + e);
        }
    }

    public static void innerTry() {
        try {
            int result = 10 / 0;
            System.out.println("Result: " + result);
        } catch (NullPointerException e) {
            System.out.println("Exception in the innerTry method: " + e);
        }
    }
}
/*
Output:
    Exception in the outerTry method: java.lang.ArithmeticException: / by zero
 */