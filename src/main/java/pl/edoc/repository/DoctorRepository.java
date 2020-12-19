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
            + "WHERE c.name = ?1 AND doctor.speciality = ?2", nativeQuery = true)
    Iterable<Doctor> findAllByClinicNameAndSpeciality(String clinicName, String speciality);

    @Query(value = "SELECT DISTINCT speciality from doctor order by speciality desc", nativeQuery = true)
    Iterable<String> getSpecialities();

    @Query(value = "SELECT concat(first_name, ' ', last_name) from doctor order by speciality desc", nativeQuery = true)
    Iterable<String> getDoctorsNames();

    @Query(value = "SELECT * FROM doctor as d "
            + "INNER JOIN schedule s on d.id = s.doctor_id "
            + "INNER JOIN clinic c on s.clinic_id = c.id "
            + "WHERE d.speciality = ?1 AND c.name = ?2 AND concat(d.first_name, ' ', d.last_name) = ?3", nativeQuery = true)
    Doctor findBySpecialityAndClinicNameAndDoctorName(String speciality, String clinicName, String doctorName);
}