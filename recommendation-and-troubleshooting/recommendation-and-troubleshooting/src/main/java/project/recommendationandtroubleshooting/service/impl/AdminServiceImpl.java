package project.recommendationandtroubleshooting.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import project.recommendationandtroubleshooting.exceptions.InvalidIdException;
import project.recommendationandtroubleshooting.exceptions.UniqueConstraintException;
import project.recommendationandtroubleshooting.model.Admin;
import project.recommendationandtroubleshooting.repository.AdminRepository;
import project.recommendationandtroubleshooting.repository.PersonRepository;
import project.recommendationandtroubleshooting.service.AdminService;

import java.util.List;

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
        return adminRepository.findById(id).orElseThrow(() -> new InvalidIdException("Admin with id " + id + " doesn't exists."));
    }

    @Override
    public Admin findByEmail(String email) {
        return adminRepository.findByEmail(email);
    }

    @Override
    public Admin saveOne(Admin admin) {
        try {
            return adminRepository.save(admin);
        } catch (Exception e) {
            throw new UniqueConstraintException("Admin with email " + admin.getEmail() + " already exists.");
        }
    }

    @Override
    public void delete(Integer id) {
        Admin found = adminRepository.findById(id).orElseThrow(() -> new InvalidIdException("Admin with id " + id + " doesn't exists."));
        adminRepository.delete(found);

        throw new InvalidIdException("Admin with id " + id + " doesn't exists.");
    }

    @Override
    public Admin update(Admin admin) {
        Admin found = adminRepository.findByEmail(admin.getEmail());
        if (found != null) {
            found.setFirstName(admin.getFirstName());
            found.setLastName(admin.getLastName());
            found.setPassword(admin.getPassword());
            return adminRepository.save(found);
        }
        throw new InvalidIdException("Admin with email " + admin.getEmail() + " doesn't exists.");
    }
}
