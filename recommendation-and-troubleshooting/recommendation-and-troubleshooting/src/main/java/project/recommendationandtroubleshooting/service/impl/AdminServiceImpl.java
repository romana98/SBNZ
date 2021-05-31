package project.recommendationandtroubleshooting.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.stereotype.Service;
import project.recommendationandtroubleshooting.model.Admin;
import project.recommendationandtroubleshooting.repository.AdminRepository;
import project.recommendationandtroubleshooting.repository.PersonRepository;
import project.recommendationandtroubleshooting.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	AdminRepository adminRepository;

	@Autowired
	PersonRepository personRepository;

	@Override
	public List<Admin> findAll() {
		return adminRepository.findAll();
	}

	@Override
	public Page<Admin> findAll(Pageable pageable) {
		return adminRepository.findAll(pageable);
	}

	@Override
	public Admin findOne(Integer id) {
		return adminRepository.findById(id).orElse(null);
	}

	@Override
	public Admin findByEmail(String email) {
		return adminRepository.findByEmail(email);
	}

	@Override
	public Admin saveOne(Admin admin) {
		if(personRepository.findByEmail(admin.getEmail()) == null){
			return adminRepository.save(admin);
		}
		return null;
	}

	@Override
	public boolean delete(Integer id) {
		Admin found = adminRepository.findById(id).orElse(null);
		if(found != null){
			adminRepository.delete(found);
			return true;
		}
		return false;
	}

	@Override
	public Admin update(Admin admin) {
		Admin found = adminRepository.findByEmail(admin.getEmail());
		if(found != null){
			admin.setId(found.getId());
			return adminRepository.save(admin);
		}
		return null;
	}
}
