package project.recommendationandtroubleshooting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import project.recommendationandtroubleshooting.model.Bug;

@Repository
public interface BugRepository extends JpaRepository<Bug, Long> {

}
