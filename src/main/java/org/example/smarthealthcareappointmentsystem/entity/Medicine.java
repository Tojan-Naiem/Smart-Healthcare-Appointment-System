package org.example.smarthealthcareappointmentsystem.entity;

/**
 * Represent the Medicine
 */
public class Medicine{
    private String id;
    private String medicineName;
    private String dosage;

    public Medicine(){

    }
    public Medicine(String medicineName,String dosage){
      this.medicineName=medicineName;
      this.dosage=dosage;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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