/**
 * MainLoginFrame class represents the main login window
 * of the Bus Attendance System.
 * 
 * It allows the user to:
 * - Select Student login
 * - Select Admin login
 * - Navigate to the corresponding login screens
 * 
 * The class also displays:
 * - System logo
 * - System title
 * - Welcome message
 * 
 * This class extends JFrame to create the GUI window.
 * 
 * @author Ghala
 */
package com.mycompany.project_bus;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
/**
 * MainLoginFrame class represents the main login window
 * of the Bus Attendance System.
 * 
 * It allows the user to:
 * - Select Student login
 * - Select Admin login
 * - Navigate to the corresponding login screens
 * 
 * The class also displays:
 * - System logo
 * - System title
 * - Welcome message
 * 
 * This class extends JFrame to create the GUI window.
 * 
 * @author Ghala
 */
public class MainLoginFrame extends JFrame {

    /**
     * Constructor for MainLoginFrame.
     * Initializes the main login window
     * and GUI components.
     */
    public MainLoginFrame() {

        setTitle("Bus Attendance System");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setSize(750, 600);

        setLocationRelativeTo(null);

        buildUI();

        setVisible(true);
    }

    /**
     * Builds the graphical user interface (GUI)
     * for the main login screen.
     * 
     * It creates:
     * - Logo and title section
     * - Welcome message
     * - Student and Admin buttons
     */
    private void buildUI() {

        JPanel main =
            new JPanel(new BorderLayout(10, 10));

        main.setBorder(
            BorderFactory.createEmptyBorder(
                25,
                35,
                25,
                35
            )
        );

        add(main);

        // Top Panel
        JPanel topPanel =
            new JPanel(new GridLayout(2, 1));

        // Logo Image
        ImageIcon icon =
            new ImageIcon(
                "C:\\Users\\fa_50\\Downloads\\project_logo.jpeg"
            );

        Image scaledImage =
            icon.getImage().getScaledInstance(
                60,
                60,
                Image.SCALE_SMOOTH
            );

        JLabel logoLabel =
            new JLabel(
                new ImageIcon(scaledImage),
                SwingConstants.CENTER
            );

        // Title
        JLabel title =
            new JLabel(
                "Bus Attendance System",
                SwingConstants.CENTER
            );

        title.setFont(
            new Font(
                Font.SANS_SERIF,
                Font.BOLD,
                17
            )
        );

        topPanel.add(logoLabel);

        topPanel.add(title);

        main.add(topPanel, BorderLayout.NORTH);

        // Welcome Message
        JLabel msg =
            new JLabel(
                "<html><center>"
                + "<b><font size='5'>Log In</font></b>"
                + "<br><br>"
                + "Please select your role:"
                + "<br>"
                + "<b>Student or Admin?</b>"
                + "</center></html>",
                SwingConstants.CENTER
            );

        main.add(msg, BorderLayout.CENTER);

        // Buttons Panel
        JPanel btnPanel =
            new JPanel(new GridLayout(1, 2, 20, 0));

        btnPanel.setBorder(
            BorderFactory.createEmptyBorder(
                15,
                10,
                0,
                10
            )
        );

        JButton studentBtn =
            makeButton("Student");

        JButton adminBtn =
            makeButton("Admin");

        // Student Button Action
        studentBtn.addActionListener(
            new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {

                    new StudentLoginFrame();

                    dispose();
                }
            }
        );

        // Admin Button Action
        adminBtn.addActionListener(
            new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {

                    try {

                        Admin admin =
                            Admin.loadFromFile(
                                "admin_credentials.txt"
                            );

                        new AdminLoginFrame(admin);

                        dispose();

                    } catch (Exception ex) {

                        JOptionPane.showMessageDialog(
                            null,
                            "Error loading admin file!"
                        );
                    }
                }
            }
        );

        btnPanel.add(studentBtn);

        btnPanel.add(adminBtn);

        main.add(btnPanel, BorderLayout.SOUTH);
    }

    /**
     * Creates a styled button with unified design.
     * 
     * @param text Button text
     * @return Styled JButton object
     */
    private JButton makeButton(String text) {

        JButton btn = new JButton(text);

        btn.setFont(
            new Font(
                Font.SANS_SERIF,
                Font.BOLD,
                14
            )
        );

        btn.setBackground(
            new Color(50, 130, 200)
        );

        btn.setForeground(Color.WHITE);

        btn.setFocusPainted(false);

        btn.setBorderPainted(false);

        btn.setCursor(
            Cursor.getPredefinedCursor(
                Cursor.HAND_CURSOR
            )
        );

        return btn;
    }

    /**
     * Main method used to start the application.
     * 
     * @param args Command-line arguments
     */
    public static void main(String[] args) {

        new MainLoginFrame();
    }
}
