package pl.edoc.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "rating")
@Getter
@Setter
@NoArgsConstructor
public class Rating {

    @Id
    @Column(name = "appointment_id")
    private int id;

    @NotNull
    @Min(1)
    @Max(5)
    private int rate;

    @Column(columnDefinition = "text")
    private String description;

    @OneToOne
    @MapsId
    @JoinColumn(name = "appointment_id")
    @JsonBackReference
    private Appointment appointment;
}
