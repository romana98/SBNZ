package project.recommendationandtroubleshooting.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import project.recommendationandtroubleshooting.model.Admin;

public interface AdminService {
	
	List<Admin> findAll();

	Page<Admin> findAll(Pageable pageable);
	
	Admin findOne(int id);
	
	Admin saveOne(Admin admin);
	
	boolean delete(int id);

	Admin update(Admin admin);

}
