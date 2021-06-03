package project.recommendationandtroubleshooting.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import project.recommendationandtroubleshooting.exceptions.InvalidIdException;
import project.recommendationandtroubleshooting.exceptions.UniqueConstraintException;
import project.recommendationandtroubleshooting.model.troubleshooting.Solution;
import project.recommendationandtroubleshooting.repository.SolutionRepository;
import project.recommendationandtroubleshooting.service.SolutionService;

import java.util.List;

@Service
public class SolutionServiceImpl implements SolutionService {

    @Autowired
    SolutionRepository solutionRepository;


    @Override
    public List<Solution> findAll() {
        return solutionRepository.findAll();
    }

    @Override
    public Page<Solution> findAll(Pageable pageable) {
        return solutionRepository.findAll(pageable);
    }

    @Override
    public Solution findOne(Integer id) {
        return solutionRepository.findById(id).orElseThrow(() -> new InvalidIdException("Solution with id " + id + " doesn't exists."));
    }

    @Override
    public Solution saveOne(Solution solution) {
        try {
            return solutionRepository.save(solution);
        } catch (Exception e) {
            throw new UniqueConstraintException("Solution " + solution.getSolution() + " already exists.");
        }
    }

    @Override
    public void delete(Integer id) {
        Solution found = solutionRepository.findById(id).orElseThrow(() -> new InvalidIdException("Solution with id " + id + " doesn't exists."));
        solutionRepository.delete(found);

    }

    @Override
    public Solution update(Solution solution) {
        solutionRepository.findById(solution.getId()).orElseThrow(() -> new InvalidIdException("Solution with id " + solution.getId() + " doesn't exists."));
        return solutionRepository.save(solution);
    }
}
