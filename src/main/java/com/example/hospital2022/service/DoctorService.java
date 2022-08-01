package com.example.hospital2022.service;

import com.example.hospital2022.model.Doctor;
import com.example.hospital2022.repository.DoctorRepo;
import lombok.RequiredArgsConstructor;

import java.util.List;

@org.springframework.stereotype.Service
@RequiredArgsConstructor
public class DoctorService implements Service<Doctor> {
    private final DoctorRepo doctorRepo;

    @Override
    public List<Doctor> list(String name) {
        return doctorRepo.findByUserSecondNameContains(name);
    }

    @Override
    public Doctor findById(Long id) {
        return doctorRepo.findById(id).orElse(null);
    }

    @Override
    public void save(Doctor doctor) {
        doctorRepo.save(doctor);
    }

    @Override
    public void delete(Doctor doctor) {
        doctorRepo.delete(doctor);
    }

    @Override
    public Doctor findByTitle(String title) {
        return null;
    }

    public void correct(Doctor doctor, String name, String secondName, String fatherName,
                        String telephone, String speciality){
        doctor.getUser().setName(name);
        doctor.getUser().setSecondName(secondName);
        doctor.getUser().setFatherName(fatherName);
        doctor.getUser().setTelephone(telephone);
        doctor.setSpeciality(speciality);
        save(doctor);
    }

}
