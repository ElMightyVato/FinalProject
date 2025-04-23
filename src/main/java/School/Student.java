package School;

import java.util.ArrayList;

public class Student extends Person {
    private int studentId;
    private int grade;
    private ArrayList<Course> courses;

    public Student(String name, int age, String email, int grade, int studentId) {
        super(name, age, email);
        this.grade = grade;
        this.studentId = studentId;
        this.courses = new ArrayList<>();
    }

    public boolean addCourse(Course course) {
        if (courses.size() >= 5) {
            System.out.println("Only 5 courses may be assigned.");
            return false;
        } else if (courses.contains(course)) {
            System.out.println("Courses already added.");
            return false;
        } else {
            courses.add(course);
            return true;
        }
    }

    public ArrayList<Course> getCourses() {
        return courses;
    }

    public int getStudentId() {
        return studentId;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "Student ID: " + studentId + ", Name: " + getName() + ", Grade: " + grade;
    }
}