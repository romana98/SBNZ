package project.recommendationandtroubleshooting.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import org.springframework.stereotype.Service;

import project.recommendationandtroubleshooting.dto.AddConfigurationDTO;
import project.recommendationandtroubleshooting.dto.ConfigurationResponseDTO;
import project.recommendationandtroubleshooting.enums.ConfigurationType;
import project.recommendationandtroubleshooting.enums.DiscType;
import project.recommendationandtroubleshooting.model.recommendation.ConfigurationClass;
import project.recommendationandtroubleshooting.model.recommendation.Rating;
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
	
	public Page<ConfigurationResponseDTO> getFavoritesByUser(Pageable pageable, int idUser) {
		Page<ConfigurationClass> page = confRepository.getFavoritesByUser(idUser, pageable);
		List<ConfigurationResponseDTO> confDTOS = recommendationService.toDTOList(page.toList(), idUser);
	    return new PageImpl<>(confDTOS,page.getPageable(),page.getTotalElements());
	}
	
	public ConfigurationClass addConfiguration(AddConfigurationDTO dto) {
		ConfigurationClass c = new ConfigurationClass(dto.getPrice(), dto.getType(), dto.getCPU(), dto.getGPU(), dto.getRAM(), dto.getOS(), 
				dto.getPSU(), dto.getDiscType(), dto.getDiscSize(), dto.getMotherboard(), dto.getScreenSize(), dto.getScreenResolution(), 
				dto.getMusicCard(), dto.isTouchscreen(), dto.isMicrophone(), dto.isCamera(), dto.isErgonomic(), new HashSet<Rating>(),
				true);
		return saveOne(c);
	}

	@Override
	public ConfigurationClass saveOne(ConfigurationClass entity) {
		return confRepository.save(entity);
	}
	
	@Override
	public ConfigurationClass findOne(Integer id) {
		return confRepository.getOne(id);
	}
	
	@Override
	public boolean delete(Integer id) {
		confRepository.deleteById(id);
		return true;
	}

	@Override
	public ConfigurationClass update(ConfigurationClass admin) {
		// TODO Auto-generated method stub
		return null;
	}

}
