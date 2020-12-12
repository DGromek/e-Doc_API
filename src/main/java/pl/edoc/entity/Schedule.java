package pl.edoc.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
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
    private LocalTime mo_begin;
    @NotNull
    private LocalTime mo_end;

    @NotNull
    private LocalTime tu_begin;
    @NotNull
    private LocalTime tu_end;

    @NotNull
    private LocalTime we_begin;
    @NotNull
    private LocalTime we_end;

    @NotNull
    private LocalTime th_begin;
    @NotNull
    private LocalTime th_end;

    @NotNull
    private LocalTime fr_begin;
    @NotNull
    private LocalTime fr_end;

    @NotNull
    private LocalTime sa_begin;
    @NotNull
    private LocalTime sa_end;

    @NotNull
    private LocalTime su_begin;
    @NotNull
    private LocalTime su_end;

    @ManyToOne
    @JsonBackReference
    private Doctor doctor;

    @ManyToOne
    @JsonBackReference
    private Clinic clinic;
}
