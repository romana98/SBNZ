package project.recommendationandtroubleshooting.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import project.recommendationandtroubleshooting.model.User;

public interface UserService {
	
	List<User> findAll();

	Page<User> findAll(Pageable pageable);
	
	User findOne(int id);
	
	User saveOne(User admin);
	
	boolean delete(int id);

	User update(User admin);

}
