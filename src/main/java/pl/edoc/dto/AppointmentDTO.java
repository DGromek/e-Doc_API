package pl.edoc.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Setter
public class AppointmentDTO {
    private LocalDateTime dateOfAppointment;
    private int clinicId;
    private int doctorId;
}
