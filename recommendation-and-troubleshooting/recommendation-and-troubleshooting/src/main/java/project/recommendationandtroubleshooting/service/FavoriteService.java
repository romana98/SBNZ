package project.recommendationandtroubleshooting.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import project.recommendationandtroubleshooting.model.recommendation.Favorite;

public interface FavoriteService {

	List<Favorite> findAll();

	Page<Favorite> findAll(Pageable pageable);
	
	Favorite findOne(Integer id);
	
	Favorite saveOne(Favorite admin);
	
	boolean delete(Integer id);

	Favorite update(Favorite admin);
}
