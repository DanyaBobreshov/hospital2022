package com.example.hospital2022.service;

import com.example.hospital2022.model.Examination;
import com.example.hospital2022.repository.ExaminationRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExaminationService implements com.example.hospital2022.service.Service<Examination> {
    private final ExaminationRepo examinationRepo;
    @Override
    public List<Examination> list(String title) {
        return null;
    }

    public List<Examination> diseaseList(Long diseaseId){
        return examinationRepo.findByDiseaseId(diseaseId);
    }

    @Override
    public Examination findById(Long id) {
        return examinationRepo.findById(id).orElse(null);
    }

    @Override
    public void save(Examination examination) {
        examinationRepo.save(examination);
    }

    @Override
    public void delete(Examination examination) {
        examinationRepo.delete(examination);
    }

    @Override
    public Examination findByTitle(String title) {
        return null;
    }
}
