package com.example.hospital2022.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.EAGER)
    private User user;

    //у пациента много записей
    @OneToMany(cascade = CascadeType.REFRESH, fetch=FetchType.LAZY)
    private List<Appointment> appointments=new ArrayList<>();

    @ManyToMany(cascade = CascadeType.REFRESH, fetch=FetchType.LAZY)
    private List<Doctor> doctors=new ArrayList<>();

    //у пациента может быть много заболеваний, у заболевания может быть много пациентов
    @ManyToMany
    @JoinTable(name="patients_diseases",
          joinColumns = @JoinColumn(name="patient_id"),
          inverseJoinColumns = @JoinColumn(name="disease_id"))
    private List<Disease> diseases;

    @ManyToMany
    @JoinTable(name="patients_diagnoses",
            joinColumns = @JoinColumn(name="patient_id"),
            inverseJoinColumns = @JoinColumn(name="diagnosis_id"))
    private List<Diagnosis> diagnoses;

}
