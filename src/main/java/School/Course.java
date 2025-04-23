package School;

public class Course {
    // Private fields to hold the course name and ID
    private String courseName;
    private int courseId;

    // My Constructor
    public Course(String courseName, int courseId) {
        this.courseName = courseName;
        this.courseId = courseId;
    }

    // Getters and Setters
    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    @Override
    public String toString() {
        return courseName + " (ID: " + courseId + ")";
    }
}

