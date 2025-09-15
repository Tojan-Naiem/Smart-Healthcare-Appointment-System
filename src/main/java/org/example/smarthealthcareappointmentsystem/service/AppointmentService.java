package org.example.smarthealthcareappointmentsystem.service;

import org.example.smarthealthcareappointmentsystem.dto.AppointmentDTO;
import org.example.smarthealthcareappointmentsystem.entity.Appointment;

import java.util.List;

/**
 * Appointment Service interface contains method for {@link org.example.smarthealthcareappointmentsystem.entity.Appointment}
 */
public interface AppointmentService {
     void createAppointment(AppointmentDTO appointmentDTO);
     void deleteAppointment(Long id);
     public void updateAppointment(Long id,String status);
     public List<Appointment> gitDoctorAppointment();
}
