package project.recommendationandtroubleshooting.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import project.recommendationandtroubleshooting.dto.ConfigurationResponseDTO;
import project.recommendationandtroubleshooting.model.recommendation.ConfigurationClass;

public interface ConfigurationService {
	
	List<ConfigurationClass> findAll();

	Page<ConfigurationResponseDTO> findAll(Pageable pageable, int idUser);
	
	ConfigurationClass findOne(Integer id);
	
	ConfigurationClass saveOne(ConfigurationClass admin);
	
	boolean delete(Integer id);

	ConfigurationClass update(ConfigurationClass admin);

}
