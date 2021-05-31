package project.recommendationandtroubleshooting.service.impl;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.stereotype.Service;
import project.recommendationandtroubleshooting.model.Admin;
import project.recommendationandtroubleshooting.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService {

	@Override
	public List<Admin> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Admin> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Admin findOne(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Admin findByEmail(String email) {
		return null;
	}

	@Override
	public Admin saveOne(Admin admin) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Admin update(Admin admin) {
		// TODO Auto-generated method stub
		return null;
	}

}
