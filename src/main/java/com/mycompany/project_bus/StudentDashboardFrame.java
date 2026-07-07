/**
 * StudentDashboardFrame represents the main dashboard
 * for students in the Bus Attendance System.
 * 
 * It allows the student to:
 * - View personal information
 * - Manage schedule
 * - View alerts
 * - Logout / go back to login screen
 * 
 * This class extends JFrame and builds the student UI.
 * 
 * @author Ghala
 */
package com.mycompany.project_bus;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * StudentDashboardFrame represents the main dashboard
 * for students in the Bus Attendance System.
 * 
 * It allows the student to:
 * - View personal information
 * - Manage schedule
 * - View alerts
 * - Logout / go back to login screen
 * 
 * This class extends JFrame and builds the student UI.
 * 
 * @author Ghala
 */
public class StudentDashboardFrame extends JFrame {

    /** Logged-in student object */
    private Student student;

    /**
     * Default constructor (not used for full UI).
     */
    public StudentDashboardFrame() {
    }

    /**
     * Constructor that initializes the dashboard
     * for a specific student.
     * 
     * @param student logged-in student
     */
    public StudentDashboardFrame(Student student) {

        this.student = student;

        setTitle("Student Dashboard");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setSize(750, 600);

        setLocationRelativeTo(null);

        setResizable(false);

        buildUI();

        setVisible(true);
    }

    /**
     * Builds the graphical user interface (GUI)
     * for the student dashboard.
     * 
     * It includes:
     * - Welcome title
     * - Info button
     * - Schedule management button
     * - Alerts button
     * - Logout and Back buttons
     */
    private void buildUI() {

        JPanel main =
            new JPanel(new BorderLayout(10, 10));

        main.setBorder(
            BorderFactory.createEmptyBorder(
                30,
                50,
                30,
                50
            )
        );

        add(main);

        // Title
        JLabel title =
            new JLabel(
                "Welcome, " + student.getFullName(),
                SwingConstants.LEFT
            );

        title.setFont(
            new Font(Font.SANS_SERIF, Font.BOLD, 22)
        );

        main.add(title, BorderLayout.NORTH);

        // Buttons Panel
        JPanel btnPanel =
            new JPanel(
                new GridLayout(1, 4, 15, 0)
            );

        btnPanel.setBorder(
            BorderFactory.createEmptyBorder(
                60,
                0,
                60,
                0
            )
        );

        JButton infoBtn =
            makeButton("My Info");

        infoBtn.addActionListener(
            new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {

                    JOptionPane.showMessageDialog(
                        StudentDashboardFrame.this,
                        student.toString()
                    );
                }
            }
        );

        JButton busBtn =
            makeButton("Manage Schedule");

        busBtn.addActionListener(
            new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {

                    new ScheduleFrame();
                }
            }
        );

        JButton alertsBtn =
            makeButton("View Alerts");

        alertsBtn.addActionListener(
            new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {

                    new ViewAlertsFrame();
                }
            }
        );

        btnPanel.add(infoBtn);

        btnPanel.add(busBtn);

        btnPanel.add(alertsBtn);

        main.add(btnPanel, BorderLayout.CENTER);

        // Bottom Buttons
        JPanel backPanel =
            new JPanel(
                new FlowLayout(FlowLayout.RIGHT)
            );

        JButton logoutBtn =
            makeButton("Logout");

        logoutBtn.addActionListener(
            new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {

                    new StudentLoginFrame();

                    dispose();
                }
            }
        );

        JButton backBtn =
            makeButton("Back");

        backBtn.addActionListener(
            new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {

                    new StudentLoginFrame();

                    dispose();
                }
            }
        );

        backPanel.add(backBtn);

        backPanel.add(logoutBtn);

        main.add(backPanel, BorderLayout.SOUTH);
    }

    /**
     * Creates a styled button used across the UI.
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

        return btn;
    }
}