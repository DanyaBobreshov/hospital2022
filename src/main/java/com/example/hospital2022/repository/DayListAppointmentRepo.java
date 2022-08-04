package com.example.hospital2022.repository;

import com.example.hospital2022.model.DayListAppointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface DayListAppointmentRepo extends JpaRepository<DayListAppointment, Long> {
    Optional<DayListAppointment> findByDateDay(LocalDate date);
}
