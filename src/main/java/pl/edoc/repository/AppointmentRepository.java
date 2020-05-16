package pl.edoc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.edoc.entity.Appointment;

import java.util.List;

@Repository
@Transactional
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

//    @Query("SELECT a.date_of_appointment FROM Appointment a " +
//            "INNER JOIN Clinic c ON a.clinic_ic =  ")
//    List<Appointment> getPatientAppointments(String pesel);

    List<Appointment> findAllByPatient_Pesel(String pesel);
}
