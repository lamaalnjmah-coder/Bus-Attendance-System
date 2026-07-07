package com.mycompany.project_bus;

/**
 *
 * @author Ghala
 */

/**
 * Represents an alert notification in the Bus Attendance System.
 * This class stores information about alerts such as alert ID,
 * message content, and alert type (e.g., Delay, Cancellation, Emergency).
 * It provides methods to access and modify alert data and perform basic operations.
 */

public class Alert {

    private int alertID;
    private String message;
    private String type;

    /**
     * Default constructor that creates an empty Alert object.
     */
    public Alert() {
    }

    /**
     * Constructs an Alert object with specified values.
     *
     * @param alertID the unique identifier of the alert
     * @param message the message content of the alert
     * @param type the type of alert (e.g., Delay, Emergency)
     */
    public Alert(int alertID, String message, String type) {

        if (message == null || message.trim().isEmpty()) {
            throw new IllegalArgumentException("Message cannot be empty");
        }

        if (type == null || type.trim().isEmpty()) {
            throw new IllegalArgumentException("Type cannot be empty");
        }

        this.alertID = alertID;
        this.message = message.trim();
        this.type = type.trim();
    }

    /**
     * Gets the alert ID.
     *
     * @return alertID
     */
    public int getAlertID() {
        return alertID;
    }

    /**
     * Sets the alert ID.
     *
     * @param alertID the alert ID to set
     */
    public void setAlertID(int alertID) {
        this.alertID = alertID;
    }

    /**
     * Gets the alert message.
     *
     * @return message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets the alert message.
     *
     * @param message the alert message to set
     */
    public void setMessage(String message) {

        if (message == null || message.trim().isEmpty()) {
            throw new IllegalArgumentException("Message cannot be empty");
        }

        this.message = message.trim();
    }

    /**
     * Gets the alert type.
     *
     * @return type
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the alert type.
     *
     * @param type the alert type to set
     */
    public void setType(String type) {

        if (type == null || type.trim().isEmpty()) {
            throw new IllegalArgumentException("Type cannot be empty");
        }

        this.type = type.trim();
    }

    /**
     * Returns a formatted string representation of the alert.
     *
     * @return formatted alert details
     */
    @Override
    public String toString() {
        return "Alert ID: " + alertID +
               "\nMessage: " + message +
               "\nType: " + type;
    }
}
