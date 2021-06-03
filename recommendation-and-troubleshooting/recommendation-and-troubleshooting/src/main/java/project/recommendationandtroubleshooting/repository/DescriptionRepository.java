package project.recommendationandtroubleshooting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.recommendationandtroubleshooting.model.troubleshooting.Description;

@Repository
public interface DescriptionRepository extends JpaRepository<Description, Integer> {
}
