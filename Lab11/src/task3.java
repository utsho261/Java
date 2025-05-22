/*
Input: A string
Task: Determine if all 5 vowels are present in the string (a, e, i, o, u).
Example: &quot;education&quot; → All vowels present.
 */
public class task3 {
    public static void main(String[] args) {
        String s ="education";
        int count = 0;
        for(int i=0; i<s.length(); i++){
            if(s.charAt(i)== 'a' || s.charAt(i) == 'e' | s.charAt(i) == 'i' | s.charAt(i) == 'o' | s.charAt(i) == 'u')
            {
                count++;
            }
        }
        if(count>=5) {
            System.out.println("All vowels present.");
        }
        else
            System.out.println("All vowels not present. ");
    }
}
