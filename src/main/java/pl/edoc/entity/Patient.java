package pl.edoc.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.pl.PESEL;
import org.springframework.security.core.GrantedAuthority;
import pl.edoc.dto.PatientDTO;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "patient")
@Getter
@Setter
@NoArgsConstructor
public class Patient implements GrantedAuthority {

    @Id
    @PESEL
    @Column(columnDefinition = "varchar(11)")
    private String pesel;

    @NotEmpty
    private String firstName;

    @NotEmpty
    private String lastName;

    @NotEmpty
    @Size(min = 2)
    private String password;

    @NotEmpty
    private String city;

    @NotEmpty
    @Column(columnDefinition = "varchar(6)")
    private String postalCode;

    @NotEmpty
    private String street;

    @NotEmpty
    @Column(columnDefinition = "varchar(8)")
    private String houseNr;

    @Email
    private String email;

    @Column(columnDefinition = "varchar(11)")
    private String phoneNumber;


    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<Appointment> appointments = new ArrayList<>();

    public Patient(PatientDTO patientDTO) {
        this.pesel = patientDTO.getPesel();
        this.firstName = patientDTO.getFirstName();
        this.lastName = patientDTO.getLastName();
        this.password = patientDTO.getPassword();
        this.city = patientDTO.getCity();
        this.postalCode = patientDTO.getPostalCode();
        this.street = patientDTO.getStreet();
        this.houseNr = patientDTO.getHouseNr();
        this.email = patientDTO.getEmail();
        this.phoneNumber = patientDTO.getPhoneNr();
    }

    @Override
    public String getAuthority() {
        return "ROLE_PATIENT";
    }
}
