package com.example.hospital2022.service;

import com.example.hospital2022.model.*;
import com.example.hospital2022.repository.DayListAppointmentRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DayListAppointmentService implements com.example.hospital2022.service.Service<DayListAppointment> {
    private final DayListAppointmentRepo dayListAppointmentRepo;
    private final AppointmentService appointmentService;
    private final MatrixAppointmentsService matrixAppointmentsService;
    @Override
    public List<DayListAppointment> list(String title) {
        return dayListAppointmentRepo.findAll();
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
                if(appointment.getDate().isBefore(LocalDate.now()))
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
            System.out.println(time);
            appointment.setDate(date);
            //appointment.setPatient(patient.orElse(null));
            appointment.setDoctor(doctor.orElse(null));
            appointment.setCabinet(cabinet);
            appointmentService.save(appointment);
            dayListAppointment.getAppointments().add(appointment);
            time=time.plusMinutes(30);
        }
        save(dayListAppointment);

    }

    public DayListAppointment findByDateDay(LocalDate date) {
        return null;
    }

    public List<DayListAppointment> makeNewDayListAppointment(LocalDate date, Doctor doctor, MatrixAppointments matrixAppointments) {
        DayListAppointment dayListAppointment1=new DayListAppointment();
        create(dayListAppointment1,"понедельник", date=date.plusDays(2),
                Optional.ofNullable(doctor), null, doctor.getCabinet());
        DayListAppointment dayListAppointment2=new DayListAppointment();
        create(dayListAppointment2,"вторник", date=date.plusDays(1),
                Optional.ofNullable(doctor), null, doctor.getCabinet());
        DayListAppointment dayListAppointment3=new DayListAppointment();
        create(dayListAppointment3,"среда", date=date.plusDays(1),
                Optional.ofNullable(doctor), null, doctor.getCabinet());
        DayListAppointment dayListAppointment4=new DayListAppointment();
        create(dayListAppointment4,"четверг", date=date.plusDays(1),
                Optional.ofNullable(doctor), null, doctor.getCabinet());
        DayListAppointment dayListAppointment5=new DayListAppointment();
        create(dayListAppointment5,"пятница", date=date.plusDays(1),
                Optional.ofNullable(doctor), null, doctor.getCabinet());

        ArrayList<DayListAppointment> dayList=new ArrayList<>();
        dayList.add(dayListAppointment1);
        dayList.add(dayListAppointment2);
        dayList.add(dayListAppointment3);
        dayList.add(dayListAppointment4);
        dayList.add(dayListAppointment5);
        matrixAppointmentsService.saveMatrix(dayList, matrixAppointments);
        return dayList;
    }
}
