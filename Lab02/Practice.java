package Lab02;
/*
Create a class Student with the following attributes:
● name (String)
● age (int)
● studentID (String)
● grade (double)
The class should have:
1. Methods to:
○ Display the student&#39;s information (displayInfo).
○ A method to check if the student passed or failed (consider 50 as the passing
grade).

Then, create an object of the Student class in another class called “Practice” in main method,
initialize it with some data, and display the student&#39;s information check whether they have failed
or not.
 */
public class Practice {
    public static void main(String[] args) {
        Student student = new Student();
        student.setName("Utsho Roy");
        student.setage(23);
        student.setstudentId("22235103261");
        student.setgrade(50);

        student.displayInfo();

        student.checkPassFail();
    }

}
