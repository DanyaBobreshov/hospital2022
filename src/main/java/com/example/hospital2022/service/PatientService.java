package com.example.hospital2022.service;

import com.example.hospital2022.model.Doctor;
import com.example.hospital2022.model.Patient;
import com.example.hospital2022.repository.PatientRepo;
import com.example.hospital2022.repository.UserRepo;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@org.springframework.stereotype.Service
@RequiredArgsConstructor
public class PatientService implements Service<Patient> {
    private final PatientRepo patientRepo;
    private final UserRepo userRepo;
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

    public Patient findByUserId(Long id){
        return patientRepo.findByUserId(id).orElse(null);
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

    public Patient findByUserLogin(String login) {
        return patientRepo.findByUserLoginContains(login).orElse(null);
    }

    public void correct(Patient patient, String name, String secondName, String fatherName,
                        LocalDate dateOfBurn, String telephone){
        patient.getUser().setName(name);
        patient.getUser().setSecondName(secondName);
        patient.getUser().setFatherName(fatherName);
        patient.getUser().setTelephone(telephone);
        patient.getUser().setDateOfBurn(dateOfBurn);
        userRepo.save(patient.getUser());
        save(patient);
    }

    public List<Patient> findByDoctor(Doctor doctor) {
        return patientRepo.findByDoctors(doctor);
    }

    public List<Patient> findByDoctorAndTitleContains(Doctor doctor, String title) {
        List<Patient>patients;
        if(title==null){
            patients= findByDoctor(doctor);
        }else {
            patients = patientRepo.findByDoctorsAndUserSecondNameContains(doctor, title);
        }
        Set<Patient> set = new HashSet<>(patients);
        patients.clear();
        patients.addAll(set);
        return patients;
    }
}
