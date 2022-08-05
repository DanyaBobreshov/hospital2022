package com.example.hospital2022.service;

import com.example.hospital2022.model.DayListAppointment;
import com.example.hospital2022.model.Doctor;
import com.example.hospital2022.model.MatrixAppointments;
import com.example.hospital2022.repository.MatrixAppointmentsRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MatrixAppointmentsService implements com.example.hospital2022.service.Service<MatrixAppointments> {
    private final MatrixAppointmentsRepo matrixAppointmentsRepo;
    private final DoctorService doctorService;

    @Override
    public List<MatrixAppointments> list(String title) {
        return null;
    }

    public MatrixAppointments findByDoctor(Doctor doctor){
        return matrixAppointmentsRepo.findByDoctor(doctor).orElse(null);
    }

    @Override
    public MatrixAppointments findById(Long id) {
        return matrixAppointmentsRepo.findById(id).orElse(null);
    }

    @Override
    public void save(MatrixAppointments matrixAppointments) {
        matrixAppointmentsRepo.save(matrixAppointments);
    }

    @Override
    public void delete(MatrixAppointments matrixAppointments) {
        matrixAppointmentsRepo.delete(matrixAppointments);
    }

    @Override
    public MatrixAppointments findByTitle(String title) {
        return null;
    }

    public void saveMatrix(List<DayListAppointment> dayList, MatrixAppointments matrixAppointments){
        matrixAppointments.setDays(dayList);
        save(matrixAppointments);
    }
}
