package qns3_b;
/*
There are two strings given:
S1 = "Happy New Year"
S2 = "Change is the only constant in life"
Now extract "New" from SI and "life" from S2. Add these two words to make a
sentence. Capitalize this sentence and find the last occurrence of "E' from the
sentence. Perform all of these tasks using proper String class functions.
 */
public class Main {
    public static void main(String[] args) {
        String s1 = "Happy New Year";
        String s2 = "Change is the only constant in life";

        String word1 = s1.substring(6, 9);
        String word2 = s2.substring(s2.lastIndexOf(" ") + 1);

        String sentence = (word1 + " " + word2).toUpperCase();
        int lastE = sentence.lastIndexOf("E");
        System.out.println(sentence);
        System.out.println("Last 'E' at index: " + lastE);
    }
}
