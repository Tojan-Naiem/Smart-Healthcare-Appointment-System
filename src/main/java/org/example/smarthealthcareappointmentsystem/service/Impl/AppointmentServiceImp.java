package org.example.smarthealthcareappointmentsystem.service.Impl;

/**
 * Implementation of the {@link org.example.smarthealthcareappointmentsystem.service.AppointmentService} interface
 * Provides business logic for managing {@link org.example.smarthealthcareappointmentsystem.entity.Appointment}entity
 */
import org.example.smarthealthcareappointmentsystem.dto.AppointmentDTO;
import org.example.smarthealthcareappointmentsystem.dto.UserMapper;
import org.example.smarthealthcareappointmentsystem.exception.AlreadyExistsException;
import org.example.smarthealthcareappointmentsystem.exception.ForbiddenActionException;
import org.example.smarthealthcareappointmentsystem.exception.ResourcesNotFound;
import org.example.smarthealthcareappointmentsystem.entity.Appointment;
import org.example.smarthealthcareappointmentsystem.entity.Doctor;
import org.example.smarthealthcareappointmentsystem.entity.Patient;
import org.example.smarthealthcareappointmentsystem.entity.Status;
import org.example.smarthealthcareappointmentsystem.repository.AppointmentRepository;
import org.example.smarthealthcareappointmentsystem.repository.DoctorRepository;
import org.example.smarthealthcareappointmentsystem.repository.PatientRepository;
import org.example.smarthealthcareappointmentsystem.service.AppointmentService;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
@Service
public class AppointmentServiceImp implements AppointmentService {
    private final AppointmentRepository appointmentRepository;
    private final PatientRepository patientRepository;
    private  final DoctorRepository doctorRepository;
    private final NotificationService notificationService;
    public AppointmentServiceImp (
            AppointmentRepository appointmentRepository,
            PatientRepository patientRepository,
            DoctorRepository doctorRepository,
            NotificationService notificationService
    ){
        this.appointmentRepository=appointmentRepository;
        this.doctorRepository=doctorRepository;
        this.patientRepository=patientRepository;
        this.notificationService = notificationService;
    }

    /**
     * Create appointment
     * Check the authentication
     * @param appointmentDTO to create
     *
     */
    public void createAppointment(AppointmentDTO appointmentDTO){

        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        String currentUserName=null;
        if(!(authentication instanceof AnonymousAuthenticationToken)){
             currentUserName=authentication.getName();
        }

        Patient patient=this.patientRepository.findByUsername(currentUserName)
                .orElseThrow(
                        ()->{
                            throw new ResourcesNotFound("The patient username is not found");
                        }
                );
        Doctor doctor=this.doctorRepository.findById(appointmentDTO.getDoctorId())
                .orElseThrow(
                        ()->{
                            throw new ResourcesNotFound("The doctor id is not found");
                        }
                );

        checkConflict(doctor,appointmentDTO.getAppointmentDateTime(),appointmentDTO.getDuration());
        Appointment appointment=new Appointment();
        appointment.setDoctor(doctor);
        appointment.setPatient(patient);
        appointment.setNotes(appointmentDTO.getNotes());
        appointment.setDuration(appointmentDTO.getDuration());
        appointment.setAppointmentDateTime(appointmentDTO.getAppointmentDateTime());
        this.notificationService.sendEmail(patient.getEmail());
        this.notificationService.sendEmail(doctor.getEmail());
        this.appointmentRepository.save(appointment);


    }

    /**
     * Check conflict if there's another appointment in the same times in the same doctor
     * @param doctor that check it's appointment
     * @param newAppointment that wanna check it's time
     * @param duration for the appointment
     */
    public void checkConflict(Doctor doctor, LocalDateTime newAppointment,Integer duration){
        LocalDateTime newStart=newAppointment;
        LocalDateTime newEnd=newAppointment.plusMinutes(duration);
        LocalDateTime searchStart=newAppointment;
        LocalDateTime searchEnd=newAppointment.plusMinutes(duration);

        List<Appointment>appointments=this.appointmentRepository.findByDoctorIdAndAppointmentDateTimeBetween(
                doctor.getId(),
                searchStart,
                searchEnd
        );
        for(Appointment a:appointments){
            LocalDateTime existStart=a.getAppointmentDateTime();
            LocalDateTime existEnd=existStart.plusMinutes(a.getDuration());
            if (newStart.isBefore(existEnd) && newEnd.isAfter(existStart)) {
                throw new AlreadyExistsException("There's already another appointment in this time");
            }
        }

    }

    /**
     * Delete the appointment
     * Check if it's there or not and throw exception if it's not
     * check if the doctor/patient can delete the appointment or not
     * @param id appointment id
     */

    public void deleteAppointment(Long id){
        Appointment appointment=this.appointmentRepository.findById(id).orElseThrow(
                ()->{
                    throw new ResourcesNotFound("The appointment id is not found");
                }
        );
        //check the user if it can delete the appointment or not ( only the assigned doctor and patient can do that)
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        String currentUserName=null;
        if(!(authentication instanceof AnonymousAuthenticationToken)){
            currentUserName=authentication.getName();
        }
        if(!currentUserName.equals(appointment.getPatient().getUsername()))
            throw new ForbiddenActionException("You do not have permission to delete this appointment");


        this.appointmentRepository.delete(appointment);

    }

    /**
     * Update appointment status by id and status
     * Check if the appointment is in the db or not and throw an exception if it's not
     * @param id for the appointment
     * @param status for the appointment
     */

    public void updateAppointment(Long id,String status){
        Appointment appointment=this.appointmentRepository.findById(id).orElseThrow(
                ()->{
                    throw new ResourcesNotFound("The appointment id is not found");
                }
        );
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        String currentUserName=null;
        //check if it's can update the appointment or not
        if(!(authentication instanceof AnonymousAuthenticationToken)){
            currentUserName=authentication.getName();
        }
        if(!currentUserName.equals(appointment.getDoctor().getUsername()))
            throw new ForbiddenActionException("You do not have permission to delete this appointment");

        appointment.setStatus(Status.COMPLETED);
        this.appointmentRepository.save(appointment);
    }
}
