package pl.edoc.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.edoc.dto.AppointmentDTO;
import pl.edoc.model.Status;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "appointment")
@Getter
@Setter
@NoArgsConstructor
public class Appointment implements Comparable<Appointment> {

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

    @OneToOne(mappedBy = "appointment", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Rating rating;

    @Enumerated(EnumType.STRING)
    private Status status;

    public Appointment(AppointmentDTO appointmentDto, Patient patient, Doctor doctor, Clinic clinic) {
        this.dateOfAppointment = appointmentDto.getDateOfAppointment();
        this.patient = patient;
        this.doctor = doctor;
        this.clinic = clinic;
        this.status = Status.PENDING;
    }

    public Appointment(Clinic clinic, Doctor doctor, LocalDateTime localDateTime) {
        this.clinic = clinic;
        this.doctor = doctor;
        this.dateOfAppointment = localDateTime;
    }

    @Override
    public int compareTo(Appointment o) {
        if (dateOfAppointment.isBefore(o.dateOfAppointment)) {
            return -1;
        } else if (dateOfAppointment.isAfter(o.dateOfAppointment)) {
            return 1;
        }
        return 0;
    }
}
