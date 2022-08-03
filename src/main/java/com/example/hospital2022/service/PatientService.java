package com.example.hospital2022.service;

import com.example.hospital2022.model.Patient;
import com.example.hospital2022.repository.PatientRepo;
import lombok.RequiredArgsConstructor;

import java.util.List;

@org.springframework.stereotype.Service
@RequiredArgsConstructor
public class PatientService implements Service<Patient> {
    private final PatientRepo patientRepo;
    @Override
    public List<Patient> list(String title) {
        if(title!=null)
        return patientRepo.findByUserSecondNameContains(title);
        return patientRepo.findAll();
    }
    public List<Patient> list(String name, String secondName) {
        if(name!=null||secondName!=null)
        return patientRepo.findByUserSecondNameContainsAndUserNameContains(secondName,name);
        return patientRepo.findAll();
    }
    @Override
    public Patient findById(Long id) {
        return patientRepo.findById(id).orElse(null);
    }

    @Override
    public void save(Patient patient) {
        patientRepo.save(patient);
    }

    @Override
    public void delete(Patient patient) {
        patientRepo.delete(patient);
    }

    @Override
    public Patient findByTitle(String title) {
        return null;
    }

    public void correct(Patient patient, String name, String secondName, String fatherName,
                        String telephone){
        patient.getUser().setName(name);
        patient.getUser().setSecondName(secondName);
        patient.getUser().setFatherName(fatherName);
        patient.getUser().setTelephone(telephone);
        save(patient);
    }
}
