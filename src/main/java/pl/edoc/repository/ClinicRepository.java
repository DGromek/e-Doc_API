package pl.edoc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.edoc.entity.Clinic;

@Repository
@Transactional
public interface ClinicRepository extends JpaRepository<Clinic, Integer> {

    @Query(value = "select distinct d.speciality from doctor d " +
            "inner join schedule s on d.id = s.doctor_id " +
            "inner join clinic c on s.clinic_id = c.id " +
            "where s.clinic_id = ?1", nativeQuery = true)
    Iterable<String> getAllSpecialitiesInClinic(int clinicId);

    @Query(value = "SELECT city from clinic order by city DESC", nativeQuery = true)
    Iterable<String> getCities();
}
