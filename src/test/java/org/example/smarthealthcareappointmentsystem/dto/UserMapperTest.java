package org.example.smarthealthcareappointmentsystem.dto;

import org.example.smarthealthcareappointmentsystem.entity.Doctor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class UserMapperTest {
   @Autowired
    private UserMapper userMapper;
   @Test
   public void givenDoctorToDoctorDTO_whenMaps_thenCorrect(){
       Doctor doctor=new Doctor();
       doctor.setFullName("Tojan");
       doctor.setEmail("t@gmail.com");
       DoctorDTO doctorDTO=userMapper.toDTO(doctor);
       assertEquals(doctorDTO.getFullName(),doctor.getFullName());
       assertEquals(doctorDTO.getEmail(),doctor.getEmail());

   }
   @Test
   public void givenDoctorDTOToDoctor_whenMaps_thenCorrect(){
       DoctorDTO doctorDTO=new DoctorDTO();
       doctorDTO.setFullName("Tojan");
       doctorDTO.setEmail("t@gmail.com");
       Doctor doctor=userMapper.toEntity(doctorDTO);
       assertEquals(doctorDTO.getFullName(),doctor.getFullName());
       assertEquals(doctorDTO.getEmail(),doctor.getEmail());
   }
}