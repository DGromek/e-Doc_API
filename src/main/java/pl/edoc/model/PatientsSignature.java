package pl.edoc.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.edoc.entity.Patient;

@Getter
@Setter
@NoArgsConstructor
public class PatientsSignature {
    private String firstName;
    private String lastName;

    public PatientsSignature(Patient patient) {
        this.firstName = patient.getFirstName();
        this.lastName = patient.getLastName().substring(0, 1);
    }
}
