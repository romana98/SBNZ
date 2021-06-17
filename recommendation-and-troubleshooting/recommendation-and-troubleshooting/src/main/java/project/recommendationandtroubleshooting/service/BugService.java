package project.recommendationandtroubleshooting.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import project.recommendationandtroubleshooting.model.troubleshooting.Bug;
import project.recommendationandtroubleshooting.model.troubleshooting.Description;

import java.util.List;
import java.util.Set;

public interface BugService {

    List<Bug> findAll();

    Page<Bug> findAll(Pageable pageable);

    Bug findOne(Integer id);

    Bug findByDescription(Set<Description> descriptions);

    Bug saveOne(Bug bug);

    void delete(Integer id);

    Bug update(Bug bug);
}
