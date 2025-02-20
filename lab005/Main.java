package lab005;
/*
- Create a base class Shape with attributes color and a constructor.
- Derive Circle and Rectangle classes that call the Shape constructor using super.
- Demonstrate object creation and constructor execution.
 */
public class Main {
    public static void main(String[] args) {
        Circle circle = new Circle("Red");
        Rectangle rectangle = new Rectangle("Blue");
        System.out.println("Circle Color: "+circle.getColor());
        System.out.println("Rectangle Color: "+rectangle.getColor());
    }
}
