package org.example.smarthealthcareappointmentsystem.dto;

import java.time.LocalDate;

public class DoctorDTO {
    private String name;
    private String username;
    private String email;
    private String password;
    private LocalDate birthday;
    private String specialty;
    public DoctorDTO(){

    }

    public DoctorDTO(String name, String username, String email, String password, LocalDate birthday, String specialty) {
     this.birthday=birthday;
     this.name=name;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
