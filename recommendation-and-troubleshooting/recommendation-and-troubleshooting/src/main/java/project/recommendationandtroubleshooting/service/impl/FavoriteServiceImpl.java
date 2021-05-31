package project.recommendationandtroubleshooting.service.impl;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.stereotype.Service;
import project.recommendationandtroubleshooting.model.recommendation.Favorite;
import project.recommendationandtroubleshooting.service.FavoriteService;

@Service
public class FavoriteServiceImpl implements FavoriteService {

	@Override
	public List<Favorite> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Favorite> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Favorite findOne(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Favorite saveOne(Favorite admin) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Favorite update(Favorite admin) {
		// TODO Auto-generated method stub
		return null;
	}

}
