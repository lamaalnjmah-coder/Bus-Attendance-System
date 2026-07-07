/**
 * Driver class represents a bus driver
 * in the Bus Attendance System.
 * 
 * It extends the Person class and adds:
 * - Driver ID
 * - Driving behavior
 * 
 * This class provides constructors,
 * getters, setters, and driver actions.
 * 
 * @author Ghala
 */
package com.mycompany.project_bus;
/**
 * Driver class represents a bus driver
 * in the Bus Attendance System.
 * 
 * It extends the Person class and adds:
 * - Driver ID
 * - Driving behavior
 * 
 * This class provides constructors,
 * getters, setters, and driver actions.
 * 
 * @author Ghala
 */
public class Driver extends Person {

    /** Unique ID of the driver */
    private int driverID;

    /**
     * Constructor to create a Driver object.
     * 
     * @param driverID Unique driver ID
     * @param firstName Driver first name
     * @param lastName Driver last name
     * @param phone Driver phone number
     */
    public Driver(int driverID,
                  String firstName,
                  String lastName,
                  String phone) {

        super(0, firstName, lastName, phone);

        this.driverID = driverID;
    }

    /**
     * Returns the driver ID.
     * 
     * @return driver ID
     */
    public int getDriverID() {
        return driverID;
    }

    /**
     * Updates the driver ID.
     * 
     * @param driverID New driver ID
     */
    public void setDriverID(int driverID) {
        this.driverID = driverID;
    }

    /**
     * Simulates the driving action.
     * 
     * Prints a message indicating
     * that the driver is driving the bus.
     */
    public void drives() {

        System.out.println(
            "Driver is driving the bus"
        );
    }
}
