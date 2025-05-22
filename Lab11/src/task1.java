/*
Input: A string of words.
Task: Count how many times each word appears using a HashMap.
Example: &quot;this is a test this&quot; → {this=2, is=1, a=1, test=1}
 */
import java.util.HashMap;
import java.util.Map;

public class task1 {
    public static void main(String[] args) {
        String s = "this is a test this";
        Map<String,Integer> words = new HashMap<>();
        for(String w : s.split(" ")){
            if(words.containsKey(w)){
                words.put(w,words.get(w)+1);
            }
            else
                words.put(w,1);

        }
        System.out.println(words);
    }
}
