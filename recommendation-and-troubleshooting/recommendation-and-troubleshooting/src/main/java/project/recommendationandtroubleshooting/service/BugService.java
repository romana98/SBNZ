package project.recommendationandtroubleshooting.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import project.recommendationandtroubleshooting.model.troubleshooting.Bug;

public interface BugService {

	List<Bug> findAll();

	Page<Bug> findAll(Pageable pageable);
	
	Bug findOne(Integer id);
	
	Bug saveOne(Bug admin);
	
	boolean delete(Integer id);

	Bug update(Bug admin);
}
