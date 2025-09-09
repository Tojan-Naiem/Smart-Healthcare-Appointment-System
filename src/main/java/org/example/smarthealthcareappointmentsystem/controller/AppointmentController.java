package org.example.smarthealthcareappointmentsystem.controller;

import org.example.smarthealthcareappointmentsystem.dto.AppointmentDTO;
import org.example.smarthealthcareappointmentsystem.dto.UpdateAppointmentStatusRequest;
import org.example.smarthealthcareappointmentsystem.service.AppointmentService;
import org.example.smarthealthcareappointmentsystem.service.Imp.AppointmentServiceImp;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
/**
 * REST controller for handling appointment operations
 * Provide endpoints for create/update/delete appointment
 */
@RestController
@RequestMapping("/api/v1/appointment")
public class AppointmentController {
    private final AppointmentServiceImp appointmentService;

    public AppointmentController(AppointmentServiceImp appointmentService) {
        this.appointmentService = appointmentService;
    }

    /**
     * Create appointment
     * @param appointmentDTO for create
     * @return msg
     */

    @PostMapping("/")
    public ResponseEntity<?> addAppointment(@RequestBody AppointmentDTO appointmentDTO) {
      this.appointmentService.createAppointment(appointmentDTO);
        return new ResponseEntity<>(
                "Successfully created the appointment!",
                HttpStatus.CREATED);
    }

    /**
     * Update appointment status
     * @param id the appointment id
     * @param request the updated stats
     * @return msg
     */

    @PutMapping("/{id}")
    public ResponseEntity<?>updateAppointmentStatus(
            @PathVariable Long id,
            @RequestBody UpdateAppointmentStatusRequest request
    ){
        this.appointmentService.updateAppointment(id,request.getStatus());
        return ResponseEntity.ok("Successfully updated the appointment!");
    }

    /**
     * Delete appointment
     * @param id the appointment
     * @return msg
     */


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAppointment(@PathVariable Long id) {
        this.appointmentService.deleteAppointment(id);
        return ResponseEntity.ok("Successfully deleted the appointment!");
    }
}