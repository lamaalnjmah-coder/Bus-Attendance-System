/**
 * DatabaseConnection class is responsible for
 * managing the database connection in the
 * Bus Attendance System.
 * 
 * It provides:
 * - A single shared database connection
 * - Methods to open and close the connection
 * 
 * This class uses MySQL JDBC connection.
 * 
 * @author Ghala
 */
package com.mycompany.project_bus;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 * DatabaseConnection class is responsible for
 * managing the database connection in the
 * Bus Attendance System.
 * 
 * It provides:
 * - A single shared database connection
 * - Methods to open and close the connection
 * 
 * This class uses MySQL JDBC connection.
 * 
 * @author Ghala
 */
public class DatabaseConnection {

    /** Database URL */
    private static final String URL =
        "jdbc:mysql://127.0.0.1:3306/Bus_Attendance_System";

    /** Database username */
    private static final String USER =
        "root";

    /** Database password */
    private static final String PASSWORD =
        "your_password";

    /** Shared database connection object */
    private static Connection connection = null;

    /**
     * Private constructor to prevent object creation.
     * 
     * This class follows the utility class pattern.
     */
    private DatabaseConnection() {
    }

    /**
     * Establishes and returns a database connection.
     * 
     * If the connection is closed or not created,
     * a new connection will be established.
     * 
     * @return Database connection object
     * @throws SQLException if connection fails
     */
    public static Connection getConnection()
            throws SQLException {

        if (connection == null || connection.isClosed()) {

            connection =
                DriverManager.getConnection(
                    URL,
                    USER,
                    PASSWORD
                );
        }

        return connection;
    }

    /**
     * Closes the database connection safely.
     * 
     * If the connection exists and is open,
     * it will be closed.
     */
    public static void close() {

        try {

            if (connection != null
                    && !connection.isClosed()) {

                connection.close();
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }
    }
}
