package project.recommendationandtroubleshooting.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import project.recommendationandtroubleshooting.model.troubleshooting.Solution;

import java.util.List;

public interface SolutionService {

    List<Solution> findAll();

    Page<Solution> findAll(Pageable pageable);

    Solution findOne(Integer id);

    Solution saveOne(Solution solution);

    void delete(Integer id);

    Solution update(Solution solution);
}
