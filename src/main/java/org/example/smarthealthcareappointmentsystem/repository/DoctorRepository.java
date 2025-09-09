package org.example.smarthealthcareappointmentsystem.repository;

import org.example.smarthealthcareappointmentsystem.entity.Doctor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor,Long> {
    boolean existsByEmail(String email);
    boolean existsByUsername(String username);
    Optional<Doctor> findByEmail(String email);

    Page<Doctor> findBySpecialty(String specialty, Pageable pageable);
    Optional<Doctor> findByUsername(String username);

}
