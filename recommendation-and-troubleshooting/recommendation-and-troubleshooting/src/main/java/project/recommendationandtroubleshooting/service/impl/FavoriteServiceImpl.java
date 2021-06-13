package project.recommendationandtroubleshooting.service.impl;

import java.util.Date;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.stereotype.Service;

import project.recommendationandtroubleshooting.dto.FavoriteDTO;
import project.recommendationandtroubleshooting.model.User;
import project.recommendationandtroubleshooting.model.recommendation.ConfigurationClass;
import project.recommendationandtroubleshooting.model.recommendation.Favorite;
import project.recommendationandtroubleshooting.repository.FavoriteRepository;
import project.recommendationandtroubleshooting.repository.UserRepository;
import project.recommendationandtroubleshooting.service.FavoriteService;

@Service
public class FavoriteServiceImpl implements FavoriteService {
	
	@Autowired
	FavoriteRepository favoriteRepository;
	
	@Autowired
	ConfigurationServiceImpl confService;
	
	@Autowired
	UserServiceImpl userService;
	
	public boolean checkIfUserFavorited(int idUser, int idConfiguration) {
		return favoriteRepository.checkIfUserFavorited(idUser, idConfiguration) > 0 ? true : false;
	}
	
	public boolean addFavorite(FavoriteDTO dto, int userId) {
		Favorite favorite = favoriteRepository.getFavoriteByUserAndConfiguration(userId, dto.getConfigId());
		if (favorite == null) {
			ConfigurationClass config = confService.findOne(dto.getConfigId());
			User u = userService.findOne(userId);
			Favorite f = new Favorite(config, 1L, new Date());
			if (u.getFavorites() == null)
				u.setFavorites(new HashSet<Favorite>());
			u.getFavorites().add(f);
			saveOne(f);
			//DODATI U KIE SESIJU
		} else {
			favorite.setDateOfFavorite(new Date());
			favorite.setActive(dto.isValue());
			if (dto.isValue())
				favorite.incrementPutToFavorite();
			saveOne(favorite);
		}
		return true;
	}
	
	@Override
	public Favorite saveOne(Favorite favorite) {
		return favoriteRepository.save(favorite);
	}


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
