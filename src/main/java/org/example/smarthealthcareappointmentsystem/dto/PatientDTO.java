package org.example.smarthealthcareappointmentsystem.dto;

import jakarta.persistence.Column;

import java.time.LocalDate;

/**
 * Represents Patient dto
 */
public class PatientDTO {
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

    public PatientDTO(){

    }

    public PatientDTO(String fullName, String username, String email, String password, LocalDate birthday) {
        this.birthday=birthday;
        this.fullName=fullName;
        this.email=email;
        this.password=password;

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

    public void setFullName(String name) {
        this.fullName = name;
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
