package org.example.smarthealthcareappointmentsystem.service;

import org.example.smarthealthcareappointmentsystem.dto.PrescriptionDTO;

import java.util.List;
/**
 * Patient Service interface for {@link org.example.smarthealthcareappointmentsystem.entity.Prescription}
 */
public interface PrescriptionService {
    public void createPrescription(PrescriptionDTO prescriptionDTO);
    public List<PrescriptionDTO> getPrescriptionsForPatient();
}
