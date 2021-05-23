package project.recommendationandtroubleshooting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import project.recommendationandtroubleshooting.model.recommendation.Favorite;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite, Long> {

}
