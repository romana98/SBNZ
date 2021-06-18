package project.recommendationandtroubleshooting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import project.recommendationandtroubleshooting.model.recommendation.ConfigurationUsageTypeRequirements;

@Repository
public interface ConfigurationUsageTypeRequirementsRepository extends JpaRepository<ConfigurationUsageTypeRequirements, Integer> {

	@Query("SELECT c FROM ConfigurationUsageTypeRequirements c WHERE c.usage.id = ?1")
	ConfigurationUsageTypeRequirements getByUsage(Integer id);
}
