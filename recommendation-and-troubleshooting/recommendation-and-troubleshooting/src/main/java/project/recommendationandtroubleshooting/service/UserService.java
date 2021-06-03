package project.recommendationandtroubleshooting.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import project.recommendationandtroubleshooting.model.Admin;
import project.recommendationandtroubleshooting.model.User;

public interface UserService {
	
	List<User> findAll();

	Page<User> findAll(Pageable pageable);
	
	User findOne(Integer id);

	User findByEmail(String email);
	
	User saveOne(User user);
	
	void delete(Integer id);

	User update(User user);

	User registerUser(User user);

	User activateAccount(Integer id);

}
