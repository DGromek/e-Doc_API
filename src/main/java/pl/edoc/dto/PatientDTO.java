package pl.edoc.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PatientDTO {
    private String pesel;
    private String firstName;
    private String lastName;
    private String city;
    private String street;
    private String houseNr;
    private String postalCode;
    private String password;
    private String phoneNr;
    private String email;
}
