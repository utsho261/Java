package qns2_b;
/*
A data annotator is inputting everyones name through a software. But for some reason,
he is facing some issues. An unwanted character is getting inside the name. For example,
when he is inputting "Chandra Mukhi", the software is showing the as "Chan---dra
M-u--kh-i". The unwanted character is *". Now generate a function where it will take a
String as input, remove these unwanted characters and return the noiseless String.
 */
public class ChandraMukhi {
    public static void main(String[] args) {
        String s = "Chan---dra M-u--kh-i";
        String s1="";
        for (int i = 0; i < s.length(); i++) {
            if(s.charAt(i) != '-')
                s1+=s.charAt(i);
        }
        System.out.println(s1);
    }
}
