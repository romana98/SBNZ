package project.recommendationandtroubleshooting.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import project.recommendationandtroubleshooting.exceptions.InvalidIdException;
import project.recommendationandtroubleshooting.exceptions.UniqueConstraintException;
import project.recommendationandtroubleshooting.model.troubleshooting.Description;
import project.recommendationandtroubleshooting.repository.DescriptionRepository;
import project.recommendationandtroubleshooting.service.DescriptionService;

import java.util.List;

@Service
public class DescriptionServiceImpl implements DescriptionService {

    @Autowired
    DescriptionRepository descriptionRepository;

    @Override
    public List<Description> findAll() {
        return descriptionRepository.findAll();
    }

    @Override
    public Page<Description> findAll(Pageable pageable) {
        return descriptionRepository.findAll(pageable);
    }

    @Override
    public Description findOne(Integer id) {
        return descriptionRepository.findById(id).orElseThrow(() -> new InvalidIdException("Description with id " + id + " doesn't exists."));
    }

    @Override
    public Description saveOne(Description description) {
        try {
            return descriptionRepository.save(description);
        } catch (Exception e) {
            throw new UniqueConstraintException("Description " + description.getProblemDescription() + " already exists.");
        }
    }

    @Override
    public void delete(Integer id) {
        Description found = descriptionRepository.findById(id).orElseThrow(() -> new InvalidIdException("Description with id " + id + " doesn't exists."));
        descriptionRepository.delete(found);
    }

    @Override
    public Description update(Description description) {
        descriptionRepository.findById(description.getId()).orElseThrow(() -> new InvalidIdException("Description with id " + description.getId() + " doesn't exists."));
        return descriptionRepository.save(description);
    }
}
