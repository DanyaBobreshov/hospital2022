package com.example.hospital2022.service;

import com.example.hospital2022.model.DayListAppointment;
import com.example.hospital2022.model.DayMatrixAppointment;
import com.example.hospital2022.model.Doctor;
import com.example.hospital2022.model.Patient;
import com.example.hospital2022.repository.DayListAppointmentRepo;
import com.example.hospital2022.repository.DayMatrixAppointmentRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DayMatrixAppointmentService implements com.example.hospital2022.service.Service<DayMatrixAppointment> {
    private final DayListAppointmentService dayListAppointmentService;
    private final DayMatrixAppointmentRepo dayMatrixAppointmentRepo;
    @Override
    public List<DayMatrixAppointment> list(String title) {
        return null;
    }

    @Override
    public DayMatrixAppointment findById(Long id) {
        return dayMatrixAppointmentRepo.findById(id).orElse(null);
    }

    @Override
    public void save(DayMatrixAppointment dayMatrixAppointment) {
        dayMatrixAppointmentRepo.save(dayMatrixAppointment);
    }

    @Override
    public void delete(DayMatrixAppointment dayMatrixAppointment) {
        for (DayListAppointment dayListAppointment:dayMatrixAppointment.getDaysList()){
            dayListAppointmentService.delete(dayListAppointment);
        }
        dayMatrixAppointmentRepo.delete(dayMatrixAppointment);
    }

    @Override
    public DayMatrixAppointment findByTitle(String title) {
        return null;
    }

    public DayMatrixAppointment findByDoctor(Doctor doctor) {
        return dayMatrixAppointmentRepo.findByDoctor(doctor).orElse(null);
    }

    public void create(DayMatrixAppointment dayMatrixAppointment, LocalDate date,
                       Optional<Patient> patient, String cabinet){
        Optional<Doctor> doctor= Optional.ofNullable(dayMatrixAppointment.getDoctor());
        ArrayList<String>days=new ArrayList<>();
        String monday="понедельник";
        days.add(monday);
        String tuesday="вторник";
        days.add(tuesday);
        String wednesday="среда";
        days.add(wednesday);
        String thursday="четверг";
        days.add(thursday);
        String friday="пятница";
        days.add(friday);
        DayListAppointment dayListAppointment;
        for(int i=0; i<5; i++){
            dayListAppointment=new DayListAppointment();
            dayListAppointmentService.create(dayListAppointment, days.get(i), date, doctor, patient,cabinet);
            date.plusDays(1);
        }

    }
}
