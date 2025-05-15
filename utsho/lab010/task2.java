package lab010;
/*
Create an ArrayList&lt;String&gt; with 5 city names.
Ask the user to input a city name. Don’t use the contains function
 */
import java.util.ArrayList;
import java.util.Scanner;

public class task2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<String> cities = new ArrayList<>();
        cities.add("Dhaka");
        cities.add("Rangpur");
        cities.add("Nilphamari");
        cities.add("Khulna");
        cities.add("Rajshahi");

        System.out.println("Enter a city name: ");
        String c = sc.nextLine();

        boolean found = false;
        for (String city : cities) {
            if (city.equals(c)) {
                found = true;
                break;
            }
        }
        if (found) {
            System.out.println(c + " is in the list");
        } else {
            System.out.println(c + " is in the list");
        }
    }
}
