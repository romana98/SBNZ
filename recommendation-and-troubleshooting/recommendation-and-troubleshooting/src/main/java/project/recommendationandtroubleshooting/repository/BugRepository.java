package project.recommendationandtroubleshooting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import project.recommendationandtroubleshooting.model.troubleshooting.Bug;

@Repository
public interface BugRepository extends JpaRepository<Bug, Integer> {

}
