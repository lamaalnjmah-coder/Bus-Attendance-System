/**
 * ManageStudentFrame class represents the student management
 * window in the Bus Attendance System.
 * 
 * It allows the admin to:
 * - View all students
 * - Add new students
 * - Refresh student data automatically after changes
 * - Close the management window
 * 
 * The class extends JFrame to create the GUI window.
 * 
 * @author Ghala
 */
package com.mycompany.project_bus;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
/**
 * ManageStudentFrame class represents the student management
 * window in the Bus Attendance System.
 * 
 * It allows the admin to:
 * - View all students
 * - Add new students
 * - Refresh student data automatically after changes
 * - Close the management window
 * 
 * The class extends JFrame to create the GUI window.
 * 
 * @author Ghala
 */
public class ManageStudentFrame extends JFrame {

    /** Table used to display student data */
    private JTable table;

    /** Table model for managing table rows and columns */
    private DefaultTableModel tableModel;

    /**
     * Constructor for ManageStudentFrame.
     * Initializes the student management window
     * and GUI components.
     */
    public ManageStudentFrame() {

        setTitle("Manage Students");

        setSize(600, 400);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setLocationRelativeTo(null);

        buildUI();

        setVisible(true);
    }

    /**
     * Builds the graphical user interface (GUI)
     * for managing students.
     * 
     * It includes:
     * - Title label
     * - Student table
     * - Add and Back buttons
     */
    private void buildUI() {

        setLayout(new BorderLayout(10, 10));

        // Title
        JLabel title =
            new JLabel(
                "Manage Students",
                SwingConstants.CENTER
            );

        title.setFont(
            new Font("Arial", Font.BOLD, 18)
        );

        title.setBorder(
            BorderFactory.createEmptyBorder(
                10,
                0,
                10,
                0
            )
        );

        add(title, BorderLayout.NORTH);

        // Table
        tableModel = new DefaultTableModel(
            new String[]{
                "Student ID",
                "First Name",
                "Last Name",
                "Phone"
            },
            0
        );

        table = new JTable(tableModel);

        loadData();

        add(
            new JScrollPane(table),
            BorderLayout.CENTER
        );

        // Buttons Panel
        JButton addBtn =
            new JButton("Add Student");

        JButton backBtn =
            new JButton("Back");

        // Add Student Action
        addBtn.addActionListener(
            new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {

                    showAddStudentForm();
                }
            }
        );

        // Back Button Action
        backBtn.addActionListener(
            new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {

                    dispose();
                }
            }
        );

        JPanel btnPanel =
            new JPanel(
                new FlowLayout(FlowLayout.RIGHT)
            );

        btnPanel.add(addBtn);

        btnPanel.add(backBtn);

        add(btnPanel, BorderLayout.SOUTH);
    }

    /**
     * Loads student data from database
     * and displays it in the table.
     */
    private void loadData() {

        tableModel.setRowCount(0);

        try {

            Connection conn =
                DatabaseConnection.getConnection();

            Statement stmt =
                conn.createStatement();

            ResultSet rs =
                stmt.executeQuery("SELECT * FROM STUDENT");

            while (rs.next()) {

                tableModel.addRow(
                    new Object[]{

                        rs.getInt("StudentID"),

                        rs.getString("S_Fname"),

                        rs.getString("S_Lname"),

                        rs.getString("S_Phone")
                    }
                );
            }

            rs.close();

            stmt.close();

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(
                this,
                "Error loading data: "
                + e.getMessage()
            );
        }
    }

    /**
     * Displays a dialog form to add a new student.
     * Includes validation and database insertion.
     */
    private void showAddStudentForm() {

        JDialog dialog =
            new JDialog(
                this,
                "Add New Student",
                true
            );

        dialog.setSize(300, 250);

        dialog.setLayout(
            new GridLayout(5, 2, 10, 10)
        );

        dialog.setLocationRelativeTo(this);

        JTextField idField =
            new JTextField();

        JTextField fnameField =
            new JTextField();

        JTextField lnameField =
            new JTextField();

        JTextField phoneField =
            new JTextField();

        dialog.add(new JLabel(" Student ID:"));

        dialog.add(idField);

        dialog.add(new JLabel(" First Name:"));

        dialog.add(fnameField);

        dialog.add(new JLabel(" Last Name:"));

        dialog.add(lnameField);

        dialog.add(new JLabel(" Phone:"));

        dialog.add(phoneField);

        JButton saveBtn =
            new JButton("Save");

        JButton cancelBtn =
            new JButton("Cancel");

        dialog.add(saveBtn);

        dialog.add(cancelBtn);

        // Save Button Action
        saveBtn.addActionListener(
            new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {

                    String idText =
                        idField.getText().trim();

                    String fname =
                        fnameField.getText().trim();

                    String lname =
                        lnameField.getText().trim();

                    String phone =
                        phoneField.getText().trim();

                    if (idText.isEmpty()
                            || fname.isEmpty()
                            || lname.isEmpty()
                            || phone.isEmpty()) {

                        JOptionPane.showMessageDialog(
                            dialog,
                            "Fill all fields"
                        );

                        return;
                    }

                    try {

                        int id =
                            Integer.parseInt(idText);

                        Connection conn =
                            DatabaseConnection.getConnection();

                        PreparedStatement ps =
                            conn.prepareStatement(
                                "INSERT INTO STUDENT "
                                + "(StudentID, S_Fname, S_Lname, S_Phone) "
                                + "VALUES (?, ?, ?, ?)"
                            );

                        ps.setInt(1, id);

                        ps.setString(2, fname);

                        ps.setString(3, lname);

                        ps.setString(4, phone);

                        ps.executeUpdate();

                        ps.close();

                        JOptionPane.showMessageDialog(
                            dialog,
                            "Student added successfully!"
                        );

                        dialog.dispose();

                        loadData();

                    } catch (NumberFormatException ex) {

                        JOptionPane.showMessageDialog(
                            dialog,
                            "Student ID must be a number"
                        );

                    } catch (SQLException ex) {

                        JOptionPane.showMessageDialog(
                            dialog,
                            "Error: " + ex.getMessage()
                        );
                    }
                }
            }
        );

        // Cancel Button Action
        cancelBtn.addActionListener(
            new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {

                    dialog.dispose();
                }
            }
        );

        dialog.setVisible(true);
    }
}
