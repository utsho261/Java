package qns2_a;
/*
Write a function that takes a string as input and returns the string in the reversed form.
(You cannot use the reverse) method of StringBuilder. )
For example:
    Input: "hello"
    Output: "olleh"

 */
public class reverseString {
    public static void main(String[] args) {
        String s = "hello";
        String rev = "";
        for (int i = s.length() - 1; i >= 0; i--) {
            rev += s.charAt(i);
        }
        System.out.println(rev);
    }
}
