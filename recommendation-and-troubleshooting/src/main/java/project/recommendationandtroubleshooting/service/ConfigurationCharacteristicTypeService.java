package project.recommendationandtroubleshooting.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import project.recommendationandtroubleshooting.model.input_model.recommendation.ConfigurationCharacteristicType;

public interface ConfigurationCharacteristicTypeService {

	List<ConfigurationCharacteristicType> findAll();

	Page<ConfigurationCharacteristicType> findAll(Pageable pageable);
	
	ConfigurationCharacteristicType findOne(int id);
	
	ConfigurationCharacteristicType saveOne(ConfigurationCharacteristicType admin);
	
	boolean delete(int id);

	ConfigurationCharacteristicType update(ConfigurationCharacteristicType admin);
}
