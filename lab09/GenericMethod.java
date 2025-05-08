package lab09;
/*
Write a generic method printList(List&lt;T&gt; list) that prints all elements in a list, regardless
of type.
 */
import java.util.List;
public class GenericMethod {
    public static <T> void printList(List<T>list){
        for (T element : list){
            System.out.println(element + " " );
        }
    }
}
