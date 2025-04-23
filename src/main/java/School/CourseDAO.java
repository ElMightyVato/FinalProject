package School;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CourseDAO {

    // Constructor
    public CourseDAO() {
        createCourseTable(); // Calling method to create the table
    }

    // Create the courses table if it doesn't exist
    private void createCourseTable() {
        // My SQL query below
        String sql = "CREATE TABLE IF NOT EXISTS courses (" +
                "course_id INT PRIMARY KEY, " +
                "course_name VARCHAR(255))";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Courses table created or already exists.");
        } catch (SQLException e) {
            System.out.println("Error creating courses table: " + e.getMessage());
        }
    }

    // Insert a new course into the database
    public void insertCourse(Course course) {
        // Query to insert a new course
        String sql = "INSERT INTO courses (course_id, course_name) VALUES (?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, course.getCourseId());
            statement.setString(2, course.getCourseName());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error inserting course: " + e.getMessage());
        }
    }

    // Get a course by ID
    public Course getCourseById(int id) {
        Course course = null;
        String sql = "SELECT * FROM courses WHERE course_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                course = new Course(
                        resultSet.getString("course_name"),
                        resultSet.getInt("course_id")
                );
            }
        } catch (SQLException e) {
            System.out.println("Error fetching course: " + e.getMessage());
        }
        return course;
    }

    // Get all courses
    public List<Course> getAllCourses() {
        List<Course> courses = new ArrayList<>();
        String sql = "SELECT * FROM courses";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement statement = conn.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                Course course = new Course(
                        resultSet.getString("course_name"),
                        resultSet.getInt("course_id")
                );
                courses.add(course);
            }
        } catch (SQLException e) {
            System.out.println("Error fetching all courses: " + e.getMessage());
        }
        return courses;
    }
}
