package com.example.hospital2022.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
//список записей в течении одного дня
@Entity
@Data
@NoArgsConstructor
public class DayListAppointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titleDay;
    private LocalDate dateDay;

    @OneToMany(cascade = CascadeType.ALL, fetch=FetchType.LAZY)
    private List<Appointment> appointments=new ArrayList<>();

}
