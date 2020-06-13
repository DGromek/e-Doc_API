package pl.edoc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.edoc.entity.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor, Integer> {

    @Query(value = "SELECT * FROM doctor "
                   + "INNER JOIN schedule s on doctor.id = s.doctor_id "
                   + "INNER JOIN clinic c on s.clinic_id = c.id "
                   + "WHERE clinic_id = ?1", nativeQuery = true)
    Iterable<Doctor> findAllByClinicId(int clinicId);
}