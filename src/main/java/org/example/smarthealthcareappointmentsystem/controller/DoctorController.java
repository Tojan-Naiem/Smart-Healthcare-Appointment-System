package org.example.smarthealthcareappointmentsystem.controller;

import org.example.smarthealthcareappointmentsystem.dto.DoctorDTO;
import org.example.smarthealthcareappointmentsystem.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.print.Doc;

@RestController
@RequestMapping("/api/v1/doctor")
public class DoctorController {
    @Autowired
    private DoctorService doctorService;

    @GetMapping("/")
    public ResponseEntity getDoctors(
            @RequestParam(value = "page", required = false)int page,
            @RequestParam(value = "size",required = false)int size,
            @RequestParam(value="filterKey",required = false)String filterKey,
            @RequestParam(value = "filterValue",required = false)String filterValue
    ){
        Pageable pageable= PageRequest.of(page,size);
        Page<DoctorDTO> doctorDTOS= this.doctorService.getDoctors(pageable,filterKey,filterValue);
        return ResponseEntity.ok(doctorDTOS);
    }
    @GetMapping("/{id}")
    public ResponseEntity<DoctorDTO> getDoctorById(@PathVariable Long id){
        return ResponseEntity.ok(this.doctorService.getDoctorById(id));
    }

    @PostMapping("/")
    public ResponseEntity addDoctor(@RequestBody DoctorDTO doctorDTO){
         this.doctorService.addDoctor(doctorDTO);
         return ResponseEntity.ok("Successfully create the doctor!");
    }
    @PutMapping("/{id}")
    public ResponseEntity updateDoctor(@RequestBody DoctorDTO doctorDTO){
        return ResponseEntity.ok(this.doctorService.updateDoctor(doctorDTO));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity deleteDoctor(@PathVariable Long id){
        this.doctorService.deleteDoctor(id);
        return ResponseEntity.ok("Successfully deleted the doctor");
    }

}
