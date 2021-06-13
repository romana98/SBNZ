package project.recommendationandtroubleshooting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import project.recommendationandtroubleshooting.model.recommendation.Favorite;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite, Integer> {

	@Query("SELECT COUNT(f.id) FROM User u JOIN u.favorites f WHERE u.id = ?1 AND f.configuration.id = ?2 AND f.active = true")
	Long checkIfUserFavorited(int idUser, int idConfiguration);
	
	@Query("SELECT f FROM User u JOIN u.favorites f WHERE u.id = ?1 AND f.configuration.id = ?2")
	Favorite getFavoriteByUserAndConfiguration(int idUser, int idConfiguration);
}


