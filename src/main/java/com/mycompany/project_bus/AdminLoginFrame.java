/**
 * AdminLoginFrame class represents the login window
 * for the administrator in the Bus Attendance System.
 * 
 * The admin can:
 * - Enter username and password
 * - Login to the dashboard
 * - Logout and return to the main login screen
 * 
 * This class extends JFrame to create the GUI window.
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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
/**
 * AdminLoginFrame class represents the login window
 * for the administrator in the Bus Attendance System.
 * 
 * The admin can:
 * - Enter username and password
 * - Login to the dashboard
 * - Logout and return to the main login screen
 * 
 * This class extends JFrame to create the GUI window.
 * 
 * @author Ghala
 */
public class AdminLoginFrame extends JFrame {

    /** Admin object used for login validation */
    private Admin admin;

    /** Text field for username input */
    private JTextField usernameField;

    /** Password field for password input */
    private JPasswordField passwordField;

    /**
     * Constructor for AdminLoginFrame.
     * Initializes the login window and GUI components.
     * 
     * @param admin Admin object containing login information
     */
    public AdminLoginFrame(Admin admin) {

        this.admin = admin;

        setTitle("Log In (Admin)");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 400);
        setLocationRelativeTo(null);
        setResizable(false);

        buildUI();

        setVisible(true);
    }

    /**
     * Builds the graphical user interface (GUI)
     * for the admin login screen.
     * 
     * It creates:
     * - Title label
     * - Username and password fields
     * - Login and logout buttons
     */
    private void buildUI() {

        JPanel main =
            new JPanel(new BorderLayout(10, 10));

        main.setBorder(
            BorderFactory.createEmptyBorder(30, 50, 30, 50)
        );

        add(main);

        // Title
        JLabel title =
            new JLabel("Log In", SwingConstants.CENTER);

        title.setFont(
            new Font(Font.SANS_SERIF, Font.PLAIN, 26)
        );

        main.add(title, BorderLayout.NORTH);

        // Fields Panel
        JPanel fieldsPanel =
            new JPanel(new GridLayout(4, 1, 5, 5));

        JLabel userLabel = new JLabel("Username");
        userLabel.setForeground(Color.GRAY);

        usernameField = new JTextField();

        JLabel passLabel = new JLabel("Password");
        passLabel.setForeground(Color.GRAY);

        passwordField = new JPasswordField();

        fieldsPanel.add(userLabel);
        fieldsPanel.add(usernameField);
        fieldsPanel.add(passLabel);
        fieldsPanel.add(passwordField);

        main.add(fieldsPanel, BorderLayout.CENTER);

        // =========================
        // Buttons Panel
        // =========================

        JPanel btnPanel =
            new JPanel(new FlowLayout(
                FlowLayout.RIGHT,
                10,
                0
            ));

        btnPanel.setBorder(
            BorderFactory.createEmptyBorder(10, 0, 0, 0)
        );

        JButton nextBtn = makeButton("Next");

        JButton logOutBtn = makeButton("Log Out");

        // Login Button Action
        nextBtn.addActionListener(
            new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {

                    String inputUser =
                        usernameField.getText().trim();

                    String inputPass =
                        new String(passwordField.getPassword());

                    if (admin.checkLogin(inputUser, inputPass)) {

                        new AdminDashboardFrame();

                        dispose();

                    } else {

                        JOptionPane.showMessageDialog(
                            null,
                            "Invalid username or password!"
                        );
                    }
                }
            }
        );

        // Logout Button Action
        logOutBtn.addActionListener(
            new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {

                    new MainLoginFrame();

                    dispose();
                }
            }
        );

        btnPanel.add(nextBtn);
        btnPanel.add(logOutBtn);

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

        btn.setBackground(new Color(50, 130, 200));

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
}
