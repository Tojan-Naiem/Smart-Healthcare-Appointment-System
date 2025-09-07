package org.example.smarthealthcareappointmentsystem.service;

import org.example.smarthealthcareappointmentsystem.dto.AppointmentDTO;

public interface AppointmentService {
     AppointmentDTO createAppointment(AppointmentDTO appointmentDTO);
     void deleteAppointment(Long id);
}
