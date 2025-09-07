package org.example.smarthealthcareappointmentsystem.controller;

import org.example.smarthealthcareappointmentsystem.dto.AppointmentDTO;
import org.example.smarthealthcareappointmentsystem.dto.UpdateAppointmentStatusRequest;
import org.example.smarthealthcareappointmentsystem.service.AppointmentService;
import org.example.smarthealthcareappointmentsystem.service.Imp.AppointmentServiceImp;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/appointment")
public class AppointmentController {
    private AppointmentServiceImp appointmentService;

    public AppointmentController(AppointmentServiceImp appointmentService) {
        this.appointmentService = appointmentService;
    }

    @PostMapping("/")
    public ResponseEntity<AppointmentDTO> addAppointment(@RequestBody AppointmentDTO appointmentDTO) {
        AppointmentDTO appointmentDTO1 = this.appointmentService.createAppointment(appointmentDTO);
        return new ResponseEntity<>(
                appointmentDTO1,
                HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?>updateAppointmentStatus(
            @PathVariable Long id,
            @RequestBody UpdateAppointmentStatusRequest request
    ){
        System.out.println("IN");
        this.appointmentService.updateAppointment(id,request.getStatus());
        return ResponseEntity.ok("Successfully updated appointment");
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAppointment(@PathVariable Long id) {
        this.appointmentService.deleteAppointment(id);
        return ResponseEntity.ok("Successfully deleted appointment");
    }
}