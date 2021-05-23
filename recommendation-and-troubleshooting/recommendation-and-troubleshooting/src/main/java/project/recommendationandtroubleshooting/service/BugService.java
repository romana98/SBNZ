package project.recommendationandtroubleshooting.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import project.recommendationandtroubleshooting.model.troubleshooting.Bug;

public interface BugService {

	List<Bug> findAll();

	Page<Bug> findAll(Pageable pageable);
	
	Bug findOne(int id);
	
	Bug saveOne(Bug admin);
	
	boolean delete(int id);

	Bug update(Bug admin);
}
