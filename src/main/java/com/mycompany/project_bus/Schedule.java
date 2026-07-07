/**
 * Schedule class represents a bus schedule
 * in the Bus Attendance System.
 * 
 * It stores information about:
 * - Schedule ID
 * - Day of week
 * - Time
 * - Bus ID
 * 
 * It also includes validation to ensure
 * data integrity when creating or updating schedules.
 * 
 * @author Ghala
 */
package com.mycompany.project_bus;


/**
 * Schedule class represents a bus schedule
 * in the Bus Attendance System.
 * 
 * It stores information about:
 * - Schedule ID
 * - Day of week
 * - Time
 * - Bus ID
 * 
 * It also includes validation to ensure
 * data integrity when creating or updating schedules.
 * 
 * @author Ghala
 */

public class Schedule {

    /** Unique schedule ID */
    private int scheduleID;

    /** Day of the week for the schedule */
    private String dayOfWeek;

    /** Time of the schedule */
    private String time;

    /** Associated bus ID */
    private int busID;

    /**
     * Default constructor.
     */
    public Schedule() {
    }

    /**
     * Constructor to create a Schedule object.
     * Includes validation for all fields.
     * 
     * @param dayOfWeek day of the week
     * @param time schedule time
     * @param busID associated bus ID
     * @throws IllegalArgumentException if any field is invalid
     */
    public Schedule(String dayOfWeek,
                    String time,
                    int busID) {

        if (dayOfWeek == null
                || dayOfWeek.trim().isEmpty()) {

            throw new IllegalArgumentException(
                "Day of week cannot be empty"
            );
        }

        if (time == null
                || time.trim().isEmpty()) {

            throw new IllegalArgumentException(
                "Time cannot be empty"
            );
        }

        if (busID <= 0) {

            throw new IllegalArgumentException(
                "Bus ID must be positive"
            );
        }

        this.dayOfWeek = dayOfWeek.trim();

        this.time = time.trim();

        this.busID = busID;
    }

    /**
     * Returns schedule ID.
     * 
     * @return schedule ID
     */
    public int getScheduleID() {
        return scheduleID;
    }

    /**
     * Updates schedule ID.
     * 
     * @param scheduleID new schedule ID
     */
    public void setScheduleID(int scheduleID) {
        this.scheduleID = scheduleID;
    }

    /**
     * Returns day of week.
     * 
     * @return day of week
     */
    public String getDayOfWeek() {
        return dayOfWeek;
    }

    /**
     * Updates day of week with validation.
     * 
     * @param dayOfWeek new day of week
     */
    public void setDayOfWeek(String dayOfWeek) {

        if (dayOfWeek == null
                || dayOfWeek.trim().isEmpty()) {

            throw new IllegalArgumentException(
                "Day of week cannot be empty"
            );
        }

        this.dayOfWeek = dayOfWeek.trim();
    }

    /**
     * Returns schedule time.
     * 
     * @return time
     */
    public String getTime() {
        return time;
    }

    /**
     * Updates schedule time with validation.
     * 
     * @param time new time
     */
    public void setTime(String time) {

        if (time == null
                || time.trim().isEmpty()) {

            throw new IllegalArgumentException(
                "Time cannot be empty"
            );
        }

        this.time = time.trim();
    }

    /**
     * Returns bus ID.
     * 
     * @return bus ID
     */
    public int getBusID() {
        return busID;
    }

    /**
     * Updates bus ID with validation.
     * 
     * @param busID new bus ID
     */
    public void setBusID(int busID) {

        if (busID <= 0) {

            throw new IllegalArgumentException(
                "Bus ID must be positive"
            );
        }

        this.busID = busID;
    }
}
