package project.recommendationandtroubleshooting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import project.recommendationandtroubleshooting.model.recommendation.ConfigurationCharacteristicType;

@Repository
public interface ConfigurationCharacteristicTypeRepository extends JpaRepository<ConfigurationCharacteristicType, Integer> {

}
