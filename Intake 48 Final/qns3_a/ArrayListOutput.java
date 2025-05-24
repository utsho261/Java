package qns3_a;
/*
Write the output of the given code-
 */
import java.util.ArrayList;
import java.util.List;

public class ArrayListOutput {
    public static void main(String[] args) {
        List<String> colors = new ArrayList<>();
        colors.add("Red");
        colors.add("Green");
        colors.add("Blue");
        colors.add("Blue");
        colors.add("Yellow");

        System.out.println(colors.set(4, "Orange"));
        System.out.println(colors.indexOf("Purple"));
        colors.remove("Blue");
        System.out.println(colors.get(2));
        System.out.println(colors.contains("Blue"));
        System.out.println(colors.subList(1, 3));

    }
}
/*
Output:
    Yellow
    -1
    Blue
    true
    [Green, Blu
 */
