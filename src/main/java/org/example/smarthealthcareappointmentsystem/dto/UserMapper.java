package org.example.smarthealthcareappointmentsystem.dto;

import org.example.smarthealthcareappointmentsystem.model.Doctor;
import org.example.smarthealthcareappointmentsystem.model.Patient;
import org.example.smarthealthcareappointmentsystem.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    DoctorDTO toDTO(Doctor doctor);

    @Mapping(target = "password", ignore = true)
    Doctor toEntity(DoctorDTO doctorDTO);
    PatientDTO toDTO(Patient patient);

    @Mapping(target = "password", ignore = true)
    Patient toEntity(PatientDTO patientDTO);
}
