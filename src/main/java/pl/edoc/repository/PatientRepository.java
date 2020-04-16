package pl.edoc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.edoc.entity.Patient;

@Repository
@Transactional
public interface PatientRepository extends JpaRepository<Patient, Long> {
    Patient findByPesel(String pesel);
}
