package com.example.hospital2022.repository;

import com.example.hospital2022.model.Examination;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ExaminationRepo extends JpaRepository<Examination, Long> {
    List<Examination> findByDiseaseId(Long diseaseId);
    Optional<Examination> findByAppointmentId(Long appointmentId);
}
