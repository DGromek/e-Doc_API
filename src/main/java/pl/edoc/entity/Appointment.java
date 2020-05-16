package pl.edoc.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.edoc.model.Status;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "appointment")
@Getter
@Setter
@NoArgsConstructor
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    private LocalDateTime dateOfAppointment;

    @ManyToOne
    @JsonBackReference
    private Patient patient;

    @ManyToOne
    @JsonManagedReference
    private Doctor doctor;

    @ManyToOne
    @JsonManagedReference
    private Clinic clinic;

    @Enumerated(EnumType.STRING)
    private Status status;
}
