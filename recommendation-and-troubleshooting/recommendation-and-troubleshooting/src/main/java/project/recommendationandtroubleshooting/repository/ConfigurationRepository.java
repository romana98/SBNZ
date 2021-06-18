package project.recommendationandtroubleshooting.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import project.recommendationandtroubleshooting.model.recommendation.ConfigurationClass;
import project.recommendationandtroubleshooting.model.recommendation.Favorite;

@Repository
public interface ConfigurationRepository extends JpaRepository<ConfigurationClass, Integer> {
	
	@Query("SELECT f.configuration FROM User u JOIN u.favorites f WHERE u.id = ?1 AND f.active = true")
	Page<ConfigurationClass> getFavoritesByUser(int idUser, Pageable pageable);

}
