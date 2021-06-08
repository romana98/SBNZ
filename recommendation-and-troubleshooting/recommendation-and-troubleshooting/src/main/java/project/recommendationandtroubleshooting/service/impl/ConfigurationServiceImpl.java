package project.recommendationandtroubleshooting.service.impl;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.stereotype.Service;
import project.recommendationandtroubleshooting.model.recommendation.ConfigurationClass;
import project.recommendationandtroubleshooting.service.ConfigurationService;

@Service
public class ConfigurationServiceImpl implements ConfigurationService {

	@Override
	public List<ConfigurationClass> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<ConfigurationClass> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ConfigurationClass findOne(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ConfigurationClass saveOne(ConfigurationClass admin) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ConfigurationClass update(ConfigurationClass admin) {
		// TODO Auto-generated method stub
		return null;
	}

}
