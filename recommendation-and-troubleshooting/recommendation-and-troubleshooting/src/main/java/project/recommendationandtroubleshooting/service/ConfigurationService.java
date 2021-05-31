package project.recommendationandtroubleshooting.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import project.recommendationandtroubleshooting.model.recommendation.Configuration;

public interface ConfigurationService {
	
	List<Configuration> findAll();

	Page<Configuration> findAll(Pageable pageable);
	
	Configuration findOne(Integer id);
	
	Configuration saveOne(Configuration admin);
	
	boolean delete(Integer id);

	Configuration update(Configuration admin);

}
