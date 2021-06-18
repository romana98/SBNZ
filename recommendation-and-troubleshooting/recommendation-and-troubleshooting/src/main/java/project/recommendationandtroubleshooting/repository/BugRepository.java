package project.recommendationandtroubleshooting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import project.recommendationandtroubleshooting.model.troubleshooting.Bug;
import project.recommendationandtroubleshooting.model.troubleshooting.Description;

import java.util.Set;

@Repository
public interface BugRepository extends JpaRepository<Bug, Integer> {

    @Query("SELECT b FROM Bug b WHERE b.descriptions = ?1")
    Bug findByDescriptions(Set<Description> descriptions);

}
