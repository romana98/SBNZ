package project.recommendationandtroubleshooting.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import project.recommendationandtroubleshooting.exceptions.InvalidIdException;
import project.recommendationandtroubleshooting.exceptions.UniqueConstraintException;
import project.recommendationandtroubleshooting.model.User;
import project.recommendationandtroubleshooting.model.troubleshooting.BugHistory;
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
        return userRepository.findById(id).orElseThrow(() -> new InvalidIdException("User with id " + id + " doesn't exists."));
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User saveOne(User user) {
        try {
            return userRepository.save(user);
        } catch (Exception e) {
            throw new UniqueConstraintException("User with email " + user.getEmail() + " already exists.");
        }
    }

    @Override
    public void delete(Integer id) {
        User found = userRepository.findById(id).orElseThrow(() -> new InvalidIdException("Admin with id " + id + " doesn't exists."));
        if (found != null) {
            userRepository.delete(found);
        }
        throw new InvalidIdException("User with id " + id + " doesn't exists.");
    }

    @Override
    public User update(User user) {
        User found = userRepository.findByEmail(user.getEmail());
        if (found != null) {
            user.setId(found.getId());
            return userRepository.save(user);
        }
        throw new InvalidIdException("User with email " + user.getEmail() + " doesn't exists.");
    }

    @Override
    public User registerUser(User user) {
        User newUser = this.saveOne(user);
        emailService.sendVerificationMail(newUser.getEmail(), newUser.getId());

        return newUser;
    }

    @Override
    public User activateAccount(Integer id) {
        User found = userRepository.findById(id).orElseThrow(() -> new InvalidIdException("Admin with id " + id + " doesn't exists."));

        found.setVerified(true);
        return userRepository.save(found);
    }

    @Override
    public User addToBugHistory(Integer userId, BugHistory bugHistory) {
        User found = userRepository.findById(userId).orElseThrow(() -> new InvalidIdException("Admin with id " + userId + " doesn't exists."));
        found.getBugHistory().add(bugHistory);
        return this.update(found);
    }

}
