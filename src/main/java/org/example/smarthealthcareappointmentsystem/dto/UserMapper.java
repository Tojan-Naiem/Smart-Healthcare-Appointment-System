package org.example.smarthealthcareappointmentsystem.dto;

import org.example.smarthealthcareappointmentsystem.model.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    DoctorDTO toDTO(Doctor doctor);

    @Mapping(target = "password", ignore = true)
    Doctor toEntity(DoctorDTO doctorDTO);
    PatientDTO toDTO(Patient patient);

    @Mapping(target = "password", ignore = true)
    Patient toEntity(PatientDTO patientDTO);
    AppointmentDTO toDTO(Appointment appointment);

    Appointment toEntity(AppointmentDTO appointmentDTO);


    @Mapping(target = "id", ignore = true)
    Prescription toEntity(PrescriptionDTO prescriptionDTO);

    PrescriptionDTO toDTO(Prescription prescription);

    // Medicine mapping
    Medicine toMedicineEntity(MedicineDTO medicineDTO);
    MedicineDTO toMedicineDTO(Medicine medicine);

    // List mappings
    List<Medicine> toMedicineEntities(List<MedicineDTO> medicineDTOs);
    List<MedicineDTO> toMedicineDTOs(List<Medicine> medicines);
}
