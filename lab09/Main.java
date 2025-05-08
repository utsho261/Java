package lab09;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List <Integer> intList = List.of(10, 20, 30, 40, 50);
        List <String> strList = List.of("Utsho", "Roy");

        System.out.println("Integer: ");
        GenericMethod.printList(intList);

        System.out.println();

        System.out.println("String: ");
        GenericMethod.printList(strList);


    }

}
