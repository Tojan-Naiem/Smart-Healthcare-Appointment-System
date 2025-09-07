package org.example.smarthealthcareappointmentsystem.repository;
import org.example.smarthealthcareappointmentsystem.model.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface PatientRepository  extends JpaRepository<Patient,Long> {
    boolean existsByEmail(String email);
    boolean existsByUsername(String username);
    Optional<Patient> findByEmail(String email);
    Optional<Patient>findByUsername(String username);


}
