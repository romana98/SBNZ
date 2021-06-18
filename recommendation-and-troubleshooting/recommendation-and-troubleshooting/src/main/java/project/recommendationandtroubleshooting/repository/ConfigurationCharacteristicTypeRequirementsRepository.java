package project.recommendationandtroubleshooting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import project.recommendationandtroubleshooting.model.recommendation.ConfigurationCharacteristicTypeRequirements;
import project.recommendationandtroubleshooting.model.recommendation.ConfigurationUsageTypeRequirements;

public interface ConfigurationCharacteristicTypeRequirementsRepository extends JpaRepository<ConfigurationCharacteristicTypeRequirements, Integer> {

	@Query("SELECT c FROM ConfigurationCharacteristicTypeRequirements c WHERE c.characteristic.id = ?1")
	ConfigurationCharacteristicTypeRequirements getByCharacterstic(Integer id);
}
