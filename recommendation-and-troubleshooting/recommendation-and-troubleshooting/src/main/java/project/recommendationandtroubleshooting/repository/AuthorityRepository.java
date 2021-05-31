package project.recommendationandtroubleshooting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.recommendationandtroubleshooting.model.Authority;

public interface AuthorityRepository extends JpaRepository<Authority, Integer> {
}
