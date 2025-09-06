package org.example.smarthealthcareappointmentsystem.repository;

import org.example.smarthealthcareappointmentsystem.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment,Long> {
}
