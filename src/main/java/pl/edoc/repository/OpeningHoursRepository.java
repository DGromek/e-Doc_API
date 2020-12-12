package pl.edoc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.edoc.entity.OpeningHours;

@Repository
@Transactional
public interface OpeningHoursRepository extends JpaRepository<OpeningHours, Integer> {
}
