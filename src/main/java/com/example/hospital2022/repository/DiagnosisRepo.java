package com.example.hospital2022.repository;

import com.example.hospital2022.model.Diagnosis;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DiagnosisRepo extends JpaRepository<Diagnosis, Long> {
    List<Diagnosis>findByTitleContains(String title);
    Optional<Diagnosis>findByTitle(String title);
}
