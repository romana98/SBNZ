package project.recommendationandtroubleshooting.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import project.recommendationandtroubleshooting.exceptions.InvalidIdException;
import project.recommendationandtroubleshooting.model.troubleshooting.BugHistory;
import project.recommendationandtroubleshooting.repository.BugHistoryRepository;
import project.recommendationandtroubleshooting.service.BugHistoryService;

import java.util.List;

@Service
public class BugHistoryServiceImpl implements BugHistoryService {

    @Autowired
    BugHistoryRepository bugHistoryRepository;

    @Override
    public List<BugHistory> findAll() {
        return bugHistoryRepository.findAll();
    }

    @Override
    public Page<BugHistory> findAll(Pageable pageable) {
        return bugHistoryRepository.findAll(pageable);
    }

    @Override
    public BugHistory findOne(Integer id) {
        return bugHistoryRepository.findById(id).orElseThrow(() -> new InvalidIdException("BugHistory with id " + id + " doesn't exists."));
    }

    @Override
    public BugHistory saveOne(BugHistory bugHistory) {
        return bugHistoryRepository.save(bugHistory);
    }

    @Override
    public void delete(Integer id) {
        BugHistory found = bugHistoryRepository.findById(id).orElseThrow(() -> new InvalidIdException("BugHistory with id " + id + " doesn't exists."));
        bugHistoryRepository.delete(found);
    }

    @Override
    public BugHistory update(BugHistory bugHistory) {
        bugHistoryRepository.findById(bugHistory.getId()).orElseThrow(() -> new InvalidIdException("BugHistory with id " + bugHistory.getId() + " doesn't exists."));
        return bugHistoryRepository.save(bugHistory);
    }

}
