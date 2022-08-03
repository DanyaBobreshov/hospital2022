package com.example.hospital2022.controller;

import com.example.hospital2022.model.Doctor;
import com.example.hospital2022.model.User;
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

@Controller
@RequiredArgsConstructor
public class DoctorsController {
    private final DoctorService doctorService;
    private final UserService userService;

    @GetMapping("/addDoctors")
    public String addDoctor(@RequestParam(name = "SearchWord", required = false) String title,
                            Principal principal, Model model) {
        model.addAttribute("doctors", doctorService.list(title));
        model.addAttribute("user", userService.getUserByPrincipal(principal));
        model.addAttribute("searchWord", title);
        return "addDoctors";
    }

    @GetMapping("/addDoctors/edit/{id}")
    public String addDoctorsEdit(@PathVariable("id") Long id, Model model, Principal principal) {
        Doctor doctor = doctorService.findById(id);
        model.addAttribute("doctor", doctor);
        model.addAttribute("user", userService.getUserByPrincipal(principal));
        return "doctor-edit";
    }

    @PostMapping("/addDoctors/edit/doctor-edit/{id}")
    public String doctorEdit(@RequestParam("name") String name,
                             @RequestParam("secondName") String secondName,
                             @RequestParam("fatherName") String fatherName,
                             @RequestParam("dateOfBurn") Date dateOfBurn,
                             @RequestParam("telephone") String telephone,
                             @RequestParam("speciality") String speciality,
                             @PathVariable("id") Long id) {
        Doctor doctor = doctorService.findById(id);
        doctorService.correct(doctor, name, secondName, fatherName, telephone, dateOfBurn.toLocalDate(), speciality);
        return "redirect:/addDoctors";
    }

    @PostMapping("addDoctors/add")
    public String addDoctor( @RequestParam("userId") Long userId,
                             @RequestParam("speciality") String speciality,Principal principal) {
        Doctor doctor = new Doctor();
        doctor.setUser(userService.findById(userId));
        doctor.setSpeciality(speciality);
        doctorService.save(doctor);
        return "redirect:/addDoctors";
    }

    @GetMapping("/addDoctors/delete/{id}")
    public String delete(@PathVariable("id") Long id, Principal principal) {
        User user = userService.getUserByPrincipal(principal);
        doctorService.delete(doctorService.findById(id));
        return "redirect:/addDoctors";
    }

    @GetMapping("/doctorPage")
    public String doctorPage(Principal principal, Model model){
        User user= userService.getUserByPrincipal(principal);
        Doctor doctor=doctorService.findByUserId(user.getId());
        model.addAttribute("doctor", doctor);
        model.addAttribute("user", userService.getUserByPrincipal(principal));
        return "doctorPage";
    }
}