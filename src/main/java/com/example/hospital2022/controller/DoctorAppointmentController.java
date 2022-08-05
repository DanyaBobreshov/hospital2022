package com.example.hospital2022.controller;

import com.example.hospital2022.model.Appointment;
import com.example.hospital2022.model.DayListAppointment;
import com.example.hospital2022.model.Doctor;
import com.example.hospital2022.model.MatrixAppointments;
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
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class DoctorAppointmentController {
    private final UserService userService;
    private final DoctorService doctorService;
    private final DayListAppointmentService dayListAppointmentService;
    private final MatrixAppointmentsService matrixAppointmentsService;
    private final AppointmentService appointmentService;
    private final PatientService patientService;
    @GetMapping("/doctor-appointments/{id}")
    public String doctorAppoints(@RequestParam(name = "SearchWord", required = false)
                            Principal principal, Model model, @PathVariable("id") Long id) {
        Doctor doctor=doctorService.findById(id);
        LocalDate date=LocalDate.now();
        ArrayList<DayListAppointment> dayList;
        if(date.getDayOfWeek().equals(DayOfWeek.SATURDAY)){
            dayList = (ArrayList<DayListAppointment>) dayListAppointmentService.list(" ");
            for(DayListAppointment dayListAppointment:dayList){
                doctor.getMatrixAppointments().getDays().remove(dayListAppointment);
                matrixAppointmentsService.save(doctor.getMatrixAppointments());
                dayListAppointmentService.delete(dayListAppointment);
            }
            if (doctor.getMatrixAppointments()==null){
                MatrixAppointments matrixAppointments=new MatrixAppointments();
                matrixAppointmentsService.save(matrixAppointments);
                doctor.setMatrixAppointments(matrixAppointments);
            }
         dayList= (ArrayList<DayListAppointment>) dayListAppointmentService.makeNewDayListAppointment(date, doctor, doctor.getMatrixAppointments());
        }else {
            dayList= (ArrayList<DayListAppointment>) doctor.getMatrixAppointments().getDays();
        }
        date=LocalDate.now();
        for(int i=1; i<6; i++){
            model.addAttribute("appointments"+i, dayList.get(i-1).getAppointments());
        }
        model.addAttribute("date", LocalDate.now());
        for (int i=1; i<6; i++){
            model.addAttribute("date"+i, date);
            date=date.plusDays(1);
        }
        model.addAttribute("user", userService.getUserByPrincipal(principal));
        model.addAttribute("doctorName", doctor.getUser().getSecondName());
        model.addAttribute("date", LocalDate.now());
        return "doctor-appointments";
    }

    @GetMapping("/doctor-appointmentEdit/{id}")
    public String doctorappointment(@PathVariable("id") String idString, Model model, Principal principal){
        idString=idString.replaceAll("\\s","");
        System.out.println(idString);
        Long id=Long.valueOf(idString);
        Appointment appointment=appointmentService.findById(id);
        model.addAttribute("appointment", appointment);
        model.addAttribute("user", userService.getUserByPrincipal(principal));
        return "doctor-appointmentEdit";
    }

    @PostMapping("/doctor-appointmentEdit/{id}")
    public String doctorappointmentEdit(@PathVariable("id") String idString,
                                        @RequestParam("date")Date date,
                                        @RequestParam("time") Time time,
                                        @RequestParam("cabinet")String cabinet){
       String nidString=idString.replaceAll("\\s","");
        Long id=Long.valueOf(nidString);
        Appointment appointment=appointmentService.findById(id);
        appointmentService.correct(appointment,date.toLocalDate(), time.toLocalTime(), cabinet);
        return "redirect:/doctor-appointmentEdit";
    }

}
