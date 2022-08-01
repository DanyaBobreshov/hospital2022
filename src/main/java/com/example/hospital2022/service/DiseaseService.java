package com.example.hospital2022.service;

import com.example.hospital2022.model.Disease;
import com.example.hospital2022.repository.DiseaseRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DiseaseService implements com.example.hospital2022.service.Service<Disease> {
    private final DiseaseRepo diseaseRepo;
    @Override
    public List<Disease> list(String title) {
        return diseaseRepo.findByTitleContains(title);
    }
    public List<Disease> diagnosisTitleList(String title){
        return diseaseRepo.findByDiagnosisTitleContains(title);
    }

    public List<Disease> diagnosisList(Long diagnosisId){
        return diseaseRepo.findByDiagnosisId(diagnosisId);
    }

    public List<Disease> patientTitleList(String title){
        return diseaseRepo.findByPatientUserSecondNameContains(title);
    }

    public List<Disease> patientList(Long id){
        return diseaseRepo.findByPatientId(id);
    }

    @Override
    public Disease findById(Long id) {
        return diseaseRepo.findById(id).orElse(null);
    }

    @Override
    public void save(Disease disease) {
        diseaseRepo.save(disease);
    }

    @Override
    public void delete(Disease disease) {
        diseaseRepo.delete(disease);
    }

    @Override
    public Disease findByTitle(String title) {
        return null;
    }
}
