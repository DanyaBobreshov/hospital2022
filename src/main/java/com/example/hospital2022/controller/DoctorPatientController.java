package com.example.hospital2022.controller;

import com.example.hospital2022.model.Doctor;
import com.example.hospital2022.model.Patient;
import com.example.hospital2022.model.User;
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

@Controller
@RequiredArgsConstructor
public class DoctorPatientController {

private final DoctorService doctorService;
private final UserService userService;
private final PatientService patientService;

    @GetMapping("doctor-show-patients")
    public String doctorShowPatients(@RequestParam(name = "searchWord", required = false)String title,
                            Principal principal, Model model){
        User user = userService.getUserByPrincipal(principal);
        Doctor doctor=doctorService.findByUserId(user.getId());
        model.addAttribute("patients", patientService.findByDoctorAndTitleContains(doctor, title));
        model.addAttribute("user", user);
        model.addAttribute("searchWord", title);
        return "doctor-show-patients";
    }

    @GetMapping("patients/edit/{id}")
    public String edit(@PathVariable("id") Long id, Model model, Principal principal){
        Patient patient=patientService.findById(id);
        model.addAttribute("patient", patient);
        model.addAttribute("user", userService.getUserByPrincipal(principal));
        return "patient-edit";
    }

    @PostMapping("patients/edit/patient-edit/{id}")
    public String edit(@RequestParam("secondName") String secondName,
                       @RequestParam("name") String name,
                       @RequestParam("fatherName") String fatherName,
                       @RequestParam("telephone") String telephone,
                       @RequestParam("dateOfBurn") Date SQLDate,
                       @PathVariable ("id") Long id){
        Patient patient=patientService.findById(id);
        patientService.correct(patient, name, secondName, fatherName, SQLDate.toLocalDate(), telephone);
        return "redirect:/doctor-show-patients";
    }

    @GetMapping("patients/delete/{id}")
    public String delete(@PathVariable("id")Long id){
        patientService.delete(patientService.findById(id));
        return "redirect:/doctor-show-patients";
    }
}
