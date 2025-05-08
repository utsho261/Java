package lab009;
/*
Custom Generic Class
● Create a generic class Box&lt;T&gt; that can store any object and has get() and set()
methods.
● Create instances with Integer, String, and Double.
 */
public class Box <T> {
    public T value;


    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }
}
