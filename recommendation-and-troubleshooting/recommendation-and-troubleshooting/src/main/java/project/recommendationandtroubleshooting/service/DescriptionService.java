package project.recommendationandtroubleshooting.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import project.recommendationandtroubleshooting.model.troubleshooting.Description;

import java.util.List;

public interface DescriptionService {

    List<Description> findAll();

    Page<Description> findAll(Pageable pageable);

    Description findOne(Integer id);

    Description saveOne(Description description);

    void delete(Integer id);

    Description update(Description description);
}
