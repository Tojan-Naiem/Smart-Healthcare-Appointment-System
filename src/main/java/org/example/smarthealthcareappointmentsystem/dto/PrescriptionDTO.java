package org.example.smarthealthcareappointmentsystem.dto;

import java.time.LocalDate;
import java.util.List;

public class PrescriptionDTO {

    private Long patientId;
    private Long doctorId;
    private List<MedicineDTO> medicineDTOS;
    private String instructions;
    private LocalDate issueDate;
    private List<LabResultDTO> labResultDTO;



    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public Long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
    }

    public List<MedicineDTO> getMedicines() {
        return medicineDTOS;
    }

    public void setMedicines(List<MedicineDTO> medicineDTOS) {
        this.medicineDTOS = medicineDTOS;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
    }

    public List<MedicineDTO> getMedicineDTOS() {
        return medicineDTOS;
    }

    public void setMedicineDTOS(List<MedicineDTO> medicineDTOS) {
        this.medicineDTOS = medicineDTOS;
    }

    public List<LabResultDTO> getLabResultDTO() {
        return labResultDTO;
    }

    public void setLabResultDTO(List<LabResultDTO> labResultDTO) {
        this.labResultDTO = labResultDTO;
    }
}
