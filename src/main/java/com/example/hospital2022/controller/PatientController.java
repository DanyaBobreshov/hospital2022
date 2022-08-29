package com.example.hospital2022.controller;

import com.example.hospital2022.model.Appointment;
import com.example.hospital2022.model.Doctor;
import com.example.hospital2022.model.Patient;
import com.example.hospital2022.model.User;
import com.example.hospital2022.service.AppointmentService;
import com.example.hospital2022.service.DoctorService;
import com.example.hospital2022.service.PatientService;
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
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class PatientController {
    private final UserService userService;
    private final PatientService patientService;
    private final DoctorService doctorService;
    private final AppointmentService appointmentService;

    //главная страница пациента
    @GetMapping("/patient")
    public String patientPage(Principal principal, Model model) {
        User user = userService.getUserByPrincipal(principal);
        Patient patient = patientService.findByUserId(user.getId());
        model.addAttribute("patient", patient);
        model.addAttribute("user", userService.getUserByPrincipal(principal));
        return "patient";
    }

    @GetMapping("/patient/{id}")
    public String patientPageId(Principal principal, Model model, @PathVariable("id") Long id) {
        Patient patient = patientService.findById(id);
        model.addAttribute("patient", patient);
        model.addAttribute("user", userService.getUserByPrincipal(principal));
        return "patient";
    }

    @GetMapping("/patient-searchDoctor")
    public String searchDoctor(@RequestParam(name = "searchWordName", required = false) String titleName,
                               @RequestParam(name = "searchWordSpec", required = false) String titleSpeciality,
                               Principal principal, Model model) {
        model.addAttribute("doctors", doctorService.listForPatient(titleName, titleSpeciality));
        model.addAttribute("user", userService.getUserByPrincipal(principal));
        model.addAttribute("searchWordName", titleName);
        model.addAttribute("searchWordSpec", titleSpeciality);
        return "patient-searchDoctor";
    }

    @GetMapping("/patient-doctorSearch/{id}")
    public String patientDoctorAppoints(@RequestParam(name = "searchWord", required = false) Date titleDate,
                                        Principal principal, Model model, @PathVariable("id") Long id) {
        Doctor doctor = doctorService.findById(id);
        LocalDate date;
        if (titleDate == null) {
            date = LocalDate.now();
        } else {
            date = titleDate.toLocalDate();
        }
        LocalTime time = LocalTime.of(8, 30);
        LocalTime lunch = LocalTime.of(13, 00);
        LocalTime endDay = LocalTime.of(18, 00);
        List<LocalTime> timeList = new ArrayList<>();
        while (time.isBefore(lunch)) {
            if (appointmentService.findByDoctorAndDateAndTime(doctor, date, time).isEmpty()) {
                timeList.add(time);
                System.out.println(time);
            }
            time = time.plusMinutes(30);
        }
        time = LocalTime.of(14, 00);
        while (time.isBefore(endDay)) {
            if (appointmentService.findByDoctorAndDateAndTime(doctor, date, time).isEmpty()) {
                timeList.add(time);
            }
            time = time.plusMinutes(30);
        }
        model.addAttribute("user", userService.getUserByPrincipal(principal));
        model.addAttribute("timeList", timeList);
        model.addAttribute("doctor", doctor);
        model.addAttribute("searchWord", date);
        return "/patient-doctorSearch";
    }

    @GetMapping("/patient-appointmentAdd/{date}/{time}/{id}")
    public String patientAppointmentAdd(@PathVariable("time") String time,
                                        @PathVariable("date") Date date,
                                        @PathVariable("id") Long doctorId,
                                        Principal principal, Model model) {
        model.addAttribute("doctor", doctorService.findById(doctorId));
        model.addAttribute("time", LocalTime.parse(time));
        model.addAttribute("date", date.toLocalDate());
        model.addAttribute("user", userService.getUserByPrincipal(principal));
        return "patient-appointmentAdd";
    }

    @PostMapping("/patient-appointment/{date}/{time}/{id}")
    public String addDoctor( @PathVariable("date") Date date,
                             @PathVariable("time") String time,
                             @PathVariable("id") Long doctorId,
                             Principal principal){
        Appointment appointment=new Appointment();
        LocalTime localTime=LocalTime.parse(time);
        LocalDate localDate=date.toLocalDate();
        //if(localDate.getDayOfWeek().equals(DayOfWeek.SATURDAY)||localDate.getDayOfWeek().equals(DayOfWeek.SUNDAY)){
        //    return "redirect:/patient-searchDoctor";
        //}
        Doctor doctor=doctorService.findById(doctorId);
        Patient patient=patientService.findByUserId(userService.getUserByPrincipal(principal).getId());
        appointmentService.setAppointment(appointment,localDate,localTime,doctor, patient);
        return "redirect:/patient-searchDoctor";
    }
}