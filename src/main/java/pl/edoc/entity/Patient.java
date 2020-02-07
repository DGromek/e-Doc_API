package pl.edoc.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.pl.PESEL;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name="patient")
@Getter
@Setter
@NoArgsConstructor
public class Patient {

    @Id
    @PESEL
    @Column(columnDefinition = "varchar(11)")
    String pesel;

    @NotEmpty
    String firstName;

    @NotEmpty
    String lastName;

    @NotEmpty
    char gender;

    @NotEmpty
    String city;

    @NotEmpty
    @Column(columnDefinition = "varchar(5)")
    String postal_code;

    @NotEmpty
    String street;

    @NotEmpty
    @Column(columnDefinition = "varchar(4)")
    String houseNr;

    @NotEmpty
    short flatNr;

    @NotEmpty
    @Email
    String email;

    @NotEmpty
    @Column(columnDefinition = "varchar(11)")
    String phoneNumber;

    boolean isInsured;


}
