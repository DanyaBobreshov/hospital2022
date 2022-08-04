package com.example.hospital2022.controller;

import com.example.hospital2022.model.DayListAppointment;
import com.example.hospital2022.model.DayMatrixAppointment;
import com.example.hospital2022.model.Doctor;
import com.example.hospital2022.service.DayMatrixAppointmentService;
import com.example.hospital2022.service.DoctorService;
import com.example.hospital2022.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.time.DayOfWeek;
import java.time.LocalDate;

@Controller
@RequiredArgsConstructor
public class DoctorAppointmentController {
    private final UserService userService;
    private DayMatrixAppointment dayMatrixAppointment;
    private final DoctorService doctorService;
    private final DayMatrixAppointmentService dayMatrixAppointmentService;
    @GetMapping("/doctor-appointments/{id}")
    public String addDoctor(@RequestParam(name = "SearchWord", required = false)
                            Principal principal, Model model, @PathVariable("id") Long id) {
        Doctor doctor=doctorService.findById(id);
        LocalDate date=LocalDate.now();
        dayMatrixAppointment=dayMatrixAppointmentService.findByDoctor(doctor);
        if(dayMatrixAppointment==null){
            dayMatrixAppointment=new DayMatrixAppointment();
        }
       // if (date.getDayOfWeek().equals(DayOfWeek.SATURDAY)){
            dayMatrixAppointmentService.delete(dayMatrixAppointment);
            dayMatrixAppointment=new DayMatrixAppointment();
            dayMatrixAppointmentService.create(dayMatrixAppointment, date, null, doctor.getCabinet());
        //}
        DayListAppointment dayListAppointment1=dayMatrixAppointment.getDaysList().get(1);
        DayListAppointment dayListAppointment2=dayMatrixAppointment.getDaysList().get(2);
        DayListAppointment dayListAppointment3=dayMatrixAppointment.getDaysList().get(3);
        DayListAppointment dayListAppointment4=dayMatrixAppointment.getDaysList().get(4);
        DayListAppointment dayListAppointment5=dayMatrixAppointment.getDaysList().get(5);
        dayMatrixAppointment.getDaysList().add(dayListAppointment1);
        dayMatrixAppointment.getDaysList().add(dayListAppointment2);
        dayMatrixAppointment.getDaysList().add(dayListAppointment3);
        dayMatrixAppointment.getDaysList().add(dayListAppointment4);
        dayMatrixAppointment.getDaysList().add(dayListAppointment5);
        model.addAttribute("days", dayMatrixAppointment);
        model.addAttribute("user", userService.getUserByPrincipal(principal));
        model.addAttribute("day1",dayListAppointment1);
        model.addAttribute("day2",dayListAppointment2);
        model.addAttribute("day3",dayListAppointment3);
        model.addAttribute("day4",dayListAppointment4);
        model.addAttribute("day5",dayListAppointment5);
        return "doctor-appointments";
    }
}
