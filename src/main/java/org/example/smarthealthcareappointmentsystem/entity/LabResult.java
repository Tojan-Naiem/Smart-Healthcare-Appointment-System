package org.example.smarthealthcareappointmentsystem.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Represent the lab result
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LabResult {
    private String id;
    private String testName;
    private String result;

}
