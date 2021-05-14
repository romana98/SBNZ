package project.recommendationandtroubleshooting.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import project.recommendationandtroubleshooting.model.Rating;

public interface RatingService {

	List<Rating> findAll();

	Page<Rating> findAll(Pageable pageable);
	
	Rating findOne(int id);
	
	Rating saveOne(Rating admin);
	
	boolean delete(int id);

	Rating update(Rating admin);
}
