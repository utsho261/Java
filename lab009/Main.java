package lab009;

public class Main {
    public static void main(String[] args) {
        Box<Integer>integerBox = new Box<>();
        Box<String >stringBox = new Box<>();
        Box<Double>doubleBox = new Box<>();

        integerBox.setValue(100);
        System.out.println("Integer: "+integerBox.getValue());

        stringBox.setValue("Utsho Roy");
        System.out.println("String: "+stringBox.getValue());

        doubleBox.setValue(10.50);
        System.out.println("Double: "+doubleBox.getValue());

    }
}
