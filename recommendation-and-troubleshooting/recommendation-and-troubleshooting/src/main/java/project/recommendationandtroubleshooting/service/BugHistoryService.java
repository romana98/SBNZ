package project.recommendationandtroubleshooting.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import project.recommendationandtroubleshooting.model.troubleshooting.BugHistory;

public interface BugHistoryService {

	List<BugHistory> findAll();

	Page<BugHistory> findAll(Pageable pageable);
	
	BugHistory findOne(Integer id);
	
	BugHistory saveOne(BugHistory bugHistory);

	void delete(Integer id);

	BugHistory update(BugHistory bugHistory);
}
