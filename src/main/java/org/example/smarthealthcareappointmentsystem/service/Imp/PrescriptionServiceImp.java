package org.example.smarthealthcareappointmentsystem.service.Imp;

import org.example.smarthealthcareappointmentsystem.dto.PrescriptionDTO;
import org.example.smarthealthcareappointmentsystem.dto.UserMapper;
import org.example.smarthealthcareappointmentsystem.exception.ResourcesNotFound;
import org.example.smarthealthcareappointmentsystem.entity.Doctor;
import org.example.smarthealthcareappointmentsystem.entity.Patient;
import org.example.smarthealthcareappointmentsystem.entity.Prescription;
import org.example.smarthealthcareappointmentsystem.repository.DoctorRepository;
import org.example.smarthealthcareappointmentsystem.repository.PatientRepository;
import org.example.smarthealthcareappointmentsystem.repository.PrescriptionRepository;
import org.example.smarthealthcareappointmentsystem.service.PrescriptionService;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrescriptionServiceImp implements PrescriptionService {

    private PrescriptionRepository prescriptionRepository;
    private PatientRepository patientRepository;
    private DoctorRepository doctorRepository;
    private UserMapper userMapper;
    public PrescriptionServiceImp (
            PatientRepository patientRepository,
            DoctorRepository doctorRepository,
            UserMapper userMapper,
            PrescriptionRepository prescriptionRepository
    ){
        this.doctorRepository=doctorRepository;
        this.patientRepository=patientRepository;
        this.userMapper=userMapper;
        this.prescriptionRepository=prescriptionRepository;

    }
    public void createPrescription(PrescriptionDTO prescriptionDTO){
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        String currentUserName=null;
        if(!(authentication instanceof AnonymousAuthenticationToken)){
            currentUserName=authentication.getName();
        }
        Patient patient=this.patientRepository.findById(prescriptionDTO.getPatientId())
                .orElseThrow(
                        ()->{
                            throw new ResourcesNotFound("The patient username is not found");
                        }
                );
        Doctor doctor=this.doctorRepository.findByUsername(currentUserName)
                .orElseThrow(
                        ()->{
                            throw new ResourcesNotFound("The doctor is not found");
                        }
                );
        Prescription prescription=new Prescription();
        prescription.setDoctorId(doctor.getId());
        prescription.setPatientId(patient.getId());
        prescription.setIssueDate(prescriptionDTO.getIssueDate());
        prescription.setMedicines(
                prescriptionDTO.getMedicines()
                        .stream().map(
                                (p)->
                                    userMapper.toMedicineEntity(p)

                        ).toList()
        );
        this.prescriptionRepository.save(prescription);


    }
    public List<PrescriptionDTO> getPrescriptionsForPatient(){
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

        return this.prescriptionRepository.findByPatientId(patient.getId()).stream().map(
                (m)-> userMapper.toDTO(m)
                ).toList();
    }



}
