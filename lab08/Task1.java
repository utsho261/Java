package lab08;

public class Task1 {
    public static void main(String[] args) {
        String s = "String";
        String s1="";
        for(int i=s.length()-1;i>=0;i--){
            s1+=s.charAt(i);
        }
        System.out.println(s1);
    }
}
