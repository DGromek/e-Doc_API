package pl.edoc.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.edoc.entity.Patient;

@Repository
@Transactional
public interface PatientRepository extends CrudRepository<Patient, Long> {
}
