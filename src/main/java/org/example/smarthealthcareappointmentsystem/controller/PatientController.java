package org.example.smarthealthcareappointmentsystem.controller;

import org.example.smarthealthcareappointmentsystem.dto.PatientDTO;
import org.example.smarthealthcareappointmentsystem.service.Imp.PatientServiceImp;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * REST Controller for handling Patients operations.
 * Provides endpoints for create/get/getById/update/delete patients
 */
@RestController
@RequestMapping("/api/v1/patient")
public class PatientController {

    private PatientServiceImp patientServiceImp;
    public PatientController(PatientServiceImp patientServiceImp){
        this.patientServiceImp = patientServiceImp;
    }

    /**
     * Create patient account
     * @param patientDTO for creating the account
     * @return msg successfully and created status code
     */

    @PostMapping("/")
    public ResponseEntity<?> addPatient(@RequestBody PatientDTO patientDTO) {
        this.patientServiceImp.addPatient(patientDTO);
        return new ResponseEntity<>("Successfully created patient account!", HttpStatus.CREATED);
    }

    /**
     * Get All patients
     * @param page of patients
     * @param size of patients
     * @return page of a list patients
     */
    @GetMapping("/")
    public ResponseEntity<Page<PatientDTO>> getPatients(
            @RequestParam(value = "page", required = false , defaultValue = "0")int page,
            @RequestParam(value = "size",required = false,defaultValue = "5")int size
    ){
        Pageable pageable= PageRequest.of(page,size);
        Page<PatientDTO> patientDTOS= this.patientServiceImp.getPatients(pageable);
        return ResponseEntity.ok(patientDTOS);
    }

    /**
     * Get Patient by id
     * @param id patient id
     * @return patient dto
     */

    @GetMapping("/{id}")
    public ResponseEntity<PatientDTO> getPatientById(@PathVariable Long id){
        return ResponseEntity.ok(this.patientServiceImp.getPatientById(id));
    }

    /**
     * Update patient info
     * @param id for patient
     * @param patientDTO the updated patient info
     * @return the updated patient
     */
    @PutMapping("/{id}")
    public ResponseEntity<PatientDTO> updatePatient(@PathVariable Long id,@RequestBody PatientDTO patientDTO){
        return ResponseEntity.ok(this.patientServiceImp.updatePatient(id,patientDTO));
    }


}
