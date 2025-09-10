package org.example.smarthealthcareappointmentsystem.service.Impl;

import org.example.smarthealthcareappointmentsystem.dto.PatientDTO;
import org.example.smarthealthcareappointmentsystem.mapper.UserMapper;
import org.example.smarthealthcareappointmentsystem.exception.AlreadyExistsException;
import org.example.smarthealthcareappointmentsystem.exception.ResourcesNotFound;
import org.example.smarthealthcareappointmentsystem.entity.Patient;
import org.example.smarthealthcareappointmentsystem.entity.Role;
import org.example.smarthealthcareappointmentsystem.repository.PatientRepository;
import org.example.smarthealthcareappointmentsystem.service.PatientService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;


/**
 * Implementation of the {@link PatientService} interface
 * Provides business logic for managing {@link Patient}entity
 */
@Service
public class PatientServiceImp implements PatientService {

    private final PatientRepository patientRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    public PatientServiceImp(
            PatientRepository patientRepository,
            UserMapper userMapper,
            PasswordEncoder passwordEncoder
    ){
        this.patientRepository=patientRepository;
        this.userMapper=userMapper;
        this.passwordEncoder=passwordEncoder;
    }

    /**
     * Create Patient account
     * check if the email and the username already used in db and throw an exception
     * @param registeredPatient the info about patient
     */


    public void addPatient(PatientDTO registeredPatient){
        boolean isExistByEmail=this.patientRepository.existsByEmail(registeredPatient.getEmail());
        boolean isExistByUsername=this.patientRepository.existsByUsername(registeredPatient.getUsername());

        if(isExistByEmail){
            throw new AlreadyExistsException("The email is already exists");
        }
        if(isExistByUsername){
            throw new AlreadyExistsException("The username is already exists");
        }
        Patient patient= userMapper.toEntity(registeredPatient);
        patient.setPassword(passwordEncoder.encode(registeredPatient.getPassword()));
        patient.setRole(new Role(3,"PATIENT"));

        this.patientRepository.save(patient);
    }

    /**
     * Get all Patients info
     * @param pageable for size and page
     * @return page of patients list
     */

    public Page<PatientDTO> getPatients(Pageable pageable){
        Page<Patient> patients =this.patientRepository.findAll(pageable);
        patients.forEach(
                p->{
                    System.out.println(p.getId());
                }
        );
        return patients.map(
                d-> userMapper.toDTO(d)
        );
    }

    /**
     * Get Patient by id
     * Check if the patient is in the db or not and throw an exception
     * @param id for the patient
     * @return patientDTO for found patient
     */
    public PatientDTO getPatientById(Long id){
        Patient patient= this.patientRepository.findById(id).orElseThrow(
                ()-> {throw new ResourcesNotFound("The patient is not found");}
        );
        return userMapper.toDTO(patient);
    }

    /**
     * update patient
     * check if the patient are there or not
     * @param id
     * @param patientDTO
     * @return
     */
    public PatientDTO updatePatient(Long id,PatientDTO patientDTO){
        Optional<Patient> existsDoctor=this.patientRepository.findByEmail(patientDTO.getEmail());
        if(existsDoctor.isEmpty()){
            throw new ResourcesNotFound("The Patient is not found");
        }
        Patient updatedPatient =existsDoctor.get();
        updatedPatient.setFullName(patientDTO.getFullName());
        updatedPatient.setBirthday(patientDTO.getBirthday());
        updatedPatient.setPassword(patientDTO.getPassword());

        this.patientRepository.save(updatedPatient);
        return userMapper.toDTO(updatedPatient);
    }

}
