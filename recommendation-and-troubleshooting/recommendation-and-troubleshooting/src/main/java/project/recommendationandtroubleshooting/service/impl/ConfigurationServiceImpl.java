package project.recommendationandtroubleshooting.service.impl;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.stereotype.Service;
import project.recommendationandtroubleshooting.model.recommendation.Configuration;
import project.recommendationandtroubleshooting.service.ConfigurationService;

@Service
public class ConfigurationServiceImpl implements ConfigurationService {

	@Override
	public List<Configuration> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Configuration> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Configuration findOne(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Configuration saveOne(Configuration admin) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Configuration update(Configuration admin) {
		// TODO Auto-generated method stub
		return null;
	}

}
