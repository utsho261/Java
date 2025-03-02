package lab02;

public class Student {
    String name;
    int age;
    String StudentId;
    double grade;

    public Student(int age, double grade, String name, String studentId) {
        this.age = age;
        this.grade = grade;
        this.name = name;
        this.StudentId = studentId;
    }

    void display() {
        System.out.println("Name: "+name);
        System.out.println("Age: "+age);
        System.out.println("StudentId: "+StudentId);
        System.out.println("Grade: "+grade);
    }

    void checkPassFail() {
        if (grade >= 50) {
            System.out.println("Pass");
        }
        else {
            System.out.println("Fail");
        }
    }
}
