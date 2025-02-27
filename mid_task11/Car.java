package mid_task11;

public class Car {
    public static int count = 0;

    Car(){
        count++;
    }

    public static int getCount() {
        return count;
    }

}
