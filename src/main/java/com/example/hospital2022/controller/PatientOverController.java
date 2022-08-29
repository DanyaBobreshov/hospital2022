package com.example.hospital2022.controller;

import com.example.hospital2022.model.Disease;
import com.example.hospital2022.model.User;
import com.example.hospital2022.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class PatientOverController {
    private final PatientService patientService;
    private final AppointmentService appointmentService;
    private final UserService userService;
    private final DiseaseService diseaseService;
    private final ExaminationService examinationService;

    @GetMapping("/patient-appointments")
    public String patientAppointments(Principal principal, Model model){
        User user=userService.getUserByPrincipal(principal);
        model.addAttribute("appointments", appointmentService.listPatient(patientService.findByUserId(user.getId()).getId()));
        model.addAttribute("user", user);
        return "patient-appointments";
    }

    @GetMapping("/appointment-delete/{id}")
    public String delete(@PathVariable("id") Long id, Principal principal) {
        User user = userService.getUserByPrincipal(principal);
        appointmentService.delete(appointmentService.findById(id));
        return "redirect:/patient-appointments";
    }

    @GetMapping("/patient-diseases")
    public String patientDiseases(Principal principal, Model model){
        User user=userService.getUserByPrincipal(principal);
        model.addAttribute("diseases", diseaseService.patientList(patientService.findByUserId(user.getId()).getId()));
        model.addAttribute("user", user);
        return "patient-diseases";
    }

    @GetMapping("/disease-examination/{id}")
    public String patientExamination(@PathVariable("id") Long diseaseId, Principal principal, Model model){
        User user=userService.getUserByPrincipal(principal);
        model.addAttribute("examinations", examinationService.diseaseList(diseaseId));
        model.addAttribute("user", user);
        return "disease-examination";
    }

}
