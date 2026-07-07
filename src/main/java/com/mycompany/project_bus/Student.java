/**
 * Student class represents a student
 * in the Bus Attendance System.
 * 
 * It extends the Person class and adds:
 * - Student ID
 * - Days of week schedule preference
 * - Attendance behavior for schedules
 * 
 * This class is used to store and manage
 * student-related information.
 * 
 * @author Ghala
 */
package com.mycompany.project_bus;
/**
 * Student class represents a student
 * in the Bus Attendance System.
 * 
 * It extends the Person class and adds:
 * - Student ID
 * - Days of week schedule preference
 * - Attendance behavior for schedules
 * 
 * This class is used to store and manage
 * student-related information.
 * 
 * @author Ghala
 */
public class Student extends Person {

    /** Unique student ID */
    private int studentID;

    /** Selected days of week for bus usage */
    private String[] daysOfWeek;

    /**
     * Constructor to create a Student object.
     * 
     * @param studentID student ID
     * @param firstName first name
     * @param lastName last name
     * @param phone phone number
     */
    public Student(int studentID,
                   String firstName,
                   String lastName,
                   String phone) {

        super(studentID, firstName, lastName, phone);

        this.studentID = studentID;
    }

    /**
     * Returns student ID.
     * 
     * @return student ID
     */
    public int getStudentID() {
        return studentID;
    }

    /**
     * Returns selected days of week.
     * 
     * @return array of days
     */
    public String[] getDaysOfWeek() {
        return daysOfWeek;
    }

    /**
     * Simulates student attending a schedule.
     * Prints attendance information.
     * 
     * @param s Schedule object
     */
    public void attendSchedule(Schedule s) {

        System.out.println(
            getFullName()
            + " attended schedule on "
            + s.getDayOfWeek()
        );
    }

    /**
     * Returns formatted string of student data.
     * Includes ID, name, phone, and selected days.
     * 
     * @return formatted student information
     */
    @Override
    public String toString() {

        String days = "";

        if (daysOfWeek != null) {

            for (String d : daysOfWeek) {

                days += d + " ";
            }
        }

        return "Student ID: " + studentID
             + "\nName: " + getFullName()
             + "\nPhone: " + getPhone()
             + "\nDays Of Week: " + days;
    }
}
