package org.example.smarthealthcareappointmentsystem.repository;
import org.example.smarthealthcareappointmentsystem.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository  extends JpaRepository<Patient,Long> {
}
