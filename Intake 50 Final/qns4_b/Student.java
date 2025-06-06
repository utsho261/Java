package qns4_b;
/*
 Suppose there are two tables. One table saves the name and the student _id. The other
table saves the student _id and grade. Now generate a function where it takes name as an
input and you have to determine the grade of that student. Solve this problem using map.
 */
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Student {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Map<String, Integer> map1 = new HashMap<>();
        map1.put("Utsho", 261);
        map1.put("Achol", 358);
        map1.put("Tanny", 367);

        Map<Integer, Double> map2 = new HashMap<>();
        map2.put(261, 2.00);
        map2.put(358, 3.00);
        map2.put(367, 4.00);

        System.out.println("Enter student name: ");
        String name = sc.nextLine();
        int Id = map1.get(name);
        double CGPA = map2.get(Id);
        System.out.println("CGPA: " + CGPA);
    }
}

