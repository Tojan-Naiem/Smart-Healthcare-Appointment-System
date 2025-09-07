package org.example.smarthealthcareappointmentsystem.dto;

public class MedicineDTO {

    private String medicineName;
    private String dosage;

    public MedicineDTO(){

    }
    public MedicineDTO(String medicineName,String dosage){
        this.medicineName=medicineName;
        this.dosage=dosage;
    }


    public String getMedicineName() {
        return medicineName;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }
}
