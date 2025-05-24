package qns3_b;
/*
Complete the method 'countUniqueElements' that returns the count of unique
elements of the array.
 public static void main(String[] args) {
        int [] nums = {1, 2,3,4,2,5,6,1,7,8,9,5};
        int uniqueCount = countUniqueElements(nums);
        System.out.println(uniqueCount);
    }
 */
import java.util.HashSet;
import java.util.Set;

public class Count {
    static int countUniqueElements(int[] nums){
        Set<Integer> set = new HashSet<Integer>();
        for (int num : nums) {
            set.add(num);
        }
        return set.size();
    }
    public static void main(String[] args) {
        int [] nums = {1, 2,3,4,2,5,6,1,7,8,9,5};
        int uniqueCount = countUniqueElements(nums);
        System.out.println(uniqueCount);
    }

}
