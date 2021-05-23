package project.recommendationandtroubleshooting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import project.recommendationandtroubleshooting.model.recommendation.Rating;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {

}
