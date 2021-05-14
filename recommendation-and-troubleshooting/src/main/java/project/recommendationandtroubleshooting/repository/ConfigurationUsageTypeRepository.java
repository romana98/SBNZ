package project.recommendationandtroubleshooting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import project.recommendationandtroubleshooting.model.input_model.recommendation.ConfigurationUsageType;

@Repository
public interface ConfigurationUsageTypeRepository extends JpaRepository<ConfigurationUsageType, Long> {

}
