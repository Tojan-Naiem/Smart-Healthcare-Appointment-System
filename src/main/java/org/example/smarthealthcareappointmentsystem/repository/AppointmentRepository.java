package org.example.smarthealthcareappointmentsystem.repository;

import org.example.smarthealthcareappointmentsystem.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment,Long> {

    List<Appointment>findByDoctorIdAndAppointmentDateTimeBetween(Long doctor_id, LocalDateTime startDate,LocalDateTime endDate);

}
