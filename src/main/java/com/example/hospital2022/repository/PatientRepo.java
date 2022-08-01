package com.example.hospital2022.repository;

import com.example.hospital2022.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PatientRepo extends JpaRepository<Patient, Long> {
    List<Patient> findByUserSecondNameContains(String secondName);
    List<Patient> findByUserSecondNameContainsAndUserNameContains(String secondName, String name);
    Optional<Patient> findByUserLoginContains(String login);


}
