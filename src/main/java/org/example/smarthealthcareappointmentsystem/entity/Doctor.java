package org.example.smarthealthcareappointmentsystem.entity;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

/**
 * Represents a doctor entity in the mySql database
 * It's extend from the User entity
 * *
 */

@Entity
@DiscriminatorValue("doctor")
public class Doctor extends User{
    /**
     * The specialty for the doctor
     * This field cannot be null and should be less than 100 characters
     */
    @Column(length = 255,nullable = false)
    private String specialty;

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }
}
