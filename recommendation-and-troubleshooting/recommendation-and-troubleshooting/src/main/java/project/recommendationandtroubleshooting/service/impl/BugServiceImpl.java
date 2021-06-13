package project.recommendationandtroubleshooting.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import project.recommendationandtroubleshooting.exceptions.InvalidIdException;
import project.recommendationandtroubleshooting.model.troubleshooting.Bug;
import project.recommendationandtroubleshooting.model.troubleshooting.Description;
import project.recommendationandtroubleshooting.model.troubleshooting.Solution;
import project.recommendationandtroubleshooting.repository.BugRepository;
import project.recommendationandtroubleshooting.service.BugService;

import java.util.*;

@Service
public class BugServiceImpl implements BugService {

    @Autowired
    BugRepository bugRepository;

    @Override
    public List<Bug> findAll() {
        return bugRepository.findAll();
    }

    @Override
    public Page<Bug> findAll(Pageable pageable) {
        return bugRepository.findAll(pageable);
    }

    @Override
    public Bug findOne(Integer id) {
        return bugRepository.findById(id).orElseThrow(() -> new InvalidIdException("Bug with id " + id + " doesn't exists."));
    }

    @Override
    public Bug saveOne(Bug bug) {
        return bugRepository.save(bug);
    }

    @Override
    public void delete(Integer id) {
        Bug found = bugRepository.findById(id).orElseThrow(() -> new InvalidIdException("Bug with id " + id + " doesn't exists."));
        bugRepository.delete(found);

    }

    @Override
    public Bug update(Bug bug) {
        bugRepository.findById(bug.getId()).orElseThrow(() -> new InvalidIdException("Bug with id " + bug.getId() + " doesn't exists."));
        return bugRepository.save(bug);
    }

}
