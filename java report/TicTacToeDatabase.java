import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TicTacToeDatabase {
    private static final String URL = "jdbc:mysql://localhost:3306/tictactoe_db"; // Adjust for your DB name
    private static final String USER = "root"; // DB username
    private static final String PASSWORD = ""; // DB password, if any

    // Load MySQL driver and get connection to the database
    public static Connection getConnection() {
        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            System.out.println("MySQL JDBC Driver not found.");
            e.printStackTrace();
            return null;
        } catch (SQLException e) {
            System.out.println("Connection Failed!");
            e.printStackTrace();
            return null;
        }
    }

    // Method to create the games table
    public static void createGamesTable() {
        String createGamesTable = "CREATE TABLE IF NOT EXISTS games (" +
                                  "id INT PRIMARY KEY AUTO_INCREMENT, " +
                                  "player_x VARCHAR(100) NOT NULL, " +
                                  "player_o VARCHAR(100) NOT NULL, " +
                                  "winner VARCHAR(100), " +
                                  "timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +
                                  ");";

        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(createGamesTable)) {
            if (conn != null) {
                stmt.executeUpdate();
                System.out.println("Games table created or already exists.");
            } else {
                System.out.println("Failed to create table. Connection is null.");
            }
        } catch (SQLException e) {
            System.out.println("Error creating games table.");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        createGamesTable();
    }
}
