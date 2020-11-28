package pl.edoc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.edoc.entity.Appointment;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Repository
@Transactional
public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {

    Iterable<Appointment> findAllByPatient_Pesel(String pesel);

    @Query(value = "SELECT date_of_appointment FROM appointment "
                   + "WHERE DATE(date_of_appointment) = ?1 "
                   + "AND clinic_id=?2 "
                   + "AND doctor_id=?3", nativeQuery = true)
    Iterable<LocalDateTime> findAllDatesOfAppointmentsOnGivenDate(LocalDate dateOfAppointment, int clinicId, int doctorId);
}
