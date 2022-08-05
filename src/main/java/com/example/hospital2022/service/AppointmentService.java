package com.example.hospital2022.service;

import com.example.hospital2022.model.Appointment;
import com.example.hospital2022.model.Doctor;
import com.example.hospital2022.model.Examination;
import com.example.hospital2022.model.Patient;
import com.example.hospital2022.repository.AppointmentRepo;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@org.springframework.stereotype.Service
@RequiredArgsConstructor
public class AppointmentService implements Service<Appointment> {
    private final AppointmentRepo appointmentRepo;

    Boolean isActive(Appointment appointment){
        if(appointment.getExamination()==null){
            return false;
        }
        return true;
    }

    Boolean isActual(Appointment appointment){
        if(!appointment.getDate().isAfter(LocalDate.now())){
            return false;
        }
        return true;
    }

    @Override
    public List<Appointment> list(String title) {
      if(title!=null)  return appointmentRepo.findByDoctorUserSecondNameContains(title);
      else return appointmentRepo.findAll();

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

    public void correct(Appointment appointment, LocalDate date, LocalTime time, String cabinet){
        appointment.setDate(date);
        appointment.setTime(time);
        appointment.setCabinet(cabinet);
        save(appointment);
    }
}
