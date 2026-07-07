/**
 * ScheduleFrame class represents the schedule management screen
 * in the Bus Attendance System.
 * 
 * It allows students to:
 * - Select days for bus service
 * - Choose arrival time
 * - Save schedule preferences (UI level)
 * - Navigate back to the student dashboard
 * 
 * The class extends JFrame to create the GUI window.
 * 
 * @author Ghala
 */
package com.mycompany.project_bus;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.*;
/**
 * ScheduleFrame class represents the schedule management screen
 * in the Bus Attendance System.
 * 
 * It allows students to:
 * - Select days for bus service
 * - Choose arrival time
 * - Save schedule preferences (UI level)
 * - Navigate back to the student dashboard
 * 
 * The class extends JFrame to create the GUI window.
 * 
 * @author Ghala
 */
public class ScheduleFrame extends JFrame {

        /**
     * object student from class Student
     */
    private Student student;
    /**
     * Default constructor for ScheduleFrame.
     * Initializes the schedule management window.
     */
    public ScheduleFrame() {

        setTitle("Manage Schedule");

        setSize(750, 550);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setResizable(false);

        buildUI();

        setVisible(true);
    }

    /**
     * Constructor that accepts a Student object.(Currently unused but reserved for future enhancements.)
     *
     * @param std student object
     */
    public ScheduleFrame(Student std) {
        this.student = student;
        setTitle("Manage Schedule");
        setSize(750, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        buildUI();
        setVisible(true);
    }

    /**
     * Builds the graphical user interface (GUI)
     * for schedule management.
     * 
     * It includes:
     * - Days selection checkboxes
     * - Arrival time dropdown
     * - Save and Back buttons
     */
    

    private void buildUI() {

        JPanel mainPanel = new JPanel();

        mainPanel.setLayout(null);

        mainPanel.setBackground(Color.WHITE);

        add(mainPanel);

        // Title
        JLabel title =
            new JLabel("Manage Schedule");

        title.setFont(
            new Font("SansSerif", Font.PLAIN, 22)
        );

        title.setBounds(60, 40, 250, 40);

        mainPanel.add(title);

        // Days Label

        JLabel daysLabel =
            new JLabel("Select the days you need bus service");

        daysLabel.setFont(
            new Font("SansSerif", Font.BOLD, 11)
        );

        daysLabel.setBounds(150, 130, 250, 20);

        mainPanel.add(daysLabel);

        // Days Panel

        JPanel daysPanel =
            new JPanel();

        daysPanel.setLayout(
            new FlowLayout(FlowLayout.LEFT, 15, 10)
        );

        daysPanel.setBackground(Color.WHITE);

        daysPanel.setBounds(145, 155, 450, 40);

        JCheckBox sunday =
            new JCheckBox("Sunday");

        JCheckBox monday =
            new JCheckBox("Monday");

        JCheckBox tuesday =
            new JCheckBox("Tuesday");

        JCheckBox wednesday =
            new JCheckBox("Wednesday");

        JCheckBox thursday =
            new JCheckBox("Thursday");

        JCheckBox[] boxes =
            {sunday, monday, tuesday, wednesday, thursday};

        for (JCheckBox box : boxes) {

            box.setBackground(Color.WHITE);

            box.setFocusPainted(false);

            daysPanel.add(box);
        }

        mainPanel.add(daysPanel);

        // Time Selection
        JLabel timeLabel =
            new JLabel("Select Arrival Time :");

        timeLabel.setFont(
            new Font("SansSerif", Font.BOLD, 9)
        );

        timeLabel.setBounds(145, 235, 150, 20);

        mainPanel.add(timeLabel);

        String[] times = {
            "7:45 - 8:00",
            "9:45 - 10:00",
            "11:30 - 12:00",
            "12:45 - 1:00",
            "2:45 - 3:00"
        };

        JComboBox<String> timeCombo =
            new JComboBox<>(times);

        timeCombo.setBounds(145, 255, 180, 28);

        mainPanel.add(timeCombo);


        // Buttons
        JButton saveBtn = makeButton("Save");
        saveBtn.setBounds(450, 265, 80, 35);
        mainPanel.add(saveBtn);

        saveBtn.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
        boolean anySelected = false;
        for (JCheckBox box : boxes) {
            if (box.isSelected()) {
                anySelected = true;
                break;
            }
        }
        if (!anySelected) {
            JOptionPane.showMessageDialog(null, "Please select at least one day");
            return;
        }

        try {
            Connection conn = DatabaseConnection.getConnection();
            
            PreparedStatement del = conn.prepareStatement(
                "DELETE FROM STUDENT_DAYS WHERE StudentID=?"
            );
            del.setInt(1, student.getId());
            del.executeUpdate();
            del.close();

            PreparedStatement ps = conn.prepareStatement(
                "INSERT INTO STUDENT_DAYS (StudentID, DayOfWeek) VALUES (?, ?)"
            );
            for (JCheckBox box : boxes) {
                if (box.isSelected()) {
                    ps.setInt(1, student.getId());
                    ps.setString(2, box.getText());
                    ps.addBatch();
                }
            }
            ps.executeBatch();
            ps.close();

            JOptionPane.showMessageDialog(null, "Schedule saved successfully!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
        }
            }
        });
        mainPanel.add(saveBtn);

        JButton backBtn = makeButton("Back");

        backBtn.setBounds(550, 265, 80, 35);

        mainPanel.add(backBtn);

        // Back Button Action
        backBtn.addActionListener(
            new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {

                    new StudentDashboardFrame();

                    dispose();
                }
            }
        );
    }

    /**
     * Creates a styled button with unified design.
     * 
     * @param text button text
     * @return styled JButton
     */
    private JButton makeButton(String text) {

        JButton btn = new JButton(text);

        btn.setBackground(new Color(50, 130, 200));

        btn.setForeground(Color.WHITE);

        btn.setFocusPainted(false);

        btn.setBorderPainted(false);

        btn.setCursor(
            Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)
        );

        btn.setFont(
            new Font("SansSerif", Font.BOLD, 12)
        );

        return btn;
    }
}
