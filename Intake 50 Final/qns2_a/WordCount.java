package qns2_a;

import java.util.ArrayList;

/*
You are writing a paragraph and you want to know how many words you have written.
Now write one function where it counts the number of words from a given String. You
cannot use any built-in function.
 */
public class WordCount {
    public static void main(String[] args) {
        String s = "I am learning Java";
        int count = 1;
        for (int i = 0; i < s.length(); i++) {
            if(s.charAt(i)==' ')
            {
                count++;
            }

        }
        System.out.println(count);
    }
}
