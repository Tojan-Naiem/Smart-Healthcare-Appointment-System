package org.example.smarthealthcareappointmentsystem.dto;

import jakarta.persistence.Column;

import java.time.LocalDate;

/**
 * Represents Doctor dto
 */
public class DoctorDTO {
    @Column(length = 50,nullable = false)
    private String fullName;
    @Column(nullable = false,unique = true)
    private String username;
    @Column(nullable = false,unique = true)
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private LocalDate birthday;
    @Column(nullable = false,length = 255)
    private String specialty;
    public DoctorDTO(){

    }

    public DoctorDTO(String fullName, String username, String email, String password, LocalDate birthday, String specialty) {
     this.birthday=birthday;
     this.fullName = fullName;
     this.email=email;
     this.password=password;
     this.specialty=specialty;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }
    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
