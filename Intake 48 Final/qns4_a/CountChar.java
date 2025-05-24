package qns4_a;
/*
Write a Java program that takes a string as input and prints the count of each character
in the string using a HashMap.
For example:
    Input: "Java"
    Output: {a - 2, v - 1,J - 1}

 */
import java.util.HashMap;
import java.util.Map;

public class CountChar {
    public static void countCharacters(String input) {
        Map<Character, Integer> map = new HashMap<>();
        for (char c : input.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        System.out.println(map);
    }

    public static void main(String[] args) {
        String s = "Java";
        countCharacters(s);
    }
}
