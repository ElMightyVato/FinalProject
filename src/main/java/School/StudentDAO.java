package School;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class StudentDAO {

    public StudentDAO() {
        createStudentTable(); // Ensures table is created when DAO is initialized
    }

    private void createStudentTable() {
        String sql = "CREATE TABLE IF NOT EXISTS students (" +
                "id INT PRIMARY KEY, " +
                "name VARCHAR(100), " +
                "age INT, " +
                "email VARCHAR(200), " +
                "grade INT)";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertStudent(Student student) {
        if (student.getGrade() < 1 || student.getGrade() > 12) {
            throw new IllegalArgumentException("Grade must be between 1 and 12.");
        }

        String sql = "INSERT INTO students (id, name, age, email, grade) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, student.getStudentId());
            pstmt.setString(2, student.getName());
            pstmt.setInt(3, student.getAge());
            pstmt.setString(4, student.getEmail());
            pstmt.setInt(5, student.getGrade());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Student getStudentById(int id) {
        String sql = "SELECT * FROM students WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Student(
                        rs.getString("name"),
                        rs.getInt("age"),
                        rs.getString("email"),
                        rs.getInt("grade"),
                        rs.getInt("id")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updateStudent(Student student) {
        if (student.getGrade() < 1 || student.getGrade() > 12) {
            throw new IllegalArgumentException("Grade must be between 1 and 12.");
        }

        String sql = "UPDATE students SET name = ?, age = ?, email = ?, grade = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, student.getName());
            pstmt.setInt(2, student.getAge());
            pstmt.setString(3, student.getEmail());
            pstmt.setInt(4, student.getGrade());
            pstmt.setInt(5, student.getStudentId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteStudent(int id) {
        String sql = "DELETE FROM students WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Student> getAllStudents() {
        ArrayList<Student> list = new ArrayList<>();
        String sql = "SELECT * FROM students";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Student s = new Student(
                        rs.getString("name"),
                        rs.getInt("age"),
                        rs.getString("email"),
                        rs.getInt("grade"),
                        rs.getInt("id")
                );
                list.add(s);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public int getMaxStudentId() {
        String sql = "SELECT MAX(id) as max_id FROM students";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                return rs.getInt("max_id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0; // No Students yet
    }

    // Created this for student input validation and unique IDs
    public void createStudentWithValidation() throws IllegalArgumentException, NumberFormatException {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter student name: ");
        String name = scanner.nextLine();

        System.out.print("Enter student age: ");
        int age = Integer.parseInt(scanner.nextLine());

        System.out.print("Enter student email: ");
        String email = scanner.nextLine();

        int grade;

        while (true) {
            try {
                System.out.print("Enter grade (1-12): ");
                grade = Integer.parseInt(scanner.nextLine());
                if (grade < 1 || grade > 12) {
                    throw new IllegalArgumentException("Grade must be between 1 and 12.");
                }
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid grade. Please enter a number between 1 and 12.");
            }
            }

                int newID = getMaxStudentId() + 1;
        Student student = new Student(name, age, email, grade, newID);
        insertStudent(student);
        System.out.println("Student added with ID: " + newID);
    }
}
