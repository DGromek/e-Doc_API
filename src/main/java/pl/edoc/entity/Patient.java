package pl.edoc.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.pl.PESEL;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="patient")
@Getter
@Setter
@NoArgsConstructor
public class Patient {

    @Id
    @PESEL
    @Column(columnDefinition = "varchar(11)")
    private String pesel;

    @NotEmpty
    private String firstName;

    @NotEmpty
    private String lastName;

    @NotEmpty
    private char gender;

    @NotEmpty
    private String city;

    @NotEmpty
    @Column(columnDefinition = "varchar(5)")
    private String postal_code;

    @NotEmpty
    private String street;

    @NotEmpty
    @Column(columnDefinition = "varchar(4)")
    private String houseNr;

    private short flatNr;

    @NotEmpty
    @Email
    private String email;

    @NotEmpty
    @Column(columnDefinition = "varchar(11)")
    private String phoneNumber;

    private boolean isInsured;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
    private List<Appointment> appointments = new ArrayList<>();


}
