package org.example.smarthealthcareappointmentsystem.repository;

import org.example.smarthealthcareappointmentsystem.entity.Appointment;
import org.example.smarthealthcareappointmentsystem.entity.Doctor;
import org.example.smarthealthcareappointmentsystem.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
/**
 * Appointment repository interface for managing {@link Appointment} entity
 * Extends {@link JpaRepository} to provide the jpa for the doctor entity
 */
@Repository
public interface AppointmentRepository extends JpaRepository<Appointment,Long> {

    List<Appointment>findByDoctorIdAndAppointmentDateTimeBetween(Long doctor_id, LocalDateTime startDate,LocalDateTime endDate);
    List<Appointment>findByDoctorIdAndAppointmentDateTime(Long doctor_id, LocalDateTime time);
}
