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
import java.util.Optional;

@org.springframework.stereotype.Service
@RequiredArgsConstructor
public class AppointmentService implements Service<Appointment> {
    private final AppointmentRepo appointmentRepo;
    private final DoctorService doctorService;
    private final PatientService patientService;

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
        appointment.getDoctor().getAppointments().remove(appointment);
        appointment.getPatient().getAppointments().remove(appointment);
        doctorService.save(appointment.getDoctor());
        patientService.save(appointment.getPatient());
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

    public List<Appointment> findByDoctorAndDate(Doctor doctor, LocalDate varDate) {
        return appointmentRepo.findByDoctorAndDate(doctor,varDate);
    }

    public Optional<Appointment> findByDoctorAndDateAndTime(Doctor doctor, LocalDate date, LocalTime time) {
        return appointmentRepo.findByDoctorAndDateAndTime(doctor,date,time);
    }

    public void setAppointment(Appointment appointment, LocalDate localDate, LocalTime localTime, Doctor doctor, Patient patient) {
        appointment.setPatient(patient);
        appointment.setCabinet(doctor.getCabinet());
        appointment.setTime(localTime);
        appointment.setDate(localDate);
        appointment.setDoctor(doctor);
        doctor.getAppointments().add(appointment);
        patient.getAppointments().add(appointment);
        save(appointment);
        doctorService.save(doctor);
        patientService.save(patient);
    }
}
