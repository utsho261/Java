/*
Input: A string
Task: Count occurrences of each character. Ignore spaces.
Use: Map&lt;Character, Integer&gt;
 */
import java.util.HashMap;
import java.util.Map;

public class task2 {
    public static void main(String[] args) {
        String s = "A string";
        Map<Character, Integer> Char = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            if(s.charAt(i) != ' '){
                if (Char.containsKey(s.charAt(i))) {
                    Char.put(s.charAt(i), Char.get(s.charAt(i)) + 1);
                } else
                    Char.put(s.charAt(i), 1);
            }


        }
        System.out.println(Char);
    }
}