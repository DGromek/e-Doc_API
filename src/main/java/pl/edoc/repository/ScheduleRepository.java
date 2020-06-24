package pl.edoc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.edoc.entity.Schedule;

@Repository
@Transactional
public interface ScheduleRepository extends JpaRepository<Schedule, Integer> {

    Schedule getByDoctor_IdAndClinic_Id(int doctorId, int clinicId);
}
