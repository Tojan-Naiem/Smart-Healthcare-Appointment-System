package org.example.smarthealthcareappointmentsystem.service.Imp;

import org.example.smarthealthcareappointmentsystem.dto.AppointmentDTO;
import org.example.smarthealthcareappointmentsystem.dto.UserMapper;
import org.example.smarthealthcareappointmentsystem.exception.AlreadyExistsException;
import org.example.smarthealthcareappointmentsystem.exception.ForbiddenActionException;
import org.example.smarthealthcareappointmentsystem.exception.ResourcesNotFound;
import org.example.smarthealthcareappointmentsystem.model.Appointment;
import org.example.smarthealthcareappointmentsystem.model.Doctor;
import org.example.smarthealthcareappointmentsystem.model.Patient;
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
    private AppointmentRepository appointmentRepository;
    private PatientRepository patientRepository;
    private DoctorRepository doctorRepository;
    private UserMapper userMapper;
    public AppointmentServiceImp (
            AppointmentRepository appointmentRepository,
            PatientRepository patientRepository,
            DoctorRepository doctorRepository,
            UserMapper userMapper
            ){
        this.appointmentRepository=appointmentRepository;
        this.doctorRepository=doctorRepository;
        this.patientRepository=patientRepository;
        this.userMapper=userMapper;
    }
    public AppointmentDTO createAppointment(AppointmentDTO appointmentDTO){

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

        this.appointmentRepository.save(appointment);

        return userMapper.toDTO(appointment);

    }
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

    public void deleteAppointment(Long id){
        Appointment appointment=this.appointmentRepository.findById(id).orElseThrow(
                ()->{
                    throw new ResourcesNotFound("The appointment id is not found");
                }
        );
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        String currentUserName=null;
        if(!(authentication instanceof AnonymousAuthenticationToken)){
            currentUserName=authentication.getName();
        }
        if(!currentUserName.equals(appointment.getPatient().getUsername()))
            throw new ForbiddenActionException("You do not have permission to delete this appointment");
        this.appointmentRepository.delete(appointment);

    }
}
