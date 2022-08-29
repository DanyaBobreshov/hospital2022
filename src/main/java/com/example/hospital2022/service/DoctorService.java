package com.example.hospital2022.service;

import com.example.hospital2022.model.Doctor;
import com.example.hospital2022.repository.DoctorRepo;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@org.springframework.stereotype.Service
@RequiredArgsConstructor
public class DoctorService implements Service<Doctor> {
    private final DoctorRepo doctorRepo;

    @Override
    public List<Doctor> list(String name) {
        if (name!=null)return doctorRepo.findByUserSecondNameContains(name);
        else return doctorRepo.findAll();
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
    public Doctor findByUserLogin(String login){
        return doctorRepo.findByUserLoginContains(login).orElse(null);
    }

    public Doctor findByUserId(Long id){
        return doctorRepo.findByUserId(id).orElse(null);
    }

    public void correct(Doctor doctor, String name, String secondName, String fatherName,
                        String telephone, LocalDate dateOfBurn, String speciality, String cabinet){
        doctor.getUser().setName(name);
        doctor.getUser().setSecondName(secondName);
        doctor.getUser().setFatherName(fatherName);
        doctor.getUser().setTelephone(telephone);
        doctor.getUser().setDateOfBurn(dateOfBurn);
        doctor.setSpeciality(speciality);
        doctor.setCabinet(cabinet);
        save(doctor);
    }

    public List<Doctor> listForPatient(String titleName, String titleSpeciality) {
        if(titleName!=null&&titleSpeciality!=null) {
            return doctorRepo.findByUserSecondNameContainsAndSpecialityContains(titleName, titleSpeciality);
        }
        if(titleSpeciality!=null){
            return doctorRepo.findBySpecialityContains(titleSpeciality);
        }
        if (titleName!=null){
            return doctorRepo.findByUserSecondNameContains(titleName);
        }
        return doctorRepo.findAll();
    }
}
