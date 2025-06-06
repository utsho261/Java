package qns3_b;
/*
You have a list that consists of integers. You have to remove all the repeating integers
from the List. Example:
Input - [1, 2, 2, 3, 3, 3, 4, 4]
Output - [1, 2, 3, 4]
 */
import java.util.*;

public class RemoveDuplicates {
    public static void main(String[] args) {
        List<Integer> list = List.of(1,2,2,3,3,3,4,4);
        Set<Integer> set = new HashSet<>(list);
        System.out.println(set);
    }
}
