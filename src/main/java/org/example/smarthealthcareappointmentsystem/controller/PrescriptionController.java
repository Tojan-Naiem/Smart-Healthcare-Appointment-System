package org.example.smarthealthcareappointmentsystem.controller;

import org.example.smarthealthcareappointmentsystem.dto.PrescriptionDTO;
import org.example.smarthealthcareappointmentsystem.service.Impl.PrescriptionServiceImp;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *   REST controller for handling prescription operations
 *  Provide endpoints for create/get prescription
 */
@RestController
@RequestMapping("/api/v1/prescription")
public class PrescriptionController {
    private final PrescriptionServiceImp prescriptionService;
    public PrescriptionController(PrescriptionServiceImp prescriptionService){
        this.prescriptionService=prescriptionService;
    }

    /**
     * Represent create prescription
     * @param prescriptionDTO for patient
     * @return created
     */
    @PostMapping("/")
    public ResponseEntity<?> createPrescription(@RequestBody PrescriptionDTO prescriptionDTO)
    {
        this.prescriptionService.createPrescription(prescriptionDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();

    }

    /**
     * Represent the get prescriptions for the patient
     * @return the list of prescriptions dto
     */

    @GetMapping("/")
    public ResponseEntity<List<PrescriptionDTO>> getPrescriptionsForPatient(){

        return new ResponseEntity<>(
                this.prescriptionService.getPrescriptionsForPatient(),
                HttpStatus.OK
        );
    }
}
