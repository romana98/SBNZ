package project.recommendationandtroubleshooting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.recommendationandtroubleshooting.model.troubleshooting.Solution;

@Repository
public interface SolutionRepository extends JpaRepository<Solution, Integer> {
}
