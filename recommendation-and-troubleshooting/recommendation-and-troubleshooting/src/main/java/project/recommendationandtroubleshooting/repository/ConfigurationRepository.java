package project.recommendationandtroubleshooting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import project.recommendationandtroubleshooting.model.recommendation.ConfigurationClass;

@Repository
public interface ConfigurationRepository extends JpaRepository<ConfigurationClass, Integer> {

}
