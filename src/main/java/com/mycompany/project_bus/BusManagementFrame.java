/**
 * BusManagementFrame class represents the bus management
 * window in the Bus Attendance System.
 * 
 * It allows the admin to:
 * - View all buses
 * - Add new buses
 * - Delete buses
 * - Refresh bus data
 * 
 * The class extends JFrame to create the GUI window.
 * 
 * @author Ghala
 */

package com.mycompany.project_bus;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.*;
/**
 * BusManagementFrame class represents the bus management
 * window in the Bus Attendance System.
 * 
 * It allows the admin to:
 * - View all buses
 * - Add new buses
 * - Delete buses
 * - Refresh bus data
 * 
 * The class extends JFrame to create the GUI window.
 * 
 * @author Ghala
 */
public class BusManagementFrame extends JFrame {

    /** Table used to display bus data */
    private JTable busTable;

    /** Table model used to manage table rows and columns */
    private DefaultTableModel tableModel;

    /**
     * Constructor for BusManagementFrame.
     * Initializes the bus management window
     * and GUI components.
     */
    public BusManagementFrame() {

        setTitle("Admin Dashboard - Manage Buses");

        setSize(600, 400);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setLocationRelativeTo(null);

        setLayout(new BorderLayout());

        // Table Setup
        tableModel = new DefaultTableModel(
            new String[]{
                "BusID",
                "Capacity",
                "AvailabilityTime",
                "DriverID"
            },
            0
        );

        busTable = new JTable(tableModel);

        loadBusData();

        JScrollPane scrollPane =
            new JScrollPane(busTable);

        add(scrollPane, BorderLayout.CENTER);

        // Buttons Panel
        JPanel buttonPanel = new JPanel();

        JButton btnAdd =
            new JButton("Add Bus");

        JButton btnDelete =
            new JButton("Delete Selected");

        JButton btnRefresh =
            new JButton("Refresh");
        
        JButton btnAddDriver = new JButton("Add Driver");
        buttonPanel.add(btnAddDriver);
        
        buttonPanel.add(btnAdd);

        buttonPanel.add(btnDelete);

        buttonPanel.add(btnRefresh);

        add(buttonPanel, BorderLayout.SOUTH);

        // Add Button Action
        btnAdd.addActionListener(
            new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {

                    showAddBusForm();
                }
            }
        );
        
        btnAddDriver.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            showAddDriverForm();
        }
    });

        // Delete Button Action
        btnDelete.addActionListener(
            new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {

                    int selectedRow =
                        busTable.getSelectedRow();

                    if (selectedRow != -1) {

                        int busID =
                            (int) tableModel.getValueAt(
                                selectedRow,
                                0
                            );

                        deleteBus(busID);

                        loadBusData();

                    } else {

                        JOptionPane.showMessageDialog(
                            null,
                            "Please select a bus to delete."
                        );
                    }
                }
            }
        );

        // Refresh Button Action
        btnRefresh.addActionListener(
            new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {

                    loadBusData();
                }
            }
        );

        setVisible(true);
    }

    private void showAddDriverForm() {
    JDialog dialog = new JDialog(this, "Add New Driver", true);
    dialog.setSize(300, 200);
    dialog.setLayout(new GridLayout(4, 2, 10, 10));
    dialog.setLocationRelativeTo(this);

    JTextField idField    = new JTextField();
    JTextField fnameField = new JTextField();
    JTextField lnameField = new JTextField();

    dialog.add(new JLabel(" Driver ID:"));
    dialog.add(idField);
    dialog.add(new JLabel(" First Name:"));
    dialog.add(fnameField);
    dialog.add(new JLabel(" Last Name:"));
    dialog.add(lnameField);

    JButton saveBtn   = new JButton("Save");
    JButton cancelBtn = new JButton("Cancel");

    dialog.add(saveBtn);
    dialog.add(cancelBtn);

    saveBtn.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            try {
                int id        = Integer.parseInt(idField.getText().trim());
                String fname  = fnameField.getText().trim();
                String lname  = lnameField.getText().trim();

                if (fname.isEmpty() || lname.isEmpty()) {
                    JOptionPane.showMessageDialog(dialog, "Fill all fields");
                    return;
                }

                Connection conn = DatabaseConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(
                    "INSERT INTO DRIVER (DriverID, D_Fname, D_Lname) VALUES (?, ?, ?)"
                );
                ps.setInt(1, id);
                ps.setString(2, fname);
                ps.setString(3, lname);
                ps.executeUpdate();
                ps.close();

                JOptionPane.showMessageDialog(dialog, "Driver added successfully!");
                dialog.dispose();

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(dialog, "Driver ID must be a number");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(dialog, "Error: " + ex.getMessage());
            }
        }
    });

    cancelBtn.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            dialog.dispose();
        }
    });

    dialog.setVisible(true);
    }
    /**
     * Loads all bus records from the database
     * and displays them in the table.
     */
    private void loadBusData() {

        tableModel.setRowCount(0);

        try {

            Connection conn =
                DatabaseConnection.getConnection();

            Statement stmt =
                conn.createStatement();

            ResultSet rs =
                stmt.executeQuery("SELECT * FROM BUS");

            while (rs.next()) {

                tableModel.addRow(
                    new Object[]{

                        rs.getInt("BusID"),

                        rs.getInt("Capacity"),

                        rs.getString("AvailabilityTime"),

                        rs.getInt("DriverID")
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
     * Deletes a bus from the database.
     * 
     * @param busID ID of the bus to delete
     */
    private void deleteBus(int busID) {

        try {

            Connection conn =
                DatabaseConnection.getConnection();

            PreparedStatement ps =
                conn.prepareStatement(
                    "DELETE FROM BUS WHERE BusID=?"
                );

            ps.setInt(1, busID);

            ps.executeUpdate();

            ps.close();

            JOptionPane.showMessageDialog(
                this,
                "Bus deleted successfully!"
            );

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(
                this,
                "Error deleting bus: "
                + e.getMessage()
            );
        }
    }

    /**
     * Displays a dialog form used to add
     * a new bus to the database.
     */
    private void showAddBusForm() {

        JDialog dialog =
            new JDialog(
                this,
                "Add New Bus",
                true
            );

        dialog.setSize(300, 220);

        dialog.setLayout(
            new GridLayout(4, 2, 10, 10)
        );

        dialog.setLocationRelativeTo(this);

        JTextField txtCapacity =
            new JTextField();

        JTextField txtAvailability =
            new JTextField();

        JTextField txtDriverID =
            new JTextField();

        dialog.add(new JLabel(" Capacity:"));

        dialog.add(txtCapacity);

        dialog.add(new JLabel(" Availability Time:"));

        dialog.add(txtAvailability);

        dialog.add(new JLabel(" Driver ID:"));

        dialog.add(txtDriverID);

        JButton btnSave =
            new JButton("Save");

        JButton btnCancel =
            new JButton("Cancel");

        dialog.add(btnSave);

        dialog.add(btnCancel);

        // Save Button Action
        btnSave.addActionListener(
            new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {

                    try {

                        int cap =
                            Integer.parseInt(
                                txtCapacity.getText()
                            );

                        String avail =
                            txtAvailability.getText();

                        int driverID =
                            Integer.parseInt(
                                txtDriverID.getText()
                            );

                        addBusToDB(
                            cap,
                            avail,
                            driverID
                        );

                        dialog.dispose();

                        loadBusData();

                    } catch (NumberFormatException ex) {

                        JOptionPane.showMessageDialog(
                            dialog,
                            "Please enter valid numeric values."
                        );
                    }
                }
            }
        );

        // Cancel Button Action
        btnCancel.addActionListener(
            new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {

                    dialog.dispose();
                }
            }
        );

        dialog.setVisible(true);
    }

    /**
     * Adds a new bus record to the database.
     * 
     * @param capacity Bus capacity
     * @param availabilityTime Bus availability time
     * @param driverID Assigned driver ID
     */
    private void addBusToDB(int capacity,
                            String availabilityTime,
                            int driverID) {

        try {

            Connection conn =
                DatabaseConnection.getConnection();

            PreparedStatement ps =
                conn.prepareStatement(
                    "INSERT INTO BUS "
                    + "(Capacity, AvailabilityTime, DriverID) "
                    + "VALUES (?, ?, ?)"
                );

            ps.setInt(1, capacity);

            ps.setString(2, availabilityTime);

            ps.setInt(3, driverID);

            ps.executeUpdate();

            ps.close();

            JOptionPane.showMessageDialog(
                this,
                "Bus added successfully!"
            );

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(
                this,
                "Error adding bus: "
                + e.getMessage()
            );
        }
    }
}
