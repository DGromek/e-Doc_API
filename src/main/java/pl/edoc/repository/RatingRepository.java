package pl.edoc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edoc.entity.Rating;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Integer> {
}
