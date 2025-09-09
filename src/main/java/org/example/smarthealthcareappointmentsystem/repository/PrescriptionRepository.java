package org.example.smarthealthcareappointmentsystem.repository;

import org.example.smarthealthcareappointmentsystem.entity.Prescription;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PrescriptionRepository extends MongoRepository<Prescription,String> {
    List<Prescription> findByPatientId(Long patientId);

}
