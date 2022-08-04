package com.example.hospital2022.repository;

import com.example.hospital2022.model.DayListAppointment;
import com.example.hospital2022.model.DayMatrixAppointment;
import com.example.hospital2022.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DayMatrixAppointmentRepo extends JpaRepository<DayMatrixAppointment, Long> {
    Optional<DayMatrixAppointment>findByDoctor(Doctor doctor);
}
