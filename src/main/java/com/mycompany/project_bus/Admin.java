
package com.mycompany.project_bus;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
/**
 * Admin class represents the administrator of the Bus Attendance System.
 * It handles login functionality and manages students and drivers
 * using database operations (CRUD).
 * 
 * The class extends Person class.
 * 
 * @author Ghala
 */
public class Admin extends Person {

    /** Admin username */
    private String username;

    /** Admin password */
    private String password;

    /**
     * Constructor to create Admin object.
     * 
     * @param username Admin username
     * @param password Admin password
     */
    public Admin(String username, String password) {
        super(0, "Admin", "User", "N/A");
        this.username = username;
        this.password = password;
    }

    /**
     * Returns admin username.
     * 
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Returns admin password.
     * 
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Loads admin information from a text file.
     * 
     * File format:
     * username=admin
     * password=admin123
     * 
     * @param filePath Path of the text file
     * @return Admin object
     * @throws Exception if file cannot be read
     */
    public static Admin loadFromFile(String filePath) throws Exception {

        BufferedReader br = new BufferedReader(new FileReader(filePath));

        String line1 = br.readLine();
        String line2 = br.readLine();

        br.close();

        String u = line1.split("=")[1];
        String p = line2.split("=")[1];

        return new Admin(u, p);
    }

    /**
     * Checks admin login credentials.
     * 
     * @param inputUser Entered username
     * @param inputPass Entered password
     * @return true if login is correct, otherwise false
     */
    public boolean checkLogin(String inputUser, String inputPass) {
        return username.equals(inputUser)
                && password.equals(inputPass);
    }

    // Student Management

    /**
     * Adds a new student to the database.
     * 
     * @param studentID Student ID
     * @param fname First name
     * @param lname Last name
     * @param phone Phone number
     * @throws SQLException if database error occurs
     */
    public void addStudent(int studentID,
                           String fname,
                           String lname,
                           String phone) throws SQLException {

        Connection conn = DatabaseConnection.getConnection();

        PreparedStatement ps = conn.prepareStatement(
            "INSERT INTO STUDENT " +
            "(StudentID, S_Fname, S_Lname, S_Phone) " +
            "VALUES (?, ?, ?, ?)"
        );

        ps.setInt(1, studentID);
        ps.setString(2, fname);
        ps.setString(3, lname);
        ps.setString(4, phone);

        ps.executeUpdate();
        ps.close();
    }

    /**
     * Updates student information.
     * 
     * @param studentID Student ID
     * @param fname Updated first name
     * @param lname Updated last name
     * @param phone Updated phone number
     * @throws SQLException if database error occurs
     */
    public void updateStudent(int studentID,
                              String fname,
                              String lname,
                              String phone) throws SQLException {

        Connection conn = DatabaseConnection.getConnection();

        PreparedStatement ps = conn.prepareStatement(
            "UPDATE STUDENT " +
            "SET S_Fname=?, S_Lname=?, S_Phone=? " +
            "WHERE StudentID=?"
        );

        ps.setString(1, fname);
        ps.setString(2, lname);
        ps.setString(3, phone);
        ps.setInt(4, studentID);

        ps.executeUpdate();
        ps.close();
    }

    /**
     * Deletes a student from database.
     * 
     * @param studentID Student ID
     * @throws SQLException if database error occurs
     */
    public void deleteStudent(int studentID) throws SQLException {

        Connection conn = DatabaseConnection.getConnection();

        PreparedStatement ps = conn.prepareStatement(
            "DELETE FROM STUDENT WHERE StudentID=?"
        );

        ps.setInt(1, studentID);

        ps.executeUpdate();
        ps.close();
    }

    /**
     * Retrieves all students from database.
     * 
     * @return List of students
     * @throws SQLException if database error occurs
     */
    public List<Student> getAllStudents() throws SQLException {

        List<Student> list = new ArrayList<>();

        Connection conn = DatabaseConnection.getConnection();

        Statement stmt = conn.createStatement();

        ResultSet rs = stmt.executeQuery("SELECT * FROM STUDENT");

        while (rs.next()) {

            list.add(new Student(
                rs.getInt("StudentID"),
                rs.getString("S_Fname"),
                rs.getString("S_Lname"),
                rs.getString("S_Phone")
            ));
        }

        rs.close();
        stmt.close();

        return list;
    }

    // Driver Management

    /**
     * Adds a new driver to the database.
     * 
     * @param driverID Driver ID
     * @param fname First name
     * @param lname Last name
     * @throws SQLException if database error occurs
     */
    public void addDriver(int driverID,
                          String fname,
                          String lname) throws SQLException {

        Connection conn = DatabaseConnection.getConnection();

        PreparedStatement ps = conn.prepareStatement(
            "INSERT INTO DRIVER " +
            "(DriverID, D_Fname, D_Lname) " +
            "VALUES (?, ?, ?)"
        );

        ps.setInt(1, driverID);
        ps.setString(2, fname);
        ps.setString(3, lname);

        ps.executeUpdate();
        ps.close();
    }

    /**
     * Updates driver information.
     * 
     * @param driverID Driver ID
     * @param fname Updated first name
     * @param lname Updated last name
     * @throws SQLException if database error occurs
     */
    public void updateDriver(int driverID,
                             String fname,
                             String lname) throws SQLException {

        Connection conn = DatabaseConnection.getConnection();

        PreparedStatement ps = conn.prepareStatement(
            "UPDATE DRIVER " +
            "SET D_Fname=?, D_Lname=? " +
            "WHERE DriverID=?"
        );

        ps.setString(1, fname);
        ps.setString(2, lname);
        ps.setInt(3, driverID);

        ps.executeUpdate();
        ps.close();
    }

    /**
     * Deletes a driver from database.
     * 
     * @param driverID Driver ID
     * @throws SQLException if database error occurs
     */
    public void deleteDriver(int driverID) throws SQLException {

        Connection conn = DatabaseConnection.getConnection();

        PreparedStatement ps = conn.prepareStatement(
            "DELETE FROM DRIVER WHERE DriverID=?"
        );

        ps.setInt(1, driverID);

        ps.executeUpdate();
        ps.close();
    }

    /**
     * Retrieves all drivers from database.
     * 
     * @return List of drivers
     * @throws SQLException if database error occurs
     */
    public List<Driver> getAllDrivers() throws SQLException {

        List<Driver> list = new ArrayList<>();

        Connection conn = DatabaseConnection.getConnection();

        Statement stmt = conn.createStatement();

        ResultSet rs = stmt.executeQuery("SELECT * FROM DRIVER");

        while (rs.next()) {

            list.add(new Driver(
                rs.getInt("DriverID"),
                rs.getString("D_Fname"),
                rs.getString("D_Lname"),
                rs.getString("S_Phone")
            ));
        }

        rs.close();
        stmt.close();

        return list;
    }
}