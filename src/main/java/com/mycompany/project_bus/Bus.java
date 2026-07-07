/**
 * Bus class represents a bus in the Bus Attendance System.
 * 
 * It stores information about:
 * - Bus ID
 * - Bus capacity
 * - Availability time
 * - Assigned driver ID
 * 
 * This class provides constructors, getters,
 * and setters for bus data management.
 * 
 * @author Ghala
 */
package com.mycompany.project_bus;
/**
 * Bus class represents a bus in the Bus Attendance System.
 * 
 * It stores information about:
 * - Bus ID
 * - Bus capacity
 * - Availability time
 * - Assigned driver ID
 * 
 * This class provides constructors, getters,
 * and setters for bus data management.
 * 
 * @author Ghala
 */
public class Bus {

    /** Unique ID of the bus */
    private int busID;

    /** Maximum number of passengers */
    private int capacity;

    /** Bus availability time */
    private String availabilityTime;

    /** ID of the assigned driver */
    private int driverID;

    /**
     * Constructor to create a Bus object.
     * 
     * @param busID Unique bus ID
     * @param capacity Maximum passenger capacity
     * @param availabilityTime Bus availability time
     * @param driverID Assigned driver ID
     */
    public Bus(int busID,
               int capacity,
               String availabilityTime,
               int driverID) {

        this.busID = busID;

        this.capacity = capacity;

        this.availabilityTime = availabilityTime;

        this.driverID = driverID;
    }

    /**
     * Returns the bus ID.
     * 
     * @return bus ID
     */
    public int getBusID() {
        return busID;
    }

    /**
     * Returns the bus capacity.
     * 
     * @return bus capacity
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * Returns the availability time.
     * 
     * @return availability time
     */
    public String getAvailabilityTime() {
        return availabilityTime;
    }

    /**
     * Returns the assigned driver ID.
     * 
     * @return driver ID
     */
    public int getDriverID() {
        return driverID;
    }

    /**
     * Updates the bus capacity.
     * 
     * @param capacity New capacity value
     */
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    /**
     * Updates the availability time.
     * 
     * @param availabilityTime New availability time
     */
    public void setAvailabilityTime(String availabilityTime) {
        this.availabilityTime = availabilityTime;
    }

    /**
     * Updates the assigned driver ID.
     * 
     * @param driverID New driver ID
     */
    public void setDriverID(int driverID) {
        this.driverID = driverID;
    }
}
