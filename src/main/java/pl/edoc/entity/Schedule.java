package pl.edoc.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalTime;

@Entity
@Table(name = "schedule")
@Getter
@Setter
@NoArgsConstructor
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    LocalTime mo_begin;
    @NotNull
    LocalTime mo_end;

    @NotNull
    LocalTime tu_begin;
    @NotNull
    LocalTime tu_end;

    @NotNull
    LocalTime we_begin;
    @NotNull
    LocalTime we_end;

    @NotNull
    LocalTime th_begin;
    @NotNull
    LocalTime th_end;

    @NotNull
    LocalTime fr_begin;
    @NotNull
    LocalTime fr_end;

    @NotNull
    LocalTime sa_begin;
    @NotNull
    LocalTime sa_end;

    @NotNull
    LocalTime su_begin;
    @NotNull
    LocalTime su_end;

    @ManyToOne
    private Doctor doctor;

    @ManyToOne
    private Clinic clinic;
}
