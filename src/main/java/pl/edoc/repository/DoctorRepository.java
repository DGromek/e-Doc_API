package pl.edoc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.edoc.entity.Doctor;

@Repository
@Transactional
public interface DoctorRepository extends JpaRepository<Doctor, Integer> {

    @Query(value = "SELECT * FROM doctor "
            + "INNER JOIN schedule s on doctor.id = s.doctor_id "
            + "INNER JOIN clinic c on s.clinic_id = c.id "
            + "WHERE clinic_id = ?1", nativeQuery = true)
    Iterable<Doctor> findAllByClinicId(int clinicId);

    @Query(value = "SELECT * FROM doctor "
            + "INNER JOIN schedule s on doctor.id = s.doctor_id "
            + "INNER JOIN clinic c on s.clinic_id = c.id "
            + "WHERE clinic_id = ?1 AND doctor.speciality = ?2", nativeQuery = true)
    Iterable<Doctor> findAllByClinicIdAndSpeciality(int clinicId, String speciality);

    @Query(value = "SELECT speciality from doctor order by speciality desc", nativeQuery = true)
    Iterable<String> getSpecialities();

    @Query(value = "SELECT concat(first_name, ' ', last_name) from doctor order by speciality desc", nativeQuery = true)
    Iterable<String> getDoctorsNames();
}