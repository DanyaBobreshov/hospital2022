package com.example.hospital2022.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

//заболевание конкретного пациента (простуда а двух пациентов - два разных объекта Disease

@Entity
@Data
@NoArgsConstructor
public class Disease {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn
    private Diagnosis diagnosis;

    //Например грипп весной 2021 года
    private String title;

    private String form;

    @OneToMany(cascade = CascadeType.REFRESH, fetch=FetchType.LAZY)
    private List <Examination> examination;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Patient patient;


}
