package org.example.smarthealthcareappointmentsystem.controller;

import org.example.smarthealthcareappointmentsystem.dto.DoctorDTO;
import org.example.smarthealthcareappointmentsystem.service.Imp.DoctorServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST Controller for handling Doctor operations.
 * Provides endpoints for create/get/getById/update/delete doctors
 */
@RestController
@RequestMapping("/api/v1/doctor")
public class DoctorController {
    private final DoctorServiceImp doctorService;
    public DoctorController(DoctorServiceImp doctorService){
        this.doctorService=doctorService;
    }

    /**
     * Get all doctors
     * @param page the page
     * @param size the number of entity
     * @param filterKey if there's a filter
     * @param filterValue if there's a filter
     * @return Page of the entities
     */
    //{{Jurl}}/doctor/?filterKey=specialty&filterValue=aloah

    @GetMapping("/")
    public ResponseEntity<List<DoctorDTO>> getDoctors(
            @RequestParam(value = "page", required = false,defaultValue = "0")int page,
            @RequestParam(value = "size",required = false,defaultValue = "5")int size,
            @RequestParam(value="filterKey",required = false)String filterKey,
            @RequestParam(value = "filterValue",required = false)String filterValue
    ){
        Pageable pageable= PageRequest.of(page,size);
        Page<DoctorDTO> doctorDTOS= this.doctorService.getDoctors(pageable,filterKey,filterValue);
        return ResponseEntity.ok(doctorDTOS.getContent());
    }

    /**
     * Get doctor by id
     * @param id for the doctor
     * @return the doctorDTO
     */
    @GetMapping("/{id}")
    public ResponseEntity<DoctorDTO> getDoctorById(@PathVariable Long id){
        return ResponseEntity.ok(this.doctorService.getDoctorById(id));
    }

    /**
     * Create a new doctor
     * @param doctorDTO that received
     * @return message that successfully creating the doctor
     */
    @PostMapping("/")
    public ResponseEntity<?> addDoctor(@RequestBody DoctorDTO doctorDTO){
        System.out.println("Hello");
         this.doctorService.addDoctor(doctorDTO);
         return new ResponseEntity<>("Successfully create the doctor account !", HttpStatus.CREATED);
    }

    /**
     * Update a doctor
     * @param id the id for the doctor
     * @param doctorDTO the info wanna update it
     * @return the doctorDTO with updated info
     */
    @PutMapping("/{id}")
    public ResponseEntity<DoctorDTO> updateDoctor(@PathVariable Long id,@RequestBody DoctorDTO doctorDTO){
        return ResponseEntity.ok(this.doctorService.updateDoctor(id,doctorDTO));
    }

    /**
     * Delete doctor by id
     * @param id for the doctor
     * @return message
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDoctor(@PathVariable Long id){
        this.doctorService.deleteDoctor(id);
        return ResponseEntity.ok("Successfully deleted the doctor");
    }

}
