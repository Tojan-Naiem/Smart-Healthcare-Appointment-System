package org.example.smarthealthcareappointmentsystem.service.Impl;

import org.example.smarthealthcareappointmentsystem.dto.PatientDTO;
import org.example.smarthealthcareappointmentsystem.mapper.UserMapperDTO;
import org.example.smarthealthcareappointmentsystem.exception.AlreadyExistsException;
import org.example.smarthealthcareappointmentsystem.exception.ResourcesNotFound;
import org.example.smarthealthcareappointmentsystem.entity.Patient;
import org.example.smarthealthcareappointmentsystem.repository.PatientRepository;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

@SpringBootTest
class PatientServiceImpTest {
    @InjectMocks
    private PatientServiceImp patientServiceImp;
    @Mock
    private PatientRepository patientRepository;
    @Mock
    private UserMapperDTO userMapperDTO;
    @Mock
    private PasswordEncoder passwordEncoder;

    // add patient

    @Test
    public void addValidPatient_thenCorrect(){
        //create a patient dto to send it to the addDoctor method
        PatientDTO patientDTO =new PatientDTO();
        patientDTO.setFullName("test");
        patientDTO.setEmail("test@gmail.com");
        patientDTO.setPassword("test123");
        patientDTO.setUsername("tst");

        // when they called in addDoctor
        when(patientRepository.existsByEmail("test@gmail.com")).thenReturn(false);
        when(patientRepository.existsByUsername("tst")).thenReturn(false);
        // create for the dto
        Patient patient =new Patient();
        patient.setEmail(patientDTO.getEmail());
        patient.setPassword(patientDTO.getPassword());
        patient.setUsername(patientDTO.getUsername());
        patient.setFullName(patientDTO.getFullName());


        when(userMapperDTO.toEntity(patientDTO)).thenReturn(patient);
        when(passwordEncoder.encode("test123")).thenReturn("test123");


        patientServiceImp.addPatient(patientDTO);
        ArgumentCaptor<Patient> patientCaptor=ArgumentCaptor.forClass(Patient.class);

        verify(this.patientRepository).save(patientCaptor.capture());
        Patient savedPatient =patientCaptor.getValue();
        assertEquals(savedPatient.getPassword(), patient.getPassword());
        assertEquals(savedPatient.getEmail(), patient.getEmail());

        assertEquals("PATIENT", savedPatient.getRole().getName());
    }
    @Test
    public void addPatient_WithExistEmail_throwException() {
        PatientDTO patientDTO =new PatientDTO();
        patientDTO.setFullName("test");
        patientDTO.setEmail("test@gmail.com");
        patientDTO.setPassword("test123");
        patientDTO.setUsername("tst");

        // when they called in addDoctor
        when(patientRepository.existsByEmail("test@gmail.com")).thenReturn(true);

        AlreadyExistsException exception = assertThrows(AlreadyExistsException.class, () -> {
            patientServiceImp.addPatient(patientDTO);
        });
        assertEquals("The email is already exists",exception.getMessage());
    }
    @Test
    public void addPatient_WithExistUsername_throwException() {

        PatientDTO patientDTO =new PatientDTO();
        patientDTO.setFullName("test");
        patientDTO.setEmail("test@gmail.com");
        patientDTO.setPassword("test123");
        patientDTO.setUsername("tst");


        // when they called in addDoctor
        when(patientRepository.existsByUsername("tst")).thenReturn(true);
        AlreadyExistsException exception = assertThrows(AlreadyExistsException.class, () -> {
            patientServiceImp.addPatient(patientDTO);
        });
        assertEquals("The username is already exists",exception.getMessage());
    }

    // get patients

    @Test
    public void getPatient_withoutFilters(){
        Pageable pageable= PageRequest.of(1,5);
        Page<Patient> mockDoctorPage=mock(Page.class);
        when(this.patientRepository.findAll(pageable)).thenReturn(mockDoctorPage);
        when(mockDoctorPage.map(any())).thenReturn(mock(Page.class));

        Page<PatientDTO>patientDTOS=this.patientServiceImp.getPatients(pageable);
        verify(patientRepository).findAll(pageable);
        verify(mockDoctorPage).map(any());

    }



    // get patient by id

    @Test
    public void getDoctorById_ReturnDoctor(){
        Patient patient =new Patient();
        patient.setId(1L);
        patient.setEmail("d@gmail.com");
        Long id= patient.getId();
        PatientDTO patientDTO =new PatientDTO();
        patientDTO.setEmail("d@gmail.com");
        when(this.patientRepository.findById(id)).thenReturn(Optional.of(patient));
        when(this.userMapperDTO.toDTO(patient)).thenReturn(patientDTO);
        PatientDTO returnedPatient=this.patientServiceImp.getPatientById(id);
        assertEquals(returnedPatient.getEmail(), patient.getEmail());
        verify(patientRepository).findById(id);
    }

    @Test
    public void getDoctorById_NotExist_ThrowException(){
        Patient doctor=new Patient();
        doctor.setId(1L);
        doctor.setEmail("d@gmail.com");

        when(this.patientRepository.findById(2L)).thenReturn(Optional.empty());
        ResourcesNotFound resourcesNotFound=assertThrows(
                ResourcesNotFound.class,()->{
                    this.patientServiceImp.getPatientById(2L);
                }
        );
        assertEquals(resourcesNotFound.getMessage(),"The patient is not found");
        verify(patientRepository).findById(2L);
    }


    //update patient

    @Test
    public void updatePatient_returnDoctor(){
        // exist doctor
        Patient doctor =new Patient();
        doctor.setEmail("t@gmail.com");
        doctor.setUsername("tt");
        doctor.setFullName("Sawsan");

        //input patient

        PatientDTO patientDTO =new PatientDTO();
        patientDTO.setEmail("t@gmail.com");
        patientDTO.setUsername("tt");
        patientDTO.setFullName("Tojan");

        // update patient

        Patient updatedPatient =new Patient();
        updatedPatient.setEmail("t@gmail.com");
        updatedPatient.setUsername("tt");
        updatedPatient.setFullName("Tojan");

        when(this.patientRepository.findByEmail("t@gmail.com")).thenReturn(Optional.of(doctor));
        when(this.userMapperDTO.toDTO(any(Patient.class))).thenReturn(patientDTO);
        when(patientRepository.save(any(Patient.class))).thenReturn(updatedPatient);


        PatientDTO returnedDoctorDTO=this.patientServiceImp.updatePatient(1L, patientDTO);

        assertEquals(returnedDoctorDTO.getEmail(), patientDTO.getEmail());
        assertEquals(returnedDoctorDTO.getUsername(), patientDTO.getUsername());
        assertEquals(returnedDoctorDTO.getFullName(), patientDTO.getFullName());
        verify(patientRepository).findByEmail("t@gmail.com");

    }
    @Test
    public void updateNotExistPatient_returnException(){
        // exist doctor
        Patient doctor =new Patient();
        doctor.setEmail("t@gmail.com");
        doctor.setUsername("tt");
        doctor.setFullName("Sawsan");
        doctor.setId(1L);

        //input doctor

        PatientDTO patientDTO =new PatientDTO();
        patientDTO.setEmail("tt@gmail.com");
        patientDTO.setUsername("tt");
        patientDTO.setFullName("Tojan");


        when(this.patientRepository.findByEmail("tt@gmail.com")).thenReturn(Optional.empty());
        ResourcesNotFound resourcesNotFound=assertThrows(
                ResourcesNotFound.class,()->{
                    this.patientServiceImp.updatePatient(1L, patientDTO);
                }
        );
        assertEquals(resourcesNotFound.getMessage(),"The Patient is not found");
        verify(patientRepository).findByEmail("tt@gmail.com");

    }


}