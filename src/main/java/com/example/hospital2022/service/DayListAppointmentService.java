package com.example.hospital2022.service;

import com.example.hospital2022.model.Appointment;
import com.example.hospital2022.model.DayListAppointment;
import com.example.hospital2022.model.Doctor;
import com.example.hospital2022.model.Patient;
import com.example.hospital2022.repository.DayListAppointmentRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DayListAppointmentService implements com.example.hospital2022.service.Service<DayListAppointment> {
    private final DayListAppointmentRepo dayListAppointmentRepo;
    private final AppointmentService appointmentService;
    @Override
    public List<DayListAppointment> list(String title) {
        return null;
    }

    @Override
    public DayListAppointment findById(Long id) {
        return dayListAppointmentRepo.findById(id).orElse(null);
    }

    @Override
    public void save(DayListAppointment dayListAppointment) {
        dayListAppointmentRepo.save(dayListAppointment);
    }

    @Override
    public void delete(DayListAppointment dayListAppointment) {
        for(Appointment appointment:dayListAppointment.getAppointments()){
            if(appointment.getExamination()==null){
                appointmentService.delete(appointment);
            }
        }
        dayListAppointmentRepo.delete(dayListAppointment);
    }

    @Override
    public DayListAppointment findByTitle(String title) {
        return null;
    }

    public void create(DayListAppointment dayListAppointment, String titleDay, LocalDate date,
                       Optional<Doctor> doctor, Optional<Patient> patient, String cabinet){
        dayListAppointment.setTitleDay(titleDay);
        dayListAppointment.setDateDay(date);
        LocalTime time=LocalTime.of(9,0);
        Appointment appointment;
        for(int i=0; i<19; i++){
            appointment=new Appointment();
            appointment.setTime(time);
            appointment.setDate(date);
            //appointment.setPatient(patient.orElse(null));
            appointment.setDoctor(doctor.orElse(null));
            appointment.setCabinet(cabinet);
            appointmentService.save(appointment);
            dayListAppointment.getAppointments().add(appointment);
            time.plusMinutes(30);
        }

    }

    public DayListAppointment findByDateDay(LocalDate date) {
        return null;
    }
}
