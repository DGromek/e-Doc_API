package pl.edoc.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.Nullable;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="clinic")
@Getter
@Setter
@NoArgsConstructor
public class Clinic implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty
    private String name;

    @NotEmpty
    @Column(columnDefinition = "varchar(6)")
    private String postal_code;

    @NotEmpty
    private String city;

    @NotEmpty
    private String street;

    @NotEmpty
    @Column(columnDefinition = "varchar(4)")
    private String houseNr;

    private Integer flatNr;

    @NotEmpty
    @Column(columnDefinition = "varchar(11)")
    private String phoneNumber;

    @OneToMany(mappedBy = "clinic", cascade = CascadeType.ALL)
    private List<Appointment> appointments = new ArrayList<>();

    @OneToMany(mappedBy = "clinic", cascade = CascadeType.ALL)
    private List<Schedule> schedules = new ArrayList<>();

    @Override
    public String getAuthority() {
        return "ROLE_CLINIC";
    }
}
