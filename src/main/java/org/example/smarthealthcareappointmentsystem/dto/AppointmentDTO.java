package org.example.smarthealthcareappointmentsystem.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
public class AppointmentDTO {
    private Long id;

    private Long patientId;
    private Long doctorId;

    private String patientName;
    private String doctorName;
    private String doctorSpecialty;

    private LocalDateTime appointmentDateTime;
    private String status;
    private String notes;
    private Integer duration;


}
