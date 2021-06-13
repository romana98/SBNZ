package project.recommendationandtroubleshooting.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import org.springframework.stereotype.Service;

import project.recommendationandtroubleshooting.dto.ConfigurationResponseDTO;
import project.recommendationandtroubleshooting.model.recommendation.ConfigurationClass;
import project.recommendationandtroubleshooting.repository.ConfigurationRepository;
import project.recommendationandtroubleshooting.service.ConfigurationService;
import project.recommendationandtroubleshooting.service.RecommendationService;

@Service
public class ConfigurationServiceImpl implements ConfigurationService {
	
	@Autowired
	ConfigurationRepository confRepository;
	
	@Autowired
	RecommendationServiceImpl recommendationService;

	@Override
	public List<ConfigurationClass> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<ConfigurationResponseDTO> findAll(Pageable pageable, int idUser) {		
		Page<ConfigurationClass> page = confRepository.findAll(pageable);
        List<ConfigurationResponseDTO> confDTOS = recommendationService.toDTOList(page.toList(), idUser);
        return new PageImpl<>(confDTOS,page.getPageable(),page.getTotalElements());
	}

	@Override
	public ConfigurationClass findOne(Integer id) {
		return confRepository.getOne(id);
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
