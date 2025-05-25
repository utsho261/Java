package lab08;

public class Task3 {
    public static void main(String[] args) {
        String s = "apple";
        char ch = 'p';
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ch) {
                count++;
            }
        }
        System.out.println(count);
    }
}
