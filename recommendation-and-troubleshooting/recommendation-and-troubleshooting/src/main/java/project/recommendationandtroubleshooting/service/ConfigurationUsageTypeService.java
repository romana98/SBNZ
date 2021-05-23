package project.recommendationandtroubleshooting.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import project.recommendationandtroubleshooting.model.recommendation.ConfigurationUsageType;

public interface ConfigurationUsageTypeService {

	List<ConfigurationUsageType> findAll();

	Page<ConfigurationUsageType> findAll(Pageable pageable);
	
	ConfigurationUsageType findOne(int id);
	
	ConfigurationUsageType saveOne(ConfigurationUsageType admin);
	
	boolean delete(int id);

	ConfigurationUsageType update(ConfigurationUsageType admin);
}
