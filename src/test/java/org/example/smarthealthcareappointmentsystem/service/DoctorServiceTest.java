package org.example.smarthealthcareappointmentsystem.service;

import org.example.smarthealthcareappointmentsystem.dto.DoctorDTO;
import org.example.smarthealthcareappointmentsystem.dto.UserMapper;
import org.example.smarthealthcareappointmentsystem.exception.AlreadyExistsException;
import org.example.smarthealthcareappointmentsystem.exception.ResourcesNotFound;
import org.example.smarthealthcareappointmentsystem.model.Doctor;
import org.example.smarthealthcareappointmentsystem.repository.DoctorRepository;
import org.example.smarthealthcareappointmentsystem.service.Imp.DoctorServiceImp;
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
import static org.mockito.Mockito.*;

@SpringBootTest
class DoctorServiceTest {
    @InjectMocks
    private DoctorServiceImp doctorService;
    @Mock
    private DoctorRepository doctorRepository;
    @Mock
    private UserMapper userMapper;
    @Mock
    private PasswordEncoder passwordEncoder;

    // add doctor

    @Test
    public void addValidDoctor_thenCorrect(){
        //create a doctor dto to send it to the addDoctor method
        DoctorDTO doctorDTO=new DoctorDTO();
        doctorDTO.setName("test");
        doctorDTO.setEmail("test@gmail.com");
        doctorDTO.setPassword("test123");
        doctorDTO.setUsername("tst");
        doctorDTO.setSpecialty("Cardiology");

        // when they called in addDoctor
        when(doctorRepository.existsByEmail("test@gmail.com")).thenReturn(false);
        when(doctorRepository.existsByUsername("tst")).thenReturn(false);
        // create for the dto
        Doctor doctor=new Doctor();
        doctor.setEmail(doctorDTO.getEmail());
        doctor.setPassword(doctorDTO.getPassword());
        doctor.setSpecialty(doctorDTO.getSpecialty());
        doctor.setUsername(doctorDTO.getUsername());
        doctor.setName(doctorDTO.getName());


        when(userMapper.toEntity(doctorDTO)).thenReturn(doctor);
        when(passwordEncoder.encode("test123")).thenReturn("test123");


        doctorService.addDoctor(doctorDTO);
        ArgumentCaptor<Doctor>doctorCaptor=ArgumentCaptor.forClass(Doctor.class);

        verify(this.doctorRepository).save(doctorCaptor.capture());
        Doctor savedDoctor=doctorCaptor.getValue();
        assertEquals(savedDoctor.getPassword(),doctor.getPassword());
        assertEquals(savedDoctor.getEmail(),doctor.getEmail());

        assertEquals("DOCTOR",savedDoctor.getRole().getName());
    }
    @Test
    public void addVDoctor_WithExistEmail_throwException() {
        DoctorDTO doctorDTO=new DoctorDTO();
        doctorDTO.setName("test");
        doctorDTO.setEmail("test@gmail.com");
        doctorDTO.setPassword("test123");
        doctorDTO.setUsername("tst");
        doctorDTO.setSpecialty("Cardiology");

        // when they called in addDoctor
        when(doctorRepository.existsByEmail("test@gmail.com")).thenReturn(true);

        AlreadyExistsException exception = assertThrows(AlreadyExistsException.class, () -> {
            doctorService.addDoctor(doctorDTO);
        });
        assertEquals("The email is already exists",exception.getMessage());
    }
    @Test
    public void addVDoctor_WithExistUsername_throwException() {

        DoctorDTO doctorDTO=new DoctorDTO();
        doctorDTO.setName("test");
        doctorDTO.setEmail("test@gmail.com");
        doctorDTO.setPassword("test123");
        doctorDTO.setUsername("tst");
        doctorDTO.setSpecialty("Cardiology");

        // when they called in addDoctor
        when(doctorRepository.existsByUsername("tst")).thenReturn(true);
        AlreadyExistsException exception = assertThrows(AlreadyExistsException.class, () -> {
            doctorService.addDoctor(doctorDTO);
        });
        assertEquals("The username is already exists",exception.getMessage());
    }
    // get doctors

    @Test
    public void getDoctors_withoutFilters(){
        Pageable pageable= PageRequest.of(1,5);
        Page<Doctor>mockDoctorPage=mock(Page.class);
        when(this.doctorRepository.findAll(pageable)).thenReturn(mockDoctorPage);
        when(mockDoctorPage.map(any())).thenReturn(mock(Page.class));

        Page<DoctorDTO>doctorDTOS=this.doctorService.getDoctors(pageable,null,null);
        verify(doctorRepository).findAll(pageable);
        verify(doctorRepository, never()).findBySpecialty(any(), any());
        verify(mockDoctorPage).map(any());

    }
    @Test
    public void getDoctors_withSpecialtyFilter_ReturnFilterDoctors(){
        Pageable pageable= PageRequest.of(0,5);
        Page<Doctor>mockDoctorPage=mock(Page.class);
        when(this.doctorRepository.findBySpecialty("Cardiology",pageable)).thenReturn(mockDoctorPage);
        when(mockDoctorPage.map(any())).thenReturn(mock(Page.class));

        Page<DoctorDTO>doctorDTOS=this.doctorService.getDoctors(pageable,"specialty","Cardiology");
        verify(doctorRepository).findBySpecialty("Cardiology",pageable);
        verify(mockDoctorPage).map(any());

    }
    @Test
    public void getDoctors_withFilterKeyOnly_ReturnAllDoctors(){
        Pageable pageable= PageRequest.of(0,5);
        Page<Doctor>mockDoctorPage=mock(Page.class);
        when(this.doctorRepository.findAll(pageable)).thenReturn(mockDoctorPage);
        when(mockDoctorPage.map(any())).thenReturn(mock(Page.class));

        Page<DoctorDTO>doctorDTOS=this.doctorService.getDoctors(pageable,"specialty",null);
        verify(doctorRepository).findAll(pageable);
        verify(mockDoctorPage).map(any());

    }
    @Test
    public void getDoctors_withFilterValueOnly_ReturnAllDoctors(){
        Pageable pageable= PageRequest.of(0,5);
        Page<Doctor>mockDoctorPage=mock(Page.class);
        when(this.doctorRepository.findAll(pageable)).thenReturn(mockDoctorPage);
        when(mockDoctorPage.map(any())).thenReturn(mock(Page.class));

        Page<DoctorDTO>doctorDTOS=this.doctorService.getDoctors(pageable,null,"Cardiology");
        verify(doctorRepository).findAll(pageable);
        verify(mockDoctorPage).map(any());

    }

    // get doctor by id

    @Test
    public void getDoctorById_ReturnDoctor(){
        Doctor doctor=new Doctor();
        doctor.setId(1L);
        doctor.setEmail("d@gmail.com");
        Long id=doctor.getId();
        DoctorDTO doctorDTO=new DoctorDTO();
        doctorDTO.setEmail("d@gmail.com");
        when(this.doctorRepository.findById(id)).thenReturn(Optional.of(doctor));
        when(this.userMapper.toDTO(doctor)).thenReturn(doctorDTO);
        DoctorDTO returnedDoctor=this.doctorService.getDoctorById(id);
        assertEquals(returnedDoctor.getEmail(),doctor.getEmail());
        verify(doctorRepository).findById(id);
    }

    @Test
    public void getDoctorById_NotExist_ThrowException(){
        Doctor doctor=new Doctor();
        doctor.setId(1L);
        doctor.setEmail("d@gmail.com");

        when(this.doctorRepository.findById(2L)).thenReturn(Optional.empty());
        ResourcesNotFound resourcesNotFound=assertThrows(
                ResourcesNotFound.class,()->{
                    this.doctorService.getDoctorById(2L);
                }
        );
        assertEquals(resourcesNotFound.getMessage(),"The id doctor not found");
        verify(doctorRepository).findById(2L);
    }

    //update doctor

    @Test
    public void updateDoctor_returnDoctor(){
        // exist doctor
        Doctor doctor=new Doctor();
        doctor.setEmail("t@gmail.com");
        doctor.setUsername("tt");
        doctor.setName("Sawsan");

        //input doctor

        DoctorDTO doctorDTO=new DoctorDTO();
        doctorDTO.setEmail("t@gmail.com");
        doctorDTO.setUsername("tt");
        doctorDTO.setName("Tojan");

        // update doctor

        Doctor updatedDoctor=new Doctor();
        updatedDoctor.setEmail("t@gmail.com");
        updatedDoctor.setUsername("tt");
        updatedDoctor.setName("Tojan");

        when(this.doctorRepository.findByEmail("t@gmail.com")).thenReturn(Optional.of(doctor));
        when(this.userMapper.toDTO(any(Doctor.class))).thenReturn(doctorDTO);
        when(doctorRepository.save(any(Doctor.class))).thenReturn(updatedDoctor);


        DoctorDTO returnedDoctorDTO=this.doctorService.updateDoctor(1L,doctorDTO);

        assertEquals(returnedDoctorDTO.getEmail(),doctorDTO.getEmail());
        assertEquals(returnedDoctorDTO.getUsername(),doctorDTO.getUsername());
        assertEquals(returnedDoctorDTO.getName(),doctorDTO.getName());
        verify(doctorRepository).findByEmail("t@gmail.com");

    }
    @Test
    public void updateNotExistDoctor_returnException(){
        // exist doctor
        Doctor doctor=new Doctor();
        doctor.setEmail("t@gmail.com");
        doctor.setUsername("tt");
        doctor.setName("Sawsan");
        doctor.setId(1L);

        //input doctor

        DoctorDTO doctorDTO=new DoctorDTO();
        doctorDTO.setEmail("tt@gmail.com");
        doctorDTO.setUsername("tt");
        doctorDTO.setName("Tojan");


        when(this.doctorRepository.findByEmail("tt@gmail.com")).thenReturn(Optional.empty());
        ResourcesNotFound resourcesNotFound=assertThrows(
                ResourcesNotFound.class,()->{
                    this.doctorService.updateDoctor(1L,doctorDTO);
                }
        );
        assertEquals(resourcesNotFound.getMessage(),"The doctor is not found");
        verify(doctorRepository).findByEmail("tt@gmail.com");

    }
    // delete doctor
    @Test
    public void deleteDoctor_returnCorrect(){
        Doctor doctor=new Doctor();
        doctor.setEmail("t@gmail.com");
        doctor.setUsername("tt");
        doctor.setName("Sawsan");
        doctor.setId(1L);

        when(this.doctorRepository.findById(1L)).thenReturn(Optional.of(doctor));
        doNothing().when(doctorRepository).delete(doctor);

        assertDoesNotThrow(()->this.doctorService.deleteDoctor(1L));
        verify(this.doctorRepository).findById(1L);

    }
    @Test
    public void deleteDoctor_throwException(){
        Doctor doctor=new Doctor();
        doctor.setEmail("t@gmail.com");
        doctor.setUsername("tt");
        doctor.setName("Sawsan");
        doctor.setId(1L);

        when(this.doctorRepository.findById(2L)).thenReturn(Optional.empty());
        ResourcesNotFound resourcesNotFound=assertThrows(
                ResourcesNotFound.class,()->{
                    this.doctorService.deleteDoctor(2L);
                }
        );
        assertEquals(resourcesNotFound.getMessage(),"Doctor id is not found");
        verify(this.doctorRepository).findById(2L);

    }

}