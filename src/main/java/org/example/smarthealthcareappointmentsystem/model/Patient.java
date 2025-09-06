package org.example.smarthealthcareappointmentsystem.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("patient")
public class Patient extends User {
}
