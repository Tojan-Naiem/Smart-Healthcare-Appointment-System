package org.example.smarthealthcareappointmentsystem.repository;
import org.example.smarthealthcareappointmentsystem.entity.Doctor;
import org.example.smarthealthcareappointmentsystem.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * User repository interface for managing {@link Patient} entity
 * Extends {@link JpaRepository} to provide the jpa for the patient entity
 */
@Repository
public interface PatientRepository  extends JpaRepository<Patient,Long> {
    boolean existsByEmail(String email);
    boolean existsByUsername(String username);
    Optional<Patient> findByEmail(String email);
    Optional<Patient>findByUsername(String username);


}
