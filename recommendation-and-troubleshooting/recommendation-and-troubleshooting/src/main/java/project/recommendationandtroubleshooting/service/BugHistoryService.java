package project.recommendationandtroubleshooting.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import project.recommendationandtroubleshooting.model.BugHistory;

public interface BugHistoryService {

	List<BugHistory> findAll();

	Page<BugHistory> findAll(Pageable pageable);
	
	BugHistory findOne(int id);
	
	BugHistory saveOne(BugHistory admin);
	
	boolean delete(int id);

	BugHistory update(BugHistory admin);
}
