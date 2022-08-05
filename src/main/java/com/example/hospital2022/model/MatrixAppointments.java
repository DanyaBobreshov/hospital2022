package com.example.hospital2022.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class MatrixAppointments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.EAGER)
    Doctor doctor;

    @OneToMany(cascade = CascadeType.ALL, fetch=FetchType.LAZY)
    private List<DayListAppointment> days=new ArrayList<>();
}
