package org.example.smarthealthcareappointmentsystem.service;

import org.example.smarthealthcareappointmentsystem.dto.PatientDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PatientService {
    public void addPatient(PatientDTO registeredPatient);
    public Page<PatientDTO> getPatients(Pageable pageable);
    public PatientDTO getPatientById(Long id);
    public PatientDTO updatePatient(Long id,PatientDTO patientDTO);
}
