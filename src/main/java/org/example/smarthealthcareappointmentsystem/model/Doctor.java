package org.example.smarthealthcareappointmentsystem.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table
@DiscriminatorValue("doctor")
public class Doctor extends User{
    private String specialty;
}
