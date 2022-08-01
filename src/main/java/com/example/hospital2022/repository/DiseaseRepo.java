package com.example.hospital2022.repository;

import com.example.hospital2022.model.Disease;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DiseaseRepo extends JpaRepository<Disease, Long> {
    List<Disease> findByTitleContains(String title);
    List<Disease> findByDiagnosisTitleContains(String diagnosisTitle);
    List<Disease> findByDiagnosisId(Long diagnosisId);
    List<Disease> findByPatientUserSecondNameContains(String patientSecondName);
    List<Disease> findByPatientId(Long patientId);

}
