package project.recommendationandtroubleshooting.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import project.recommendationandtroubleshooting.model.recommendation.ConfigurationCharacteristicType;

public interface ConfigurationCharacteristicTypeService {

	List<ConfigurationCharacteristicType> findAll();

	Page<ConfigurationCharacteristicType> findAll(Pageable pageable);
	
	ConfigurationCharacteristicType findOne(Integer id);
	
	ConfigurationCharacteristicType saveOne(ConfigurationCharacteristicType admin);
	
	boolean delete(Integer id);

	ConfigurationCharacteristicType update(ConfigurationCharacteristicType admin);
}
