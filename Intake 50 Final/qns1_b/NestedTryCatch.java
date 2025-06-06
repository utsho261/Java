package qns1_b;
/*
Consider a scenario where you might want to catch an Exception inside a try block.
Now generate a code for the nested try-catch.  */
public class NestedTryCatch {
    public static void main(String[] args) {
        try {
            System.out.println("Outer try block");
            try {
                System.out.println("Inner try block");
            }catch(Exception e) {
                System.out.println("Inner catch block");
            }
        }catch (Exception e) {
            System.out.println("Outer catch block");
        }
    }
}
