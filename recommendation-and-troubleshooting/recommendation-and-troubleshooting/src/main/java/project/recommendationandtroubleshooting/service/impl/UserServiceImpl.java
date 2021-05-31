package project.recommendationandtroubleshooting.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import project.recommendationandtroubleshooting.model.User;
import project.recommendationandtroubleshooting.repository.PersonRepository;
import project.recommendationandtroubleshooting.repository.UserRepository;
import project.recommendationandtroubleshooting.service.UserService;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PersonRepository personRepository;

    @Autowired
    EmailService emailService;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Page<User> findAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public User findOne(Integer id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User saveOne(User user) {
        if (personRepository.findByEmail(user.getEmail()) == null) {
            return userRepository.save(user);
        }
        return null;
    }

    @Override
    public boolean delete(Integer id) {
        User found = userRepository.findById(id).orElse(null);
        if (found != null) {
            userRepository.delete(found);
            return true;
        }
        return false;
    }

    @Override
    public User update(User user) {
        User found = userRepository.findByEmail(user.getEmail());
        if (found != null) {
            user.setId(found.getId());
            return userRepository.save(user);
        }
        return null;
    }

    @Override
    public User registerUser(User user) {
        User newUser = this.saveOne(user);
        if(newUser == null)
            return null;

        emailService.sendVerificationMail(newUser.getEmail(), newUser.getId());

        return newUser;
    }

    @Override
    public User activateAccount(Integer id) {
        User found = userRepository.findById(id).orElse(null);
        if (found == null)
            return null;
        found.setVerified(true);
        return userRepository.save(found);
    }

}
