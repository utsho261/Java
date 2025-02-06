package lab03;
/*
Create a Student class with the following attributes (private):
 String name
 int studentId
 double GPA
- Implement getter and setter methods for all attributes.
- In the Main class, create a few Student objects and modify their details using setters.
- Print the student details using getters.
 */
public class Main {
    public static void main(String[] args) {
        Student student1 = new Student();

        student1.setName("Utsho");
        student1.setStudentId(261);
        student1.setGpa(3.79);
        System.out.println("Name: "+student1.getName());
        System.out.println("Student ID: "+student1.getStudentId());
        System.out.println("GPA: "+student1.getGpa());
    }
}
