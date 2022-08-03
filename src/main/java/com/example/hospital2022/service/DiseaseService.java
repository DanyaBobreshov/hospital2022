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
        if(diagnosisId!=null)
        return diseaseRepo.findByDiagnosisId(diagnosisId);
        return diseaseRepo.findAll();
    }

    public List<Disease> patientTitleList(String title){
        if(title!=null)
        return diseaseRepo.findByPatientUserSecondNameContains(title);
        return diseaseRepo.findAll();
    }

    public List<Disease> patientList(Long id){
        if(id!=null)
        return diseaseRepo.findByPatientId(id);
        return diseaseRepo.findAll();

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
