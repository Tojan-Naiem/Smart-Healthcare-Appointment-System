package org.example.smarthealthcareappointmentsystem.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;


/**
 * Represents a patient entity in the mySql database
 * It's extend from the User entity
 * *
 */
@Entity
@DiscriminatorValue("patient")
public class Patient extends User {

}
