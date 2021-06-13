package project.recommendationandtroubleshooting.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import project.recommendationandtroubleshooting.dto.ConfigurationResponseDTO;
import project.recommendationandtroubleshooting.dto.IntervalDTO;
import project.recommendationandtroubleshooting.dto.RateDTO;
import project.recommendationandtroubleshooting.model.recommendation.ConfigurationClass;
import project.recommendationandtroubleshooting.model.recommendation.Configurations;
import project.recommendationandtroubleshooting.model.recommendation.InputRequirements;
import project.recommendationandtroubleshooting.model.recommendation.Recommendations;

public interface RecommendationService {

	Page<ConfigurationResponseDTO> getRecommendation(InputRequirements input, Pageable pageable, int idUser);
	
	Page<ConfigurationResponseDTO> getCurrentlyPopular(Pageable pageable, int idUser);

	Page<ConfigurationResponseDTO> getIntervalPopular(IntervalDTO dto, Pageable pageable, int idUser);
	
	Page<ConfigurationResponseDTO> searchByRate(RateDTO dto, Pageable pageable, int idUser);

	Double getAverageRating(Long configurationId);
	
	Long getUsersByRate(Long configurationId, Long grade);

}
