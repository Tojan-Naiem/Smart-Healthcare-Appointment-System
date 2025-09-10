package org.example.smarthealthcareappointmentsystem.mapper;

import org.example.smarthealthcareappointmentsystem.dto.*;
import org.example.smarthealthcareappointmentsystem.entity.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    /**
     * Converts an {@link  Doctor} to an {@link DoctorDTO}.
     * @param doctor the entity to be converted
     * @return the corresponding dto
     */
    DoctorDTO toDTO(Doctor doctor);
    /**
     * Converts an {@link  DoctorDTO} to an {@link Doctor}.
     * @param doctorDTO the dto to be converted
     * @return the corresponding entity
     */
    @Mapping(target = "password", ignore = true)
    Doctor toEntity(DoctorDTO doctorDTO);
    /**
     * Converts an {@link  Patient} to an {@link PatientDTO}.
     * @param patient the entity to be converted
     * @return the corresponding dto
     */
    PatientDTO toDTO(Patient patient);
    /**
     * Converts an {@link  PatientDTO} to an {@link Patient}.
     * @param patientDTO the dto to be converted
     * @return the corresponding entity
     */
    @Mapping(target = "password", ignore = true)
    Patient toEntity(PatientDTO patientDTO);
    /**
     * Converts an {@link  Appointment} to an {@link AppointmentDTO}.
     * @param appointment the entity to be converted
     * @return the corresponding dto
     */
    AppointmentDTO toDTO(Appointment appointment);
    /**
     * Converts an {@link  AppointmentDTO} to an {@link Appointment}.
     * @param appointmentDTO the dto to be converted
     * @return the corresponding entity
     */
    Appointment toEntity(AppointmentDTO appointmentDTO);


    /**
     * Converts an {@link  PrescriptionDTO} to an {@link Prescription}.
     * @param prescriptionDTO the dto to be converted
     * @return the corresponding entity
     */
    @Mapping(target = "id", ignore = true)
    Prescription toEntity(PrescriptionDTO prescriptionDTO);
    /**
     * Converts an {@link  Prescription} to an {@link PrescriptionDTO}.
     * @param prescription the entity to be converted
     * @return the corresponding dto
     */
    PrescriptionDTO toDTO(Prescription prescription);
    /**
     * Converts an {@link  MedicineDTO} to an {@link Medicine}.
     * @param medicineDTO the dto to be converted
     * @return the corresponding entity
     */
    // Medicine mapping
    Medicine toMedicineEntity(MedicineDTO medicineDTO);
    /**
     * Converts an {@link  Medicine} to an {@link MedicineDTO}.
     * @param medicine the entity to be converted
     * @return the corresponding dto
     */
    MedicineDTO toMedicineDTO(Medicine medicine);
//    /**
//     * Converts an {@link  MedicineDTO} to  a list{@link Medicine}.
//            * @param medicineDTOs the dto to be converted
//     * @return a list of corresponding entity
//     */
//    // List mappings
//    List<Medicine> toMedicineEntities(List<MedicineDTO> medicineDTOs);
//    /**
//     * Converts an {@link  Medicine} to a list  {@link MedicineDTO}.
//     * @param medicines the entity to be converted
//     * @return a list of the corresponding dto
//     */
//    List<MedicineDTO> toMedicineDTOs(List<Medicine> medicines);
//
//    /**
//     * Converts an {@link  LabResultDTO} to a list{@link LabResult}.
//     * @param labResultDTOS the dto to be converted
//     * @return a list of corresponding entity
//     */
//    // List mappings
//    List<LabResult> toLabResultEntities(List<LabResultDTO> labResultDTOS);
//    /**
//     * Converts an {@link  LabResult} to a list  {@link LabResultDTO}.
//     * @param labResults the entity to be converted
//     * @return a list of the corresponding dto
//     */
//    List<LabResultDTO> toLabResultDTOs(List<LabResult> labResults);

    /**
     * Converts an {@link  LabResultDTO} to an {@link LabResult}.
     * @param labResultDTO the dto to be converted
     * @return the corresponding entity
     */
    // Medicine mapping
    LabResult toLabResultEntity(LabResultDTO labResultDTO);
    /**
     * Converts an {@link  LabResult} to an {@link LabResultDTO}.
     * @param labResult the entity to be converted
     * @return the corresponding dto
     */
    LabResultDTO toLabResultDTO(LabResult labResult);


}
