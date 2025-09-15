package org.example.smarthealthcareappointmentsystem.dto;

public class UpdateAppointmentStatusRequest {
    private String status;
    public UpdateAppointmentStatusRequest(String status){
        this.status=status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
