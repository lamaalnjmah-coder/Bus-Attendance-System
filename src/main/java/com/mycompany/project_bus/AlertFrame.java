/**
 * AlertFrame class represents the alert sending window
 * for the administrator in the Bus Attendance System.
 * 
 * The admin can:
 * - Enter an alert message
 * - Select alert type
 * - Enter bus ID
 * - Send alerts to the database
 * 
 * This class extends JFrame to create the GUI window.
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
 * AlertFrame class represents the alert sending window
 * for the administrator in the Bus Attendance System.
 * 
 * The admin can:
 * - Enter an alert message
 * - Select alert type
 * - Enter bus ID
 * - Send alerts to the database
 * 
 * This class extends JFrame to create the GUI window.
 * 
 * @author Ghala
 */
public class AlertFrame extends JFrame {

    /** Text field for alert message */
    private JTextField messageField;

    /** Text field for bus ID */
    private JTextField busIdField;

    /** Combo box for alert type selection */
    private JComboBox<String> typeBox;

    /**
     * Constructor for AlertFrame.
     * Initializes the alert window and GUI components.
     */
    public AlertFrame() {

        setTitle("Send Alert (Admin)");

        setSize(450, 350);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setLocationRelativeTo(null);

        buildUI();

        setVisible(true);
    }

    /**
     * Builds the graphical user interface (GUI)
     * for sending alerts.
     * 
     * It creates:
     * - Alert message field
     * - Alert type selection
     * - Bus ID field
     * - Send button
     */
    private void buildUI() {

        JPanel panel =
            new JPanel(new GridLayout(8, 1, 10, 10));

        panel.setBorder(
            BorderFactory.createEmptyBorder(
                20,
                20,
                20,
                20
            )
        );

        // Title
        JLabel title =
            new JLabel(
                "Send Alert",
                SwingConstants.CENTER
            );

        title.setFont(
            new Font("Arial", Font.BOLD, 18)
        );

        // Message Field
        JLabel msgLabel =
            new JLabel("Message:");

        messageField = new JTextField();

        // Alert Type
        JLabel typeLabel =
            new JLabel("Type:");

        String[] types = {
            "Delay",
            "RouteChange",
            "Cancellation",
            "Breakdown"
        };

        typeBox = new JComboBox<>(types);

        // Bus ID Field
        JLabel busLabel =
            new JLabel("Bus ID:");

        busIdField = new JTextField();

        // Send Button
        JButton sendBtn =
            new JButton("Send Alert");

        sendBtn.addActionListener(
            new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {

                    String message =
                        messageField.getText().trim();

                    String type =
                        (String) typeBox.getSelectedItem();

                    String busText =
                        busIdField.getText().trim();

                    // Validate fields
                    if (message.isEmpty()
                            || busText.isEmpty()) {

                        JOptionPane.showMessageDialog(
                            null,
                            "Fill all fields"
                        );

                        return;
                    }

                    try {

                        int busID =
                            Integer.parseInt(busText);

                        Connection conn =
                            DatabaseConnection.getConnection();

                        PreparedStatement ps =
                            conn.prepareStatement(
                                "INSERT INTO ALERT "
                                + "(Message, AlertType, BusID) "
                                + "VALUES (?, ?, ?)"
                            );

                        ps.setString(1, message);

                        ps.setString(2, type);

                        ps.setInt(3, busID);

                        ps.executeUpdate();

                        ps.close();

                        JOptionPane.showMessageDialog(
                            null,
                            "Alert Sent Successfully!"
                        );

                        // Clear fields
                        messageField.setText("");

                        busIdField.setText("");

                    } catch (NumberFormatException ex) {

                        JOptionPane.showMessageDialog(
                            null,
                            "Bus ID must be a number"
                        );

                    } catch (SQLException ex) {

                        JOptionPane.showMessageDialog(
                            null,
                            "Error: " + ex.getMessage()
                        );
                    }
                }
            }
        );

        // Add Components
        panel.add(title);

        panel.add(msgLabel);

        panel.add(messageField);

        panel.add(typeLabel);

        panel.add(typeBox);

        panel.add(busLabel);

        panel.add(busIdField);

        panel.add(sendBtn);

        add(panel);
    }
}
