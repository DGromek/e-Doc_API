package pl.edoc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.edoc.entity.Appointment;

@Repository
@Transactional
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    Iterable<Appointment> findAllByPatient_Pesel(String pesel);
}
