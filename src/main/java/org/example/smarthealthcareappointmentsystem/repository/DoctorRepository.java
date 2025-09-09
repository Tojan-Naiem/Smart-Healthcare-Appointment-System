package org.example.smarthealthcareappointmentsystem.repository;

import org.example.smarthealthcareappointmentsystem.entity.Doctor;
import org.example.smarthealthcareappointmentsystem.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * User repository interface for managing {@link Doctor} entity
 * Extends {@link JpaRepository} to provide the jpa for the doctor entity
 */
@Repository
public interface DoctorRepository extends JpaRepository<Doctor,Long> {
    /**
     * Check if the email is already used or not in DB
     * @param email for doctor
     * @return boolean
     */
    boolean existsByEmail(String email);

    /**
     * check if the username is already used or not in db
     * @param username for doctor
     * @return boolean
     */
    boolean existsByUsername(String username);

    /**
     * Find doctor by email
     * @param email doctor
     * @return the doctor if it found else return empty
     */
    Optional<Doctor> findByEmail(String email);

    /**
     * find doctors by specific specialty
     * @param specialty doctor
     * @param pageable the page and size
     * @return page of the doctor list
     */

    Page<Doctor> findBySpecialty(String specialty, Pageable pageable);
    /**
     * Find doctor by username
     * @param username doctor
     * @return the doctor if it found else return empty
     */
    Optional<Doctor> findByUsername(String username);

}
