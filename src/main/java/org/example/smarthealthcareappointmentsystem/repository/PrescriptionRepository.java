package org.example.smarthealthcareappointmentsystem.repository;

import org.example.smarthealthcareappointmentsystem.entity.Appointment;
import org.example.smarthealthcareappointmentsystem.entity.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
/**
 * Appointment repository interface for managing {@link Prescription} entity
 * Extends {@link MongoRepository} to provide the mongodb for the doctor entity
 */
public interface PrescriptionRepository extends MongoRepository<Prescription,String> {
    List<Prescription> findByPatientId(Long patientId);

}
