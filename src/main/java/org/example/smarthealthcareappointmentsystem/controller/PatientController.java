package org.example.smarthealthcareappointmentsystem.controller;

import org.example.smarthealthcareappointmentsystem.dto.PatientDTO;
import org.example.smarthealthcareappointmentsystem.service.Imp.PatientServiceImp;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/patient")
public class PatientController {

    private PatientServiceImp patientServiceImp;
    public PatientController(PatientServiceImp patientServiceImp){
        this.patientServiceImp = patientServiceImp;
    }
    @PostMapping("/")
    public ResponseEntity<?> addPatient(@RequestBody PatientDTO patientDTO){
        this.patientServiceImp.addPatient(patientDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @GetMapping("/")
    public ResponseEntity<Page<PatientDTO>> getPatients(
            @RequestParam(value = "page", required = false , defaultValue = "0")int page,
            @RequestParam(value = "size",required = false,defaultValue = "5")int size
    ){
        Pageable pageable= PageRequest.of(page,size);
        Page<PatientDTO> patientDTOS= this.patientServiceImp.getPatients(pageable);
        return ResponseEntity.ok(patientDTOS);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatientDTO> getPatientById(@PathVariable Long id){
        return ResponseEntity.ok(this.patientServiceImp.getPatientById(id));
    }
    @PutMapping("/{id}")
    public ResponseEntity<PatientDTO> updatePatient(@PathVariable Long id,@RequestBody PatientDTO patientDTO){
        return ResponseEntity.ok(this.patientServiceImp.updatePatient(id,patientDTO));
    }


}
