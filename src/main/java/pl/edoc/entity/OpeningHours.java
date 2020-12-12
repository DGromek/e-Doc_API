package pl.edoc.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
import java.time.LocalTime;

@Entity
@Table(name = "opening_hours")
@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class OpeningHours {

    @Id
    @Column(name = "clinic_id")
    private int id;

    private LocalTime moBegin;
    private LocalTime moEnd;

    private LocalTime tuBegin;
    private LocalTime tuEnd;

    private LocalTime weBegin;
    private LocalTime weEnd;

    private LocalTime thBegin;
    private LocalTime thEnd;

    private LocalTime frBegin;
    private LocalTime frEnd;

    private LocalTime saBegin;
    private LocalTime saEnd;

    private LocalTime suBegin;
    private LocalTime suEnd;

    @OneToOne
    @MapsId
    @JoinColumn(name = "clinic_id")
    @JsonBackReference
    private Clinic clinic;
}

