/**
 * AdminDashboardFrame class represents the graphical dashboard
 * for the admin in the Bus Attendance System.
 * 
 * It provides buttons for:
 * - Managing students
 * - Managing buses
 * - Sending alerts
 * - Viewing attendance reports
 * 
 * The class extends JFrame to create the GUI window.
 * 
 * @author Ghala
 */
package com.mycompany.project_bus;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
/**
 * AdminDashboardFrame class represents the graphical dashboard
 * for the admin in the Bus Attendance System.
 * 
 * It provides buttons for:
 * - Managing students
 * - Managing buses
 * - Sending alerts
 * - Viewing attendance reports
 * 
 * The class extends JFrame to create the GUI window.
 * 
 * @author Ghala
 */
public class AdminDashboardFrame extends JFrame {

    /** Admin object of the logged-in admin */
    private Admin admin;

    /**
     * Default constructor for AdminDashboardFrame.
     * Creates and displays the dashboard window.
     */
    public AdminDashboardFrame() {

        setTitle("Admin Dashboard");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(750, 600);
        setLocationRelativeTo(null);
        setResizable(false);

        buildUI();

        setVisible(true);
    }

    /**
     * Constructor with Admin parameter.
     * 
     * @param admin Logged-in admin object
     */
    public AdminDashboardFrame(Admin admin) {
        this.admin = admin;
    }

    /**
     * Builds the graphical user interface (GUI)
     * for the admin dashboard.
     * 
     * It creates:
     * - Title label
     * - Dashboard buttons
     * - Back button
     */
    private void buildUI() {

        JPanel main = new JPanel(new BorderLayout(10, 10));

        main.setBorder(
            BorderFactory.createEmptyBorder(30, 50, 30, 50)
        );

        add(main);

        // Title
        JLabel title = new JLabel(
            "Admin Dashboard",
            SwingConstants.LEFT
        );

        title.setFont(
            new Font(Font.SANS_SERIF, Font.BOLD, 22)
        );

        main.add(title, BorderLayout.NORTH);

        // Buttons Panel
        JPanel btnPanel = new JPanel(
            new GridLayout(1, 4, 15, 0)
        );

        btnPanel.setBorder(
            BorderFactory.createEmptyBorder(60, 0, 60, 0)
        );

        // Manage Student Button
        JButton manageStudentBtn =
            makeButton("Manage Student");

        manageStudentBtn.addActionListener(
            new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    new ManageStudentFrame();
                }
            }
        );

        btnPanel.add(manageStudentBtn);

        // Manage Buses Button
        JButton ManageBusBtn =
            makeButton("Manage Buses");

        ManageBusBtn.addActionListener(
            new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    new BusManagementFrame();
                }
            }
        );

        btnPanel.add(ManageBusBtn);

        // Send Alert Button
        JButton sendAlertBtn =
            makeButton("Send Alert");

        sendAlertBtn.addActionListener(
            new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    new AlertFrame();
                }
            }
        );

        btnPanel.add(sendAlertBtn);

        // Attendance Reports Button
        JButton reportsBtn =
            makeButton("Attendance Reports");

        reportsBtn.addActionListener(
            new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    new AttendanceReportsFrame();
                }
            }
        );

        btnPanel.add(reportsBtn);

        main.add(btnPanel, BorderLayout.CENTER);

        // Back Button
        JPanel backPanel =
            new JPanel(new FlowLayout(FlowLayout.RIGHT));

        JButton backBtn = makeButton("Back");

        backBtn.addActionListener(
            new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {

                    new AdminLoginFrame(admin);

                    dispose();
                }
            }
        );

        backPanel.add(backBtn);

        main.add(backPanel, BorderLayout.SOUTH);
    }

    /**
     * Creates a styled button with unified design.
     * 
     * @param text Button text
     * @return Styled JButton object
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
