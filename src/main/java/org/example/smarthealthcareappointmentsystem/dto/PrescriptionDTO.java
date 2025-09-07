package org.example.smarthealthcareappointmentsystem.dto;

import jakarta.persistence.Id;
import org.example.smarthealthcareappointmentsystem.model.Prescription;

import java.time.LocalDate;
import java.util.List;

public class PrescriptionDTO {

    private Long patientId;
    private Long doctorId;
    private List<Medicine> medicines;
    private String instructions;
    private LocalDate issueDate;



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

    public List<Medicine> getMedicines() {
        return medicines;
    }

    public void setMedicines(List<Medicine> medicines) {
        this.medicines = medicines;
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

    private class Medicine{
        private String medicineName;
        private String dosage;
    }
}
