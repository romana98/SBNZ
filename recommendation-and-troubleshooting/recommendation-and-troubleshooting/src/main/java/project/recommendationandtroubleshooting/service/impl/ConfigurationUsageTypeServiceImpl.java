package project.recommendationandtroubleshooting.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.stereotype.Service;
import project.recommendationandtroubleshooting.model.recommendation.ConfigurationUsageType;
import project.recommendationandtroubleshooting.repository.ConfigurationUsageTypeRepository;
import project.recommendationandtroubleshooting.service.ConfigurationUsageTypeService;

@Service
public class ConfigurationUsageTypeServiceImpl implements ConfigurationUsageTypeService {
	
	@Autowired
	ConfigurationUsageTypeRepository repository;

	@Override
	public List<ConfigurationUsageType> findAll() {
		return repository.findAll();
	}

	@Override
	public Page<ConfigurationUsageType> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ConfigurationUsageType findOne(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ConfigurationUsageType saveOne(ConfigurationUsageType admin) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ConfigurationUsageType update(ConfigurationUsageType admin) {
		// TODO Auto-generated method stub
		return null;
	}

}
