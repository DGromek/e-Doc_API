package pl.edoc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.edoc.entity.Clinic;

@Repository
@Transactional
public interface ClinicRepository extends JpaRepository<Clinic, Integer> {

    @Query(value = "select distinct d.speciality from doctor d "
            + "inner join schedule s on d.id = s.doctor_id "
            + "inner join clinic c on s.clinic_id = c.id "
            + "where s.clinic_id = ?1", nativeQuery = true)
    Iterable<String> getAllSpecialitiesInClinic(int clinicId);

    @Query(value = "SELECT DISTINCT city from clinic order by city DESC", nativeQuery = true)
    Iterable<String> getCities();

    @Query(value = "SELECT name from clinic order by city DESC", nativeQuery = true)
    Iterable<String> getClinicNames();

    Iterable<Clinic> findAllByCity(String city);

    Clinic findByCityAndName(String city, String name);

    @Query(value = "select name from clinic c "
            + "inner join schedule s on c.id = s.clinic_id "
            + "inner join doctor d on s.doctor_id = d.id "
            + "where c.city = ?1 and concat(d.last_name, ' ', d.first_name) = ?2", nativeQuery = true)
    Iterable<String> findAllClinicNamesByCityAndDoctorName(String city, String doctorName);
}
