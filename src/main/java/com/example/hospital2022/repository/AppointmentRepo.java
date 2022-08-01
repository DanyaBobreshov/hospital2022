package com.example.hospital2022.repository;

import com.example.hospital2022.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AppointmentRepo extends JpaRepository<Appointment, Long> {
    List<Appointment>findByDoctorUserSecondNameContains(String doctorSecondName);
    List<Appointment>findByPatientId(Long id);
    Optional<Appointment>findByExaminationId(Long id);
}
