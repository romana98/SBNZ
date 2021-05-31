package project.recommendationandtroubleshooting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import project.recommendationandtroubleshooting.model.troubleshooting.BugHistory;

@Repository
public interface BugHistoryRepository extends JpaRepository<BugHistory, Integer> {

}
