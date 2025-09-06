package org.example.smarthealthcareappointmentsystem.service;

import org.example.smarthealthcareappointmentsystem.dto.DoctorDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DoctorService {


    public void addDoctor(DoctorDTO registeredDoctor);
    public Page<DoctorDTO> getDoctors(Pageable pageable, String filterKey, String filterValue);
    public DoctorDTO getDoctorById(Long id);
    public DoctorDTO updateDoctor(Long id,DoctorDTO doctorDTO);
    public void deleteDoctor(Long id);
}
