package com.example.hospital2022.repository;

import com.example.hospital2022.model.DayListAppointment;
import com.example.hospital2022.model.Doctor;
import com.example.hospital2022.model.MatrixAppointments;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MatrixAppointmentsRepo extends JpaRepository<MatrixAppointments, Long> {

    Optional<MatrixAppointments> findByDoctor(Doctor doctor);
}
