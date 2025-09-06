package org.example.smarthealthcareappointmentsystem.service.Imp;

import org.example.smarthealthcareappointmentsystem.dto.PatientDTO;
import org.example.smarthealthcareappointmentsystem.dto.UserMapper;
import org.example.smarthealthcareappointmentsystem.exception.AlreadyExistsException;
import org.example.smarthealthcareappointmentsystem.exception.ResourcesNotFound;
import org.example.smarthealthcareappointmentsystem.model.Patient;
import org.example.smarthealthcareappointmentsystem.model.Role;
import org.example.smarthealthcareappointmentsystem.repository.PatientRepository;
import org.example.smarthealthcareappointmentsystem.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PatientServiceImp implements PatientService {
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;


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
    public PatientDTO getPatientById(Long id){
        Patient patient= this.patientRepository.findById(id).orElseThrow(
                ()-> {throw new ResourcesNotFound("The patient is not found");}
        );
        return userMapper.toDTO(patient);
    }
    public PatientDTO updatePatient(Long id,PatientDTO patientDTO){
        Optional<Patient> existsDoctor=this.patientRepository.findByEmail(patientDTO.getEmail());
        if(existsDoctor.isEmpty()){
            throw new ResourcesNotFound("The Patient is not found");
        }
        Patient updatedPatient =existsDoctor.get();
        updatedPatient.setName(patientDTO.getName());
        updatedPatient.setBirthday(patientDTO.getBirthday());
        updatedPatient.setPassword(patientDTO.getPassword());

        this.patientRepository.save(updatedPatient);
        return userMapper.toDTO(updatedPatient);
    }

}
