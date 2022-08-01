package com.example.hospital2022.service;

import com.example.hospital2022.model.Diagnosis;
import com.example.hospital2022.repository.DiagnosisRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DiagnosisService implements com.example.hospital2022.service.Service<Diagnosis> {
    private final DiagnosisRepo diagnosisRepo;

    @Override
    public List<Diagnosis> list(String title) {
        return diagnosisRepo.findByTitleContains(title);
    }

    @Override
    public Diagnosis findById(Long id) {
        return diagnosisRepo.findById(id).orElse(null);
    }

    @Override
    public void save(Diagnosis diagnosis) {
        diagnosisRepo.save(diagnosis);
    }

    @Override
    public void delete(Diagnosis diagnosis) {
        diagnosisRepo.delete(diagnosis);
    }

    @Override
    public Diagnosis findByTitle(String title) {
        return diagnosisRepo.findByTitle(title).orElse(null);
    }
}
