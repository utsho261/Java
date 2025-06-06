package qns4_a;
/*
Given a List of integers, generate the maximum and the minimum integers of that list,
 */
import java.util.Collections;
import java.util.List;

public class MaxMinFinder {
    public static void main(String[] args) {
        List<Integer> list = List.of(1,2,3,4,5,6,7,8,9,10);
        int max = Collections.max(list);
        int min = Collections.min(list);
        System.out.println("Max: " + max);
        System.out.println("Min: " + min);
    }
}
