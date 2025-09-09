package org.example.smarthealthcareappointmentsystem.dto;

import lombok.*;

import java.time.LocalDateTime;

/**
 * Represent an appointment dto
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentDTO {

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
