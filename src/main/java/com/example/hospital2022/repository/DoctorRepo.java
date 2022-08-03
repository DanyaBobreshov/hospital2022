package com.example.hospital2022.repository;

import com.example.hospital2022.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DoctorRepo extends JpaRepository<Doctor, Long> {
    List<Doctor> findByUserSecondNameContains(String secondName);
    Optional<Doctor> findByUserLoginContains(String login);
    Optional<Doctor> findByUserId(Long id);
}
