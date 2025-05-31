/*
Input: A string
Task: Determine if all 5 vowels are present in the string (a, e, i, o, u).
Example: &quot;education&quot; → All vowels present.
 */
public class task3 {
   public static void main(String[] args) {
        String s = "education";
        Map<Character, Integer> map = new HashMap<>();
        map.put('a', 0);
        map.put('e', 0);
        map.put('i', 0);
        map.put('o', 0);
        map.put('u', 0);
        for(int i = 0; i < s.length(); i++){
            if(map.containsKey(s.charAt(i))){
                map.put(s.charAt(i), map.get(s.charAt(i))+1);
            }
        }
        boolean flag = true;
        for(int count : map.values()){
            if(count == 0){
                flag = false;
                break;
            }
        }

        if(flag) {
            System.out.println("All vowels present.");
        }
        else
            System.out.println("All vowels not present. ");
    }
}
