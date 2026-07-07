/**
 * Person class represents a general person entity
 * in the Bus Attendance System.
 * 
 * It is a parent (super) class for:
 * - Student
 * - Driver
 * - Admin (indirectly)
 * 
 * It stores common attributes such as:
 * - ID
 * - First name
 * - Last name
 * - Phone number
 * 
 * It also provides basic validation and utility methods.
 * 
 * @author Ghala
 */
package com.mycompany.project_bus;
/**
 * Person class represents a general person entity
 * in the Bus Attendance System.
 * 
 * It is a parent (super) class for:
 * - Student
 * - Driver
 * - Admin (indirectly)
 * 
 * It stores common attributes such as:
 * - ID
 * - First name
 * - Last name
 * - Phone number
 * 
 * It also provides basic validation and utility methods.
 * 
 * @author Ghala
 */
public class Person {

    /** Unique ID of the person */
    private int id;

    /** First name of the person */
    private String firstName;

    /** Last name of the person */
    private String lastName;

    /** Phone number of the person */
    private String phone;

    /**
     * Constructor to create a Person object.
     * Includes validation for first name.
     * 
     * @param id Person ID
     * @param firstName First name
     * @param lastName Last name
     * @param phone Phone number
     * @throws IllegalArgumentException if first name is empty
     */
    public Person(int id,
                  String firstName,
                  String lastName,
                  String phone) {

        if (firstName == null
                || firstName.trim().isEmpty()) {

            throw new IllegalArgumentException(
                "First name cannot be empty."
            );
        }

        this.id = id;

        this.firstName = firstName.trim();

        this.lastName = lastName.trim();

        this.phone = phone;
    }

    /**
     * Returns the person ID.
     * 
     * @return person ID
     */
    public int getId() {
        return id;
    }

    /**
     * Returns the first name.
     * 
     * @return first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Returns the last name.
     * 
     * @return last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Returns full name (first + last).
     * 
     * @return full name
     */
    public String getFullName() {
        return firstName + " " + lastName;
    }

    /**
     * Returns phone number.
     * 
     * @return phone number
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Updates the phone number.
     * 
     * @param phone new phone number
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Returns a formatted string representation
     * of the person object.
     * 
     * @return formatted person details
     */
    @Override
    public String toString() {
        return getFullName() + " | Phone: " + phone;
    }
}