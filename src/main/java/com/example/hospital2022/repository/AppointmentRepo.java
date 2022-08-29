package com.example.hospital2022.repository;

import com.example.hospital2022.model.Appointment;
import com.example.hospital2022.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

public interface AppointmentRepo extends JpaRepository<Appointment, Long> {
    List<Appointment>findByDoctorUserSecondNameContains(String doctorSecondName);
    List<Appointment>findByPatientId(Long id);
    Optional<Appointment>findByExaminationId(Long id);

    List<Appointment> findByDoctorAndDate(Doctor doctor, LocalDate varDate);
    Optional<Appointment> findByDoctorAndDateAndTime(Doctor doctor, LocalDate date, LocalTime time);
}
