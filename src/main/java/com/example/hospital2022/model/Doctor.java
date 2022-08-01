package com.example.hospital2022.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor

public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.EAGER)
    private User user;
    private String speciality;

    //Доктор лечит много пациентов, у пациента может быть много докторов
    @ManyToMany
    @JoinTable(name="doctors_patients",
                   joinColumns = @JoinColumn(name="doctor_id"),
                   inverseJoinColumns = @JoinColumn(name="patients_id"))
    private List<Patient> patients;

    //У доктора много записей
    @OneToMany(cascade = CascadeType.ALL, fetch=FetchType.LAZY)
    private List<Appointment> appointments;


}
