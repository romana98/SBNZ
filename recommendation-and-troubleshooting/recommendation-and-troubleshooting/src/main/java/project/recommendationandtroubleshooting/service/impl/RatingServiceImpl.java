package project.recommendationandtroubleshooting.service.impl;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.FactHandle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.stereotype.Service;

import project.recommendationandtroubleshooting.dto.RatingDTO;
import project.recommendationandtroubleshooting.model.recommendation.ConfigurationClass;
import project.recommendationandtroubleshooting.model.recommendation.Rating;
import project.recommendationandtroubleshooting.repository.RatingRepository;
import project.recommendationandtroubleshooting.service.ConfigurationService;
import project.recommendationandtroubleshooting.service.RatingService;

@Service
public class RatingServiceImpl implements RatingService {
	
	@Autowired
	KieSession kieSession;

	@Autowired
	RatingRepository ratingRepository;
	
	@Autowired
	ConfigurationService confService;
	
	public Rating rate(RatingDTO dto) {
		ConfigurationClass conf = confService.findOne(dto.getConfigId());
		Rating r = new Rating(dto.getRate());
		if (conf.getRatings() == null) {
			conf.setRatings(new HashSet<Rating>());
		}
		conf.getRatings().add(r);
		
		Collection<FactHandle> handlers = kieSession.getFactHandles();
		for (FactHandle handle: handlers) {
			Object sessionObject = kieSession.getObject(handle);
        	if (sessionObject instanceof ConfigurationClass && ((ConfigurationClass) sessionObject).getId() == dto.getConfigId()) {
        		kieSession.delete(handle);
        	}
        }
		kieSession.insert(r);
		kieSession.insert(conf);
		
		return saveOne(r);
	}

	@Override
	public Rating saveOne(Rating rating) {
		return ratingRepository.save(rating);
	}
	
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
