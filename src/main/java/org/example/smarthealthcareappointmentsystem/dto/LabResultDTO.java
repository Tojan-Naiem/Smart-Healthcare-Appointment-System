package org.example.smarthealthcareappointmentsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Represent the lab result dto
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LabResultDTO {
    private String testName;
    private String result;

}
