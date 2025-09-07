package org.example.smarthealthcareappointmentsystem.controller;

import org.example.smarthealthcareappointmentsystem.dto.PrescriptionDTO;
import org.example.smarthealthcareappointmentsystem.repository.PrescriptionRepository;
import org.example.smarthealthcareappointmentsystem.service.Imp.PrescriptionServiceImp;
import org.example.smarthealthcareappointmentsystem.service.PrescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/prescription")
public class PrescriptionController {
    private PrescriptionServiceImp prescriptionService;
    public PrescriptionController(PrescriptionServiceImp prescriptionService){
        this.prescriptionService=prescriptionService;
    }
    @PostMapping("/")
    public ResponseEntity createPrescription(@RequestBody PrescriptionDTO prescriptionDTO)
    {
        System.out.println("HHHI");
        this.prescriptionService.createPrescription(prescriptionDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();

    }
    @PostMapping("/s")
    public ResponseEntity create(){
        return ResponseEntity.ok("gfsdfgas");
    }

    @GetMapping("/")
    public ResponseEntity getPrescriptionsForPatient(){

        return new ResponseEntity<>(
                this.prescriptionService.getPrescriptionsForPatient(),
                HttpStatus.OK
        );
    }
}
