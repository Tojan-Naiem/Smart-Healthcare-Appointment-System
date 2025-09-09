package org.example.smarthealthcareappointmentsystem.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("patient")
public class Patient extends User {

}
