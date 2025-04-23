package School;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:h2:~/schooldb"; // Database location
    private static final String USER = "ElMightyVato"; // Username
    private static final String PASSWORD = "NeroZero1377@"; // Password

    // Private constructor to prevent instantiation
    private DatabaseConnection() {}

    // Method to get the database connection
    public static Connection getConnection() {
        try {
            // Trying to establish connection utilizing DriverManager
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}

