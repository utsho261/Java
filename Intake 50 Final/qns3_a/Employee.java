package qns3_a;
/*
An employee could save any of his two values. For example, he can save his name or
id. Or he could save his height and job description. Now generate a class called
Employee that can save any two types of values.
 */
public class Employee <T, U>{
    T value1;
    U value2;
    public Employee(T value1, U value2) {
        this.value1 = value1;
        this.value2 = value2;
    }
    void Display(){
        System.out.println(value1 + " " + value2);
    }

    public static void main(String[] args) {
        Employee<String, Integer> emp1 = new Employee<>("Utsho",261);
        Employee<Double, String> emp2 = new Employee<>(5.6,"Developer");
        emp1.Display();
        emp2.Display();
    }

}
