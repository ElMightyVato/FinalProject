package School;

import java.util.ArrayList;

public class Teacher extends Person {
    private int teacherId;
    private String subject;
    private ArrayList<Course> courses;

    public Teacher(String name, int age, String email, int teacherId, String subject) {
        super(name, age, email);
        this.teacherId = teacherId;
        this.subject = subject;
        this.courses = new ArrayList<>();
    }

    public boolean addCourse(Course course) {
        if (courses.size() >= 3) {
            System.out.println("Only 3 courses may be taught at a time.");
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

    public int getTeacherId() {
        return teacherId;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    @Override
    public String toString() {
        return "Teacher ID: " + teacherId + ", Name: " + getName() + ", Subject: " + subject;
    }
}
