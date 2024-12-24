
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author mghir
 */
public class dbconnection {
       private static final String HOST = "localhost"; // Change this to your phpMyAdmin host
    private static final int PORT = 3306; // Default MySQL port
    private static final String DB_NAME = "conference"; // Change this to your database name in phpMyAdmin
    private static final String USERNAME = "root"; // Change this to your MySQL username
    private static final String PASSWORD = ""; // Change this to your MySQL password
    private static Connection connection;

    public static Connection getConnection() {
         try {
            // Ensure the MySQL JDBC driver is loaded
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Create the connection URL
            String url = "jdbc:mysql://" + HOST + ":" + PORT + "/" + DB_NAME;

            // Establish the connection
            connection = DriverManager.getConnection(url, USERNAME, PASSWORD);
            System.out.println("Connected to the database.");

            // Execute a SELECT query
            executeSelectQuery();
        } catch (ClassNotFoundException e) {
            System.out.println("MySQL JDBC driver not found: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Connection error: " + e.getMessage());
        }
        return connection;
    }

    private static void executeSelectQuery() throws SQLException {
        // Create a statement
        Statement statement = connection.createStatement();

        // Define your SELECT query
        String query = "SELECT * FROM admin"; // Replace your_table_name with an actual table name

        // Execute the query and get the result set
        ResultSet resultSet = statement.executeQuery(query);

        // Process the result set (print or do something with the data)
        while (resultSet.next()) {
            // Example: Print the data from each row
            System.out.println(resultSet.getString("nom"));
            // Replace column_name with an actual column name from your table
        }

        // Close resources
        resultSet.close();
        statement.close();
    }
}
