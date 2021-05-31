package project.recommendationandtroubleshooting.service.impl;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.stereotype.Service;
import project.recommendationandtroubleshooting.model.User;
import project.recommendationandtroubleshooting.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Override
	public List<User> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<User> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User findOne(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User findByEmail(String email) {
		return null;
	}

	@Override
	public User saveOne(User admin) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public User update(User admin) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User registerUser(User user) {
		return null;
	}

	@Override
	public User activateAccount(Integer id) {
		return null;
	}

}
