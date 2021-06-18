package project.recommendationandtroubleshooting.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.stereotype.Service;
import project.recommendationandtroubleshooting.model.recommendation.ConfigurationCharacteristicType;
import project.recommendationandtroubleshooting.repository.ConfigurationCharacteristicTypeRepository;
import project.recommendationandtroubleshooting.service.ConfigurationCharacteristicTypeService;

@Service
public class ConfigurationCharacteristicTypeServiceImpl implements ConfigurationCharacteristicTypeService {
	
	@Autowired
	ConfigurationCharacteristicTypeRepository repository;

	@Override
	public List<ConfigurationCharacteristicType> findAll() {
		return repository.findAll();
	}

	@Override
	public Page<ConfigurationCharacteristicType> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ConfigurationCharacteristicType findOne(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ConfigurationCharacteristicType saveOne(ConfigurationCharacteristicType admin) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ConfigurationCharacteristicType update(ConfigurationCharacteristicType admin) {
		// TODO Auto-generated method stub
		return null;
	}

}
