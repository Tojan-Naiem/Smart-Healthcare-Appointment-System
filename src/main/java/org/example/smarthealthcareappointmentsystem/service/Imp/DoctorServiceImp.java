package org.example.smarthealthcareappointmentsystem.service.Imp;

import org.example.smarthealthcareappointmentsystem.dto.UserMapper;
import org.example.smarthealthcareappointmentsystem.dto.DoctorDTO;
import org.example.smarthealthcareappointmentsystem.exception.AlreadyExistsException;
import org.example.smarthealthcareappointmentsystem.exception.ResourcesNotFound;
import org.example.smarthealthcareappointmentsystem.entity.Doctor;
import org.example.smarthealthcareappointmentsystem.entity.Role;
import org.example.smarthealthcareappointmentsystem.repository.DoctorRepository;
import org.example.smarthealthcareappointmentsystem.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DoctorServiceImp implements DoctorService {
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public void addDoctor(DoctorDTO registeredDoctor){
        boolean isExistByEmail=this.doctorRepository.existsByEmail(registeredDoctor.getEmail());
        boolean isExistByUsername=this.doctorRepository.existsByUsername(registeredDoctor.getUsername());

        if(isExistByEmail){
            throw new AlreadyExistsException("The email is already exists");
        }
        if(isExistByUsername){
            throw new AlreadyExistsException("The username is already exists");
        }
        Doctor doctor= userMapper.toEntity(registeredDoctor);
        doctor.setPassword(passwordEncoder.encode(registeredDoctor.getPassword()));
        doctor.setRole(new Role(4,"DOCTOR"));
        doctor.setSpecialty(registeredDoctor.getSpecialty());
        this.doctorRepository.save(doctor);
    }

    public Page<DoctorDTO> getDoctors(Pageable pageable,String filterKey,String filterValue){
        Page<Doctor> doctors=null;
        if(filterKey!=null&&filterValue!=null&&!filterKey.isEmpty()&&!filterValue.isEmpty()){
            switch (filterKey){
                case "specialty":
                    doctors =this.doctorRepository.findBySpecialty(filterValue,pageable);
                    break;
                default:
                    doctors =this.doctorRepository.findAll(pageable);
                    break;
            }
        }
        else doctors =this.doctorRepository.findAll(pageable);


        return doctors.map(
                d-> userMapper.toDTO(d)
        );
    }
    public DoctorDTO getDoctorById(Long id){
       Doctor doctor= this.doctorRepository.findById(id).orElseThrow(
                ()-> {throw new ResourcesNotFound("The id doctor not found");}
        );
       return userMapper.toDTO(doctor);
    }
    public DoctorDTO updateDoctor(Long id,DoctorDTO doctorDTO){
        Optional<Doctor> existsDoctor=this.doctorRepository.findByEmail(doctorDTO.getEmail());
        if(existsDoctor.isEmpty()){
            throw new ResourcesNotFound("The doctor is not found");
        }
        Doctor updatedDoctor=existsDoctor.get();
        updatedDoctor.setName(doctorDTO.getName());
        updatedDoctor.setSpecialty(doctorDTO.getSpecialty());
        updatedDoctor.setBirthday(doctorDTO.getBirthday());
        updatedDoctor.setPassword(doctorDTO.getPassword());

        this.doctorRepository.save(updatedDoctor);
        return userMapper.toDTO(updatedDoctor);
    }
    public void deleteDoctor(Long id){
        Doctor existsDoctor=this.doctorRepository.findById(id).orElseThrow(
                ()->{throw  new ResourcesNotFound("Doctor id is not found");}
        );
        this.doctorRepository.delete(existsDoctor);
    }
}
