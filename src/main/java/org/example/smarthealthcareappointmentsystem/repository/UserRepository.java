package org.example.smarthealthcareappointmentsystem.repository;

import org.example.smarthealthcareappointmentsystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    @Query("select count(*) > 0 from User u where u.email=:email")
    boolean existsByEmail(String email);

    User findByUsername(String username);
}
