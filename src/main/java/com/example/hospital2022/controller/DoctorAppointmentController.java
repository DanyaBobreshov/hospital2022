package com.example.hospital2022.controller;

import com.example.hospital2022.model.Appointment;
import com.example.hospital2022.model.Doctor;
import com.example.hospital2022.model.User;
import com.example.hospital2022.service.AppointmentService;
import com.example.hospital2022.service.DoctorService;
import com.example.hospital2022.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.sql.Date;
import java.sql.Time;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class DoctorAppointmentController {
    private final UserService userService;
    private final DoctorService doctorService;
    private final AppointmentService appointmentService;
    @GetMapping("doctor-appointments/{id}")
    public String doctorAppoints (Principal principal, Model model, @PathVariable("id") Long id){
        Doctor doctor =doctorService.findById(id);
        LocalDate date=LocalDate.now();
        LocalDate varDate=LocalDate.now();
        if(varDate.getDayOfWeek().equals(DayOfWeek.SATURDAY)){
           varDate= varDate.plusDays(2);
        }
        if (varDate.getDayOfWeek().equals(DayOfWeek.SUNDAY)){
            varDate= varDate.plusDays(1);
        }
        List<Appointment>appointmentList;
        for(int i=1; i<8; i++){
            appointmentList=appointmentService.findByDoctorAndDate(doctor, varDate);
            model.addAttribute("appointments"+i,appointmentList);
            model.addAttribute("date"+i, varDate);
            varDate=varDate.plusDays(1);
            if(varDate.getDayOfWeek().equals(DayOfWeek.SATURDAY)){
                varDate=  varDate.plusDays(2);
            }
            if (varDate.getDayOfWeek().equals(DayOfWeek.SUNDAY)){
                varDate=  varDate.plusDays(1);
            }
        }
        model.addAttribute("user", userService.getUserByPrincipal(principal));
        model.addAttribute("doctorName", doctor.getUser().getSecondName());
        model.addAttribute("date", date);
        return "doctor-appointments";
    }

    @GetMapping("/doctor-appointmentEdit/{id}")
    public String addDoctorsEdit(@PathVariable("id") Long id, Model model, Principal principal) {
        Doctor doctor = doctorService.findById(id);
        model.addAttribute("user", userService.getUserByPrincipal(principal));
        model.addAttribute("appointment", appointmentService.findById(id));
        return "doctor-appointmentEdit";
    }

    @PostMapping("/doctor-appointmentEdit/{id}")
    public String doctorAppointmentEdit(@PathVariable("id") Long id,
                                        @RequestParam ("date")Date dateSQL,
                                        @RequestParam("time")String timeSQL,
                                        @RequestParam("cabinet") String cabinet){
        Appointment appointment=appointmentService.findById(id);
        appointmentService.correct(appointment,dateSQL.toLocalDate(), LocalTime.parse(timeSQL),cabinet);
        return "redirect:/doctor-appointmentEdit/{id}";
    }

    @PostMapping("/doctor-appointmentDelete/{id}")
    public String delete(@PathVariable("id") Long id, Principal principal) {
        User user = userService.getUserByPrincipal(principal);
        appointmentService.delete(appointmentService.findById(id));
        return "redirect:/doctorPage";
    }

}
