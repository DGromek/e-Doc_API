package pl.edoc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.edoc.entity.Clinic;

@Repository
@Transactional
public interface ClinicRepository extends JpaRepository<Clinic, Integer> {

    @Query(value = "select d.speciality from doctor d " +
            "inner join appointment a on d.id = a.doctor_id " +
            "inner join clinic c on a.clinic_id = c.id " +
            "where a.clinic_id = ?1", nativeQuery = true)
    Iterable<String> getAllSpecialitiesInClinic(int clinicId);

}
