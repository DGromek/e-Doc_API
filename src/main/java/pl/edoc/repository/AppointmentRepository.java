package pl.edoc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.edoc.entity.Appointment;

@Repository
@Transactional
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

//    @Query(value = "SELECT a.date_of_appointment, d.first_name, d.last_name, c.name FROM Appointment a " +
//            "INNER JOIN Clinic c ON a.clinic_id = c.id " +
//            "INNER JOIN Doctor d ON a.doctor_id = d.id ", nativeQuery = true)
//    Iterable<Appointment> getPatientAppointments(String pesel);

    Iterable<Appointment> findAllByPatient_Pesel(String pesel);
}
