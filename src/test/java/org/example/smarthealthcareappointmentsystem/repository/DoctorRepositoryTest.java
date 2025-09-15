package org.example.smarthealthcareappointmentsystem.repository;

import org.example.smarthealthcareappointmentsystem.entity.Doctor;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
@ActiveProfiles("test")
class DoctorRepositoryTest {
    @Autowired
    private DoctorRepository doctorRepository;
    @BeforeEach
    @AfterEach
    public void setUP(){
        doctorRepository.deleteAll();
        Doctor doctor1 = new Doctor();
        doctor1.setFullName("Ahmed");
        doctor1.setUsername("ahmed123");
        doctor1.setEmail("ahmed@example.com");
        doctor1.setPassword("password1");
        doctor1.setBirthday(LocalDate.of(1990, 5, 12));
        doctor1.setSpecialty("Cardiology");

        Doctor doctor2 = new Doctor();
        doctor2.setFullName("Sara");
        doctor2.setUsername("sara90");
        doctor2.setEmail("sara@example.com");
        doctor2.setPassword("password2");
        doctor2.setBirthday(LocalDate.of(1992, 3, 25));
        doctor2.setSpecialty("Neurology");

        Doctor doctor3 = new Doctor();
        doctor3.setFullName("Omar");
        doctor3.setUsername("omar77");
        doctor3.setEmail("omar@example.com");
        doctor3.setPassword("password3");
        doctor3.setBirthday(LocalDate.of(1988, 8, 10));
        doctor3.setSpecialty("Dermatology");

        doctorRepository.saveAll(List.of(doctor1, doctor2, doctor3));

    }
    @Test
    public void existsByEmail_correct(){
        boolean exist=doctorRepository.existsByEmail("ahmed@example.com");
        assertTrue(exist);

    }
    @Test
    public void notExistsByEmail_correct(){
        boolean exist=doctorRepository.existsByEmail("toji@example.com");
        assertFalse(exist);

    }

}