package project.recommendationandtroubleshooting.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.recommendationandtroubleshooting.model.Authority;
import project.recommendationandtroubleshooting.repository.AuthorityRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthorityService {

    @Autowired
    private AuthorityRepository authorityRepository;

    public List<Authority> findById(Integer id) {
        List<Authority> auths = new ArrayList<>();
        Authority auth = this.authorityRepository.findById(id).orElse(null);
        if (auth != null)
            auths.add(auth);
        return auths;
    }

}
