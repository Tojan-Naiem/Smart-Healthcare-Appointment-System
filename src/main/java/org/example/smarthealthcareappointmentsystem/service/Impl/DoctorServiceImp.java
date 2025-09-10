package org.example.smarthealthcareappointmentsystem.service.Impl;

import org.example.smarthealthcareappointmentsystem.mapper.UserMapperDTO;
import org.example.smarthealthcareappointmentsystem.dto.DoctorDTO;
import org.example.smarthealthcareappointmentsystem.exception.AlreadyExistsException;
import org.example.smarthealthcareappointmentsystem.exception.ResourcesNotFound;
import org.example.smarthealthcareappointmentsystem.entity.Doctor;
import org.example.smarthealthcareappointmentsystem.entity.Role;
import org.example.smarthealthcareappointmentsystem.repository.DoctorRepository;
import org.example.smarthealthcareappointmentsystem.service.DoctorService;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Implementation of the {@link DoctorService} interface
 * Provides business logic for managing {@link Doctor}entity
 */
@Service
public class DoctorServiceImp implements DoctorService {

    private final DoctorRepository doctorRepository;
    private final UserMapperDTO userMapperDTO;
    private final PasswordEncoder passwordEncoder;
    public DoctorServiceImp(DoctorRepository doctorRepository, UserMapperDTO userMapperDTO, PasswordEncoder passwordEncoder){
        this.doctorRepository=doctorRepository;
        this.userMapperDTO = userMapperDTO;
        this.passwordEncoder=passwordEncoder;
    }

    /**
     * Create doctor account
     * Check if the email is already used or not and the username and if it is throw an exception
     * @param registeredDoctor contains doctor info
     */
    @CachePut(value="doctors",key="#root.methodName")
    public void addDoctor(DoctorDTO registeredDoctor){
        boolean isExistByEmail=this.doctorRepository.existsByEmail(registeredDoctor.getEmail());
        boolean isExistByUsername=this.doctorRepository.existsByUsername(registeredDoctor.getUsername());

        if(isExistByEmail){
            throw new AlreadyExistsException("The email is already exists");
        }
        if(isExistByUsername){
            throw new AlreadyExistsException("The username is already exists");
        }
        Doctor doctor= userMapperDTO.toEntity(registeredDoctor);
        doctor.setPassword(passwordEncoder.encode(registeredDoctor.getPassword()));
        doctor.setRole(new Role(4,"DOCTOR"));
        doctor.setSpecialty(registeredDoctor.getSpecialty());
        this.doctorRepository.save(doctor);
    }

    /**
     * Get all doctors
     * @param pageable the page and size
     * @param filterKey to filters the result
     * @param filterValue to filters the result
     * @return a list of page contains the doctorDTO
     */
    @Override
    @Cacheable(value="doctors",key="#root.methodName+'_'+#pageable.pageNumber+'_'+#pageable.pageSize+'_'+#filterKey+'_'+#filterValue")
    public Page<DoctorDTO> getDoctors(Pageable pageable,String filterKey,String filterValue){
        Page<Doctor> doctors=null;

        //check if there's any filters from the user
        System.out.println(filterKey+" "+filterValue);

        if(filterKey!=null&&filterValue!=null&&!filterKey.isEmpty()&&!filterValue.isEmpty()){
            if (filterKey.equals("specialty")) {
                System.out.println("IN");
                doctors = this.doctorRepository.findBySpecialty(filterValue, pageable);
            } else {
                doctors = this.doctorRepository.findAll(pageable);
            }
        }
        else doctors =this.doctorRepository.findAll(pageable);
        return doctors.map(
                userMapperDTO::toDTO
        );
    }

    /**
     * Get Doctor by id
     * check if the id is in the db , if it's not throw an exception
     * @param id for the doctor
     * @return doctorDTO
     */
    @Cacheable(value="doctors",key="#root.methodName+'_'+#id")

    public DoctorDTO getDoctorById(Long id){
       return userMapperDTO.toDTO(
               this.doctorRepository.findById(id).orElseThrow(
                       ()-> new ResourcesNotFound("The id doctor not found")
               )
       );
    }

    /**
     * Update doctor
     * check if the doctor is their or not
     * @param id for the doctor
     * @param doctorDTO the updated doctor
     * @return the doctorDTO
     */
    public DoctorDTO updateDoctor(Long id,DoctorDTO doctorDTO){
        Optional<Doctor> existsDoctor=this.doctorRepository.findByEmail(doctorDTO.getEmail());
        if(existsDoctor.isEmpty()){
            throw new ResourcesNotFound("The doctor is not found");
        }
        Doctor updatedDoctor=existsDoctor.get();
        updatedDoctor.setFullName(doctorDTO.getFullName());
        updatedDoctor.setSpecialty(doctorDTO.getSpecialty());
        updatedDoctor.setBirthday(doctorDTO.getBirthday());
        updatedDoctor.setPassword(doctorDTO.getPassword());

        this.doctorRepository.save(updatedDoctor);
        return userMapperDTO.toDTO(updatedDoctor);
    }

    /**
     * Delete doctor
     * check if the doctor is in or not and if it's not throw an exception
     * @param id for the doctor
     */

    public void deleteDoctor(Long id){
        Doctor existsDoctor=this.doctorRepository.findById(id).orElseThrow(
                ()->{throw  new ResourcesNotFound("Doctor id is not found");}
        );
        this.doctorRepository.delete(existsDoctor);
    }
}
