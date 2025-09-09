package org.example.smarthealthcareappointmentsystem.repository;

import org.example.smarthealthcareappointmentsystem.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * User repository interface for managing {@link User} entity
 * Extends {@link JpaRepository} to provide the jpa for the user entity
 */
@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    /**
     * check if the email is exists in the db
     * @param email for the user
     * @return boolean -> true if it exists and false if it's not
     */
    @Query("select count(*) > 0 from User u where u.email=:email")
    boolean existsByEmail(String email);

    /**
     * find the user by username
     * @param username for the user
     * @return the user if it's found , and empty if it's not
     */
    Optional<User> findByUsername(String username);
}
