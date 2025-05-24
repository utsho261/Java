package qns1_b;
/*
What will be the outputs of the following code
 */
public class Main {
    public static void main(String[] args) {
        String s1 = "Java";
        String s2 = new String("Java");
        System.out.println(s1.length());
        System.out.println(s1 + 9 + 9);
        System.out.println(s1.charAt(3));
        System.out.println(s1 == s2);
        System.out.println(s1.compareTo(s2));
    }

}
/*
Output:
        4
        Java99
        a
        false
        0
 */