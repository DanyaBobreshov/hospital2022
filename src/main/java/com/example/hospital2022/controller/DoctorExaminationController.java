package com.example.hospital2022.controller;

import com.example.hospital2022.model.*;
import com.example.hospital2022.service.*;
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
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class DoctorExaminationController {
    private final DoctorService doctorService;
    private final PatientService patientService;
    private final UserService userService;
    private final AppointmentService appointmentService;
    private final ExaminationService examinationService;

    @GetMapping("AddNewExamination/{id}")
    public String doctorExamination(Principal principal, Model model, @PathVariable("id") Long id) {
        Appointment appointment=appointmentService.findById(id);
        Doctor doctor=appointment.getDoctor();
        Patient patient=appointment.getPatient();
        model.addAttribute("appointment", appointment);
        model.addAttribute("patient",patient);
        model.addAttribute("doctor",doctor);
        model.addAttribute("user", userService.getUserByPrincipal(principal));
        return "AddNewExamination";
    }

    @PostMapping("/AddNewExamination/{patient}/{doctor}/{appointment}")
    public String doctorExamination(
                             @PathVariable("patient") Long idPatient,
                             @PathVariable("doctor") Long idDoctor,
                             @PathVariable("appointment") Long idAppointment,
                             @RequestParam("description") String description,
                             @RequestParam("dateOfDisease") Date dateOfDisease,
                             @RequestParam("epicrisis") String epicrisis,
                             @RequestParam("recommendation") String recommendation,
                             @RequestParam("document") String document,
                             @RequestParam("form") String form,
                             @RequestParam("diagnosis") String diagnosis,
                             @RequestParam("disease") String disease){

        Examination examination=new Examination();
        Doctor doctor=doctorService.findById(idDoctor);
        Patient patient=patientService.findById(idPatient);
        Appointment appointment=appointmentService.findById(idAppointment);
        examinationService.correct(examination, appointment, patient, doctor, description, dateOfDisease.toLocalDate(), epicrisis,
                recommendation, document, disease, form, diagnosis);
        return "redirect:/doctorPage";
    }

    @GetMapping("/doctor-examination")
    public String DoctorExaminationsList(@RequestParam (name = "searchWord", required = false)String title,
                            Principal principal, Model model){
        User user=userService.getUserByPrincipal(principal);
        Doctor doctor =doctorService.findByUserId(user.getId());
        model.addAttribute("examinations", examinationService.doctorList(doctor,title));
        model.addAttribute("user", user);
        model.addAttribute("searchWord", title);
        return "doctor-examination";
    }

    @GetMapping("/AddNewExaminationNull")
    public String doctorExaminationNull(Principal principal, Model model){
        model.addAttribute("date", LocalDate.now());
        model.addAttribute("time", LocalTime.now());
        model.addAttribute("user", userService.getUserByPrincipal(principal));
        return "AddNewExaminationNull";
    }

    @PostMapping("/AddNewExaminationNull")
    public String doctorExaminationNull(@RequestParam("patient") String numberPatient,
                                        @RequestParam("date") Date date,
                                        @RequestParam("time") String stringTime,
                                        Principal principal,
                                        @RequestParam("description") String description,
                                        @RequestParam("dateOfDisease") Date dateOfDisease,
                                        @RequestParam("epicrisis") String epicrisis,
                                        @RequestParam("recommendation") String recommendation,
                                        @RequestParam("document") String document,
                                        @RequestParam("form") String form,
                                        @RequestParam("diagnosis") String diagnosis,
                                        @RequestParam("disease") String disease){
        User user = userService.getUserByPrincipal(principal);
        Doctor doctor=doctorService.findByUserId(user.getId());
        Patient patient = patientService.findByUserLogin(numberPatient);
        Appointment appointment;
        LocalTime time=LocalTime.parse(stringTime);
        Optional<Appointment> appointmentOptional=appointmentService.findByDoctorAndDateAndTime(doctor,date.toLocalDate(), time);
        if (appointmentOptional.isEmpty()){
            appointment=new Appointment();
            appointmentService.setAppointment(appointment, date.toLocalDate(),time, doctor, patient);
            appointmentOptional=appointmentService.findByDoctorAndDateAndTime(doctor,date.toLocalDate(), time);
        }
        if(!appointmentOptional.get().getPatient().getId().equals(patient.getId())){
            appointmentService.delete(appointmentOptional.get());
            appointment=new Appointment();
            appointmentService.setAppointment(appointment, date.toLocalDate(),time, doctor, patient);
            appointmentOptional=appointmentService.findByDoctorAndDateAndTime(doctor,date.toLocalDate(), time);
        }
        appointment=appointmentOptional.get();
        Examination examination=new Examination();
        examinationService.correct(examination, appointment, patient, doctor, description, dateOfDisease.toLocalDate(), epicrisis,
                recommendation, document, disease, form, diagnosis);
        return "redirect:/doctorPage";
    }

}