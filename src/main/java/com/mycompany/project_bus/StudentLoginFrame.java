/**
 * StudentLoginFrame class represents the login screen
 * for students in the Bus Attendance System.
 * 
 * It allows students to:
 * - Enter Student ID
 * - Enter First Name
 * - Enter Last Name
 * - Authenticate using database records
 * - Access Student Dashboard if login is successful
 * 
 * This class extends JFrame and builds the login UI.
 * 
 * @author Ghala
 */
package com.mycompany.project_bus;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.*;
/**
 * StudentLoginFrame class represents the login screen
 * for students in the Bus Attendance System.
 * 
 * It allows students to:
 * - Enter Student ID
 * - Enter First Name
 * - Enter Last Name
 * - Authenticate using database records
 * - Access Student Dashboard if login is successful
 * 
 * This class extends JFrame and builds the login UI.
 * 
 * @author Ghala
 */
public class StudentLoginFrame extends JFrame {

    /** Input field for student ID */
    private JTextField studentIdField;

    /** Input field for first name */
    private JTextField firstNameField;

    /** Input field for last name */
    private JTextField lastNameField;

    /**
     * Constructor for StudentLoginFrame.
     * Initializes the login window.
     */
    public StudentLoginFrame() {

        setTitle("Student Login");

        setSize(400, 300);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLocationRelativeTo(null);

        buildUI();

        setVisible(true);
    }

    /**
     * Authenticates a student using database credentials.
     * 
     * @param studentId student ID
     * @param firstName first name
     * @param lastName last name
     * @return Student object if found, otherwise null
     */
    public static Student authenticateStudent(int studentId,
                                              String firstName,
                                              String lastName) {

        String sql =
            "SELECT * FROM STUDENT "
            + "WHERE StudentID=? AND S_Fname=? AND S_Lname=?";

        try {

            Connection conn =
                DatabaseConnection.getConnection();

            PreparedStatement ps =
                conn.prepareStatement(sql);

            ps.setInt(1, studentId);

            ps.setString(2, firstName);

            ps.setString(3, lastName);

            ResultSet rs =
                ps.executeQuery();

            if (rs.next()) {

                return new Student(
                    rs.getInt("StudentID"),
                    rs.getString("S_Fname"),
                    rs.getString("S_Lname"),
                    rs.getString("S_Phone")
                );
            }

            rs.close();

            ps.close();

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return null;
    }

    /**
     * Builds the graphical user interface (GUI)
     * for student login screen.
     * 
     * It includes:
     * - Title label
     * - Input fields
     * - Login and Back buttons
     */
    private void buildUI() {

        JPanel panel =
            new JPanel(
                new GridLayout(5, 2, 10, 10)
            );

        panel.setBorder(
            BorderFactory.createEmptyBorder(
                20,
                20,
                20,
                20
            )
        );

        JLabel title =
            new JLabel(
                "Student Login",
                SwingConstants.CENTER
            );

        title.setFont(
            new Font(
                "Arial",
                Font.BOLD,
                18
            )
        );

        studentIdField =
            new JTextField();

        firstNameField =
            new JTextField();

        lastNameField =
            new JTextField();

        JButton loginBtn =
            new JButton("Login");

        loginBtn.setBackground(
            new Color(50, 130, 200)
        );

        loginBtn.setForeground(Color.WHITE);

        loginBtn.setFocusPainted(false);

        loginBtn.setBorderPainted(false);

        loginBtn.addActionListener(
            new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {

                    String idText =
                        studentIdField.getText().trim();

                    String fname =
                        firstNameField.getText().trim();

                    String lname =
                        lastNameField.getText().trim();

                    if (idText.isEmpty()
                            || fname.isEmpty()
                            || lname.isEmpty()) {

                        JOptionPane.showMessageDialog(
                            StudentLoginFrame.this,
                            "Fill all fields"
                        );

                        return;
                    }

                    int id;
                    try {
                        id = Integer.parseInt(idText);
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(
                            StudentLoginFrame.this,
                            "Student ID must be a number"
                        );
                        return;
                    }
                    Student foundStudent =
                        authenticateStudent(id, fname, lname);

                    if (foundStudent != null) {

                        JOptionPane.showMessageDialog(
                            StudentLoginFrame.this,
                            "Login Successful!"
                        );

                        new StudentDashboardFrame(foundStudent);

                        dispose();

                    } else {

                        JOptionPane.showMessageDialog(
                            StudentLoginFrame.this,
                            "Student not found"
                        );
                    }
                }
            }
        );

        JButton backBtn =
            new JButton("Back");

        backBtn.setBackground(
            new Color(50, 130, 200)
        );

        backBtn.setForeground(Color.WHITE);

        backBtn.setFocusPainted(false);

        backBtn.setBorderPainted(false);

        backBtn.addActionListener(
            new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {

                    new MainLoginFrame();

                    dispose();
                }
            }
        );

        panel.add(new JLabel(""));

        panel.add(backBtn);

        panel.add(title);

        panel.add(new JLabel("Student ID:"));

        panel.add(studentIdField);

        panel.add(new JLabel("First Name:"));

        panel.add(firstNameField);

        panel.add(new JLabel("Last Name:"));

        panel.add(lastNameField);

        panel.add(backBtn);

        panel.add(loginBtn);

        add(panel);
    }
}
