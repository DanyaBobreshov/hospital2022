package com.example.hospital2022.service;

import com.example.hospital2022.model.Appointment;
import com.example.hospital2022.repository.AppointmentRepo;
import lombok.RequiredArgsConstructor;

import java.util.List;

@org.springframework.stereotype.Service
@RequiredArgsConstructor
public class AppointmentService implements Service<Appointment> {
    private final AppointmentRepo appointmentRepo;

    @Override
    public List<Appointment> list(String title) {
        return appointmentRepo.findByDoctorUserSecondNameContains(title);
    }

    public List<Appointment> listPatient(Long patientId){
        return appointmentRepo.findByPatientId(patientId);
    }

    @Override
    public Appointment findById(Long id) {
        return appointmentRepo.findById(id).orElse(null);
    }

    @Override
    public void save(Appointment appointment) {
        appointmentRepo.save(appointment);
    }

    @Override
    public void delete(Appointment appointment) {
        appointmentRepo.delete(appointment);
    }

    @Override
    public Appointment findByTitle(String title) {
        return null;
    }

    public Appointment findByExamination(Long id){
        return appointmentRepo.findByExaminationId(id).orElse(null);
    }
}
