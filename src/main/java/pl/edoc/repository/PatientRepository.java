package pl.edoc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.edoc.entity.Patient;

@Repository
@Transactional
public interface PatientRepository extends JpaRepository<Patient, Integer> {
    Patient findByPesel(String pesel);

    @Query(value = "SELECT * from patient " +
            "inner join appointment a on patient.pesel = a.patient_pesel " +
            "where a.id = ?1", nativeQuery = true)
    Patient findByAppointmentId(int appointmentId);
}
