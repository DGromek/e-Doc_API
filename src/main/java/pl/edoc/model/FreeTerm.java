package pl.edoc.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import pl.edoc.entity.Clinic;
import pl.edoc.entity.Doctor;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class FreeTerm {

    private Clinic clinic;
    private Doctor doctor;
    private LocalDateTime dateTime;
}
