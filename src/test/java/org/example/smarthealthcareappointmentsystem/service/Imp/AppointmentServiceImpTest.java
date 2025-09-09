package org.example.smarthealthcareappointmentsystem.service.Imp;

import org.example.smarthealthcareappointmentsystem.dto.UserMapper;
import org.example.smarthealthcareappointmentsystem.entity.Appointment;
import org.example.smarthealthcareappointmentsystem.entity.Doctor;
import org.example.smarthealthcareappointmentsystem.entity.Patient;
import org.example.smarthealthcareappointmentsystem.repository.AppointmentRepository;
import org.example.smarthealthcareappointmentsystem.repository.DoctorRepository;
import org.example.smarthealthcareappointmentsystem.repository.PatientRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
class AppointmentServiceImpTest {
    @InjectMocks
    private AppointmentServiceImp appointmentServiceImp;
    @Mock
    private AppointmentRepository appointmentRepository;
    @Mock

    private PatientRepository patientRepository;
    @Mock

    private DoctorRepository doctorRepository;
    @Mock

    private UserMapper userMapper;

    @Test
    public void checkConflict_success(){
        Patient patient = new Patient();
        patient.setId(1L);

        Doctor doctor = new Doctor();
        doctor.setId(1L);

        LocalDateTime now = LocalDateTime.now();

        Appointment existingAppointment = new Appointment();
        existingAppointment.setId(1L);
        existingAppointment.setPatient(patient);
        existingAppointment.setDoctor(doctor);
        existingAppointment.setDuration(60);
        existingAppointment.setAppointmentDateTime(now);

        LocalDateTime newAppointmentTime = now;
        Integer newDuration = 60;

        when(appointmentRepository.findByDoctorIdAndAppointmentDateTimeBetween(
                eq(doctor.getId()),
                any(LocalDateTime.class),
                any(LocalDateTime.class)
        )).thenReturn(List.of(existingAppointment));

        assertThrows(RuntimeException.class, () -> {
            appointmentServiceImp.checkConflict(doctor, newAppointmentTime, newDuration);
        });

        verify(appointmentRepository).findByDoctorIdAndAppointmentDateTimeBetween(
                eq(doctor.getId()),
                any(LocalDateTime.class),
                any(LocalDateTime.class)
        );



    }

    @Test
    public void checkConflict_shouldFailWhenConflictExists() {
        Patient patient = new Patient();
        patient.setId(1L);

        Doctor doctor = new Doctor();
        doctor.setId(1L);

        LocalDateTime sameTime = LocalDateTime.of(2024, 9, 6, 10, 30);

        Appointment existingAppointment = new Appointment();
        existingAppointment.setId(1L);
        existingAppointment.setPatient(patient);
        existingAppointment.setDoctor(doctor);
        existingAppointment.setAppointmentDateTime(sameTime);
        existingAppointment.setDuration(60);
        LocalDateTime newAppointmentTime = sameTime;
        Integer newDuration = 60;

        when(appointmentRepository.findByDoctorIdAndAppointmentDateTimeBetween(
                eq(doctor.getId()),
                any(LocalDateTime.class),
                any(LocalDateTime.class)
        )).thenReturn(List.of(existingAppointment));

        assertThrows(RuntimeException.class, () -> {
            appointmentServiceImp.checkConflict(doctor, newAppointmentTime, newDuration);
        }, "Should throw ConflictException when time conflict exists");
    }


}