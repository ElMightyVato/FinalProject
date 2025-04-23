package School;

import java.sql.*;

public class TeacherDAO {

    // Constructor
    public TeacherDAO() {
        createTeacherTable();
    }

    // Create the teachers table if it doesn't exist
    private void createTeacherTable() {
        String sql = "CREATE TABLE IF NOT EXISTS teachers (" +
                "teacher_id INT PRIMARY KEY, " +
                "name VARCHAR(255), " +
                "age INT, " +
                "email VARCHAR(255), " +
                "subject VARCHAR(255))";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Teachers table created or already exists.");
        } catch (SQLException e) {
            System.out.println("Error creating teachers table: " + e.getMessage());
        }
    }

    // Insert a new teacher into the database
    public void insertTeacher(Teacher teacher) {
        String sql = "INSERT INTO teachers (teacher_id, name, age, email, subject) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, teacher.getTeacherId());
            statement.setString(2, teacher.getName());
            statement.setInt(3, teacher.getAge());
            statement.setString(4, teacher.getEmail());
            statement.setString(5, teacher.getSubject());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error inserting teacher: " + e.getMessage());
        }
    }

    // Get a teacher by ID
    public Teacher getTeacherById(int id) {
        Teacher teacher = null;
        String sql = "SELECT * FROM teachers WHERE teacher_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                teacher = new Teacher(
                        resultSet.getString("name"),
                        resultSet.getInt("age"),
                        resultSet.getString("email"),
                        resultSet.getInt("teacher_id"),
                        resultSet.getString("subject")
                );
            }
        } catch (SQLException e) {
            System.out.println("Error fetching teacher: " + e.getMessage());
        }
        return teacher;
    }


}
