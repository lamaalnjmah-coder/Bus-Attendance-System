/**
 * ViewAlertsFrame class displays all alerts
 * sent by the admin in the Bus Attendance System.
 * 
 * It allows students to:
 * - View system alerts from the database
 * - See alert type, message, and bus ID
 * - Read alerts in a scrollable text area
 * 
 * This class extends JFrame and provides
 * a simple read-only UI for alerts.
 * 
 * @author Ghala
 */
package com.mycompany.project_bus;

import java.awt.*;
import java.sql.*;
import javax.swing.*;
/**
 * ViewAlertsFrame class displays all alerts
 * sent by the admin in the Bus Attendance System.
 * 
 * It allows students to:
 * - View system alerts from the database
 * - See alert type, message, and bus ID
 * - Read alerts in a scrollable text area
 * 
 * This class extends JFrame and provides
 * a simple read-only UI for alerts.
 * 
 * @author Ghala
 */
public class ViewAlertsFrame extends JFrame {

    /**
     * Constructor for ViewAlertsFrame.
     * Initializes the alerts window.
     */
    public ViewAlertsFrame() {

        setTitle("View Alerts (Student)");

        setSize(500, 350);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setLocationRelativeTo(null);

        buildUI();

        setVisible(true);
    }

    /**
     * Builds the graphical user interface (GUI)
     * for displaying alerts.
     * 
     * It retrieves alert data from the database
     * and displays it inside a text area.
     */
    private void buildUI() {

        JTextArea area = new JTextArea();

        area.setEditable(false);

        area.setFont(
            new Font("Arial", Font.PLAIN, 14)
        );

        try {

            Connection conn =
                DatabaseConnection.getConnection();

            Statement stmt =
                conn.createStatement();

            ResultSet rs =
                stmt.executeQuery("SELECT * FROM ALERT");

            boolean hasAlerts = false;

            while (rs.next()) {

                area.append(
                    "Type: " + rs.getString("AlertType") + "\n"
                );

                area.append(
                    "Message: " + rs.getString("Message") + "\n"
                );

                area.append(
                    "Bus ID: " + rs.getInt("BusID") + "\n"
                );

                area.append(
                    "----------------------\n"
                );

                hasAlerts = true;
            }

            if (!hasAlerts) {

                area.setText("No alerts available.");
            }

            rs.close();

            stmt.close();

        } catch (SQLException e) {

            area.setText("Error loading alerts.");
        }

        add(new JScrollPane(area));
    }
}
