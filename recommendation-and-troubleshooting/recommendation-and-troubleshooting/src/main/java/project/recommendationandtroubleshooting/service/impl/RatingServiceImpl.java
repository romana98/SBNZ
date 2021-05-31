package project.recommendationandtroubleshooting.service.impl;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.stereotype.Service;
import project.recommendationandtroubleshooting.model.recommendation.Rating;
import project.recommendationandtroubleshooting.service.RatingService;

@Service
public class RatingServiceImpl implements RatingService {

	@Override
	public List<Rating> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Rating> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Rating findOne(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Rating saveOne(Rating admin) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Rating update(Rating admin) {
		// TODO Auto-generated method stub
		return null;
	}

}
