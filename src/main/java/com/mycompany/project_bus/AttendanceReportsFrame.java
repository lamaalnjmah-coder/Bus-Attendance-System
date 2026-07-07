/**
 * AttendanceReportsFrame class represents the attendance
 * reports window in the Bus Attendance System.
 * 
 * It allows the admin to:
 * - View attendance records
 * - Display student and bus information
 * - View attendance status from the database
 * 
 * The class extends JFrame to create the GUI window.
 * 
 * @author Ghala
 */
package com.mycompany.project_bus;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;

import javax.swing.table.DefaultTableModel;
/**
 * AttendanceReportsFrame class represents the attendance
 * reports window in the Bus Attendance System.
 * 
 * It allows the admin to:
 * - View attendance records
 * - Display student and bus information
 * - View attendance status from the database
 * 
 * The class extends JFrame to create the GUI window.
 * 
 * @author Ghala
 */
public class AttendanceReportsFrame extends JFrame {

    /** Table used to display attendance data */
    private JTable table;

    /** Table model used to manage table rows and columns */
    private DefaultTableModel tableModel;

    /**
     * Constructor for AttendanceReportsFrame.
     * Initializes the reports window and GUI components.
     */
    public AttendanceReportsFrame() {

        setTitle("Attendance Reports");

        setSize(650, 400);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setLocationRelativeTo(null);

        buildUI();

        setVisible(true);
    }

    /**
     * Builds the graphical user interface (GUI)
     * for attendance reports.
     * 
     * It creates:
     * - Title label
     * - Attendance table
     * - Back button
     */
    private void buildUI() {

        setLayout(new BorderLayout(10, 10));

        // Title
        JLabel title =
            new JLabel(
                "Attendance Reports",
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
                "Bus ID",
                "Schedule ID",
                "Status"
            },
            0
        );

        table = new JTable(tableModel);

        loadData();

        add(
            new JScrollPane(table),
            BorderLayout.CENTER
        );

        // Back Button
        JButton backBtn =
            new JButton("Back");

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

        btnPanel.add(backBtn);

        add(btnPanel, BorderLayout.SOUTH);
    }

    /**
     * Loads attendance data from the database
     * and displays it in the table.
     * 
     * The query joins:
     * - ATTENDS table
     * - STUDENT table
     * - SCHEDULE table
     * 
     * @throws SQLException if database error occurs
     */
    private void loadData() {

        tableModel.setRowCount(0);

        String sql =
            "SELECT ST.StudentID, ST.S_Fname, ST.S_Lname, "
            + "SC.BusID, A.ScheduleID, A.Status "
            + "FROM ATTENDS A "
            + "JOIN STUDENT ST "
            + "ON A.StudentID = ST.StudentID "
            + "JOIN SCHEDULE SC "
            + "ON A.ScheduleID = SC.ScheduleID";

        try {

            Connection conn =
                DatabaseConnection.getConnection();

            Statement stmt =
                conn.createStatement();

            ResultSet rs =
                stmt.executeQuery(sql);

            while (rs.next()) {

                tableModel.addRow(
                    new Object[]{

                        rs.getInt("StudentID"),

                        rs.getString("S_Fname"),

                        rs.getString("S_Lname"),

                        rs.getInt("BusID"),

                        rs.getInt("ScheduleID"),

                        rs.getString("Status")
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
}
