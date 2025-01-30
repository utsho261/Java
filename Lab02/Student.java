package Lab02;

public class Student {
    String name;
    int age;
    String studentId;
    double grade;

    void setName(String name) {
        this.name = name;
    }

    String getname() {
        return name;
    }

    void setage(int age) {
        this.age = age;
    }

    int getage() {
        return age;
    }

    void setstudentId(String studentId) {
        this.studentId = studentId;
    }

    String getstudentId() {
        return studentId;
    }

    void setgrade(double grade) {
        this.grade = grade;
    }

    double getgrade() {
        return grade;
    }

    void displayInfo() {
        System.out.println("Student Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Student ID: " + studentId);
        System.out.println("Grade: " + grade);
    }


    void checkPassFail() {
        if (grade >= 50) {
            System.out.println("Pass");
        } else {
            System.out.println("Fail");
        }

    }
}
