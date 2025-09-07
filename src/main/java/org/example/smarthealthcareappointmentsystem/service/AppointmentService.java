package org.example.smarthealthcareappointmentsystem.service;

import org.example.smarthealthcareappointmentsystem.dto.AppointmentDTO;

public interface AppointmentService {
    public AppointmentDTO createAppointment(AppointmentDTO appointmentDTO);
    public void deleteAppointment(Long id);
}
