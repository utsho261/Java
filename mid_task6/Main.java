package mid_task6;
/*
Create a base class called shape which has two double type properties
and a function setdata() which is used to set the values of those two
properties. There should be another member function display_area()
in shape to compute and display the area of figures.
Derive two specific classes called triangle and rectangle from the
base shape. Use these three classes that implements the idea of
dynamic method dispatch
 */
public class Main {
    public static void main(String[] args) {
        Triangle triangle = new Triangle();
        triangle.setdata(10,20);
        triangle.display_area();

        Rectangle rectangle = new Rectangle();
        rectangle.setdata(10,20);
        rectangle.display_area();
    }
}
