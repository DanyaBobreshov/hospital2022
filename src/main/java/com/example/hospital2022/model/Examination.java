package com.example.hospital2022.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

//прием у врача
@Entity
@Data
@NoArgsConstructor
public class Examination {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Appointment appointment;
//описание
    private String description;
    //когда почувствовал недомогание
    private LocalDate dateOfDisease;
    private String epicrisis;
    private String recommendation;
    //ВАЖНО необходимо предусмотреть механизм добавления документа!
    //ПРИготовить заготовку для справки и заключения
    private String document;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn
    Disease disease;



}
