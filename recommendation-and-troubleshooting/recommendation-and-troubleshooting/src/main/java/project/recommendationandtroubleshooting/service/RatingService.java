package project.recommendationandtroubleshooting.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import project.recommendationandtroubleshooting.model.recommendation.Rating;

public interface RatingService {

	List<Rating> findAll();

	Page<Rating> findAll(Pageable pageable);
	
	Rating findOne(Integer id);
	
	Rating saveOne(Rating admin);
	
	boolean delete(Integer id);

	Rating update(Rating admin);
}
